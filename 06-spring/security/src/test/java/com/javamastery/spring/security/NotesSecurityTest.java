package com.javamastery.spring.security;

import com.jayway.jsonpath.JsonPath;
import com.javamastery.spring.security.notes.NoteRepository;
import com.javamastery.spring.security.security.TokenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Duration;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class NotesSecurityTest {

    @Autowired MockMvc mvc;
    @Autowired NoteRepository notes;
    @Autowired JwtDecoder jwtDecoder;
    @Autowired TokenService tokenService;
    @Autowired PasswordEncoder passwordEncoder;

    @BeforeEach
    void clean() {
        notes.deleteAll();
    }

    String login(String user, String password) throws Exception {
        String body = mvc.perform(post("/auth/login").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\": \"%s\", \"password\": \"%s\"}".formatted(user, password)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.expiresIn").value(3600))
                .andReturn().getResponse().getContentAsString();
        return JsonPath.read(body, "$.token");
    }

    static String bearer(String token) {
        return "Bearer " + token;
    }

    long createNote(String token, String text) throws Exception {
        String body = mvc.perform(post("/api/notes").header("Authorization", bearer(token))
                        .contentType(MediaType.APPLICATION_JSON).content("{\"text\": \"%s\"}".formatted(text)))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();
        return ((Number) JsonPath.read(body, "$.id")).longValue();
    }

    @Test
    void publicEndpoints() throws Exception {
        mvc.perform(get("/actuator/health")).andExpect(status().isOk());
    }

    @Test
    void protectedWithoutToken() throws Exception {
        mvc.perform(get("/api/notes")).andExpect(status().isUnauthorized());
        mvc.perform(get("/api/me")).andExpect(status().isUnauthorized());
    }

    @Test
    void loginFailures() throws Exception {
        mvc.perform(post("/auth/login").contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\": \"alice\", \"password\": \"wrong\"}")).andExpect(status().isUnauthorized());
        mvc.perform(post("/auth/login").contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\": \"mallory\", \"password\": \"x\"}")).andExpect(status().isUnauthorized());
    }

    @Test
    void tokenClaims() throws Exception {
        Jwt jwt = jwtDecoder.decode(login("admin", "admin-pass"));
        assertThat(jwt.getSubject()).isEqualTo("admin");
        assertThat(jwt.getClaimAsString("iss")).isEqualTo("java-mastery");
        assertThat(jwt.getClaimAsStringList("roles")).containsExactly("ADMIN", "USER");
        assertThat(Duration.between(jwt.getIssuedAt(), jwt.getExpiresAt())).isEqualTo(Duration.ofHours(1));
    }

    @Test
    void me() throws Exception {
        mvc.perform(get("/api/me").header("Authorization", bearer(login("alice", "alice-pass"))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("alice"))
                .andExpect(jsonPath("$.roles", containsInAnyOrder("USER")));
    }

    @Test
    void notesAreIsolatedPerUser() throws Exception {
        String alice = login("alice", "alice-pass"), admin = login("admin", "admin-pass");
        createNote(alice, "buy milk");
        createNote(alice, "learn spring security");
        createNote(admin, "admin stuff");
        mvc.perform(get("/api/notes").header("Authorization", bearer(alice)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].text").value("buy milk"))
                .andExpect(jsonPath("$[0].owner").value("alice"));
        mvc.perform(get("/api/notes").header("Authorization", bearer(admin)))
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void deleteRules() throws Exception {
        String alice = login("alice", "alice-pass"), admin = login("admin", "admin-pass");
        long aliceNote = createNote(alice, "mine");
        long adminNote = createNote(admin, "admin's");
        mvc.perform(delete("/api/notes/" + adminNote).header("Authorization", bearer(alice))).andExpect(status().isForbidden());
        mvc.perform(delete("/api/notes/" + aliceNote).header("Authorization", bearer(admin))).andExpect(status().isNoContent());
        mvc.perform(delete("/api/notes/" + aliceNote).header("Authorization", bearer(alice))).andExpect(status().isNotFound());
        mvc.perform(delete("/api/notes/" + adminNote).header("Authorization", bearer(admin))).andExpect(status().isNoContent());
    }

    @Test
    void adminOnly() throws Exception {
        String alice = login("alice", "alice-pass"), admin = login("admin", "admin-pass");
        createNote(alice, "x");
        mvc.perform(get("/api/admin/stats").header("Authorization", bearer(alice))).andExpect(status().isForbidden());
        mvc.perform(get("/api/admin/stats").header("Authorization", bearer(admin)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.users").value(2))
                .andExpect(jsonPath("$.notes").value(1));
    }

    @Test
    void expiredAndTamperedTokensAreRejected() throws Exception {
        String expired = tokenService.issue("alice", List.of("USER"), Duration.ofMinutes(-5));
        mvc.perform(get("/api/me").header("Authorization", bearer(expired))).andExpect(status().isUnauthorized());

        String valid = login("alice", "alice-pass");
        String[] parts = valid.split("\\.");
        // подменяем payload: пытаемся выдать себя за админа
        String forgedPayload = java.util.Base64.getUrlEncoder().withoutPadding()
                .encodeToString("{\"sub\":\"admin\",\"roles\":[\"ADMIN\"],\"exp\":9999999999}".getBytes());
        String forged = parts[0] + "." + forgedPayload + "." + parts[2];
        mvc.perform(get("/api/admin/stats").header("Authorization", bearer(forged))).andExpect(status().isUnauthorized());
    }

    @Test
    void passwordEncoderIsBcrypt() {
        assertThat(passwordEncoder.encode("x")).startsWith("$2");
    }
}
