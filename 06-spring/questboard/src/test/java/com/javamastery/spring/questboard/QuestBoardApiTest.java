package com.javamastery.spring.questboard;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.Duration;
import java.time.Instant;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Тесты «чёрного ящика»: только HTTP. Каждый тест получает чистое приложение и чистую БД.
 */
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class QuestBoardApiTest {

    static final Instant START = Instant.parse("2026-10-13T10:00:00Z");

    @TestConfiguration
    static class ClockConfig {
        @Bean
        @Primary
        MutableClock testClock() {
            return new MutableClock(START);
        }
    }

    @Autowired MockMvc mvc;
    @Autowired MutableClock clock;

    // ===== helpers =====

    ResultActions postJson(String url, String json) throws Exception {
        return mvc.perform(post(url).contentType(MediaType.APPLICATION_JSON).content(json));
    }

    long hero(String name, String cls) throws Exception {
        String body = postJson("/api/heroes", "{\"name\": \"%s\", \"heroClass\": \"%s\"}".formatted(name, cls))
                .andExpect(status().isCreated()).andReturn().getResponse().getContentAsString();
        return ((Number) JsonPath.read(body, "$.id")).longValue();
    }

    long quest(String title, String difficulty) throws Exception {
        return quest(title, difficulty, null);
    }

    long quest(String title, String difficulty, Instant deadline) throws Exception {
        String dl = deadline == null ? "" : ", \"deadline\": \"" + deadline + "\"";
        String body = postJson("/api/quests", "{\"title\": \"%s\", \"difficulty\": \"%s\"%s}".formatted(title, difficulty, dl))
                .andExpect(status().isCreated()).andReturn().getResponse().getContentAsString();
        return ((Number) JsonPath.read(body, "$.id")).longValue();
    }

    ResultActions accept(long quest, long hero) throws Exception {
        return postJson("/api/quests/" + quest + "/accept", "{\"heroId\": " + hero + "}");
    }

    ResultActions complete(long quest) throws Exception {
        return mvc.perform(post("/api/quests/" + quest + "/complete"));
    }

    void doQuest(long hero, String difficulty) throws Exception {
        long q = quest("Quest " + difficulty, difficulty);
        accept(q, hero).andExpect(status().isOk());
        complete(q).andExpect(status().isOk()).andExpect(jsonPath("$.status").value("COMPLETED"));
    }

    // ===== tests =====

    @Test
    void createHero() throws Exception {
        postJson("/api/heroes", "{\"name\": \"Aragorn\", \"heroClass\": \"WARRIOR\"}")
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Aragorn"))
                .andExpect(jsonPath("$.heroClass").value("WARRIOR"))
                .andExpect(jsonPath("$.xp").value(0))
                .andExpect(jsonPath("$.level").value(1))
                .andExpect(jsonPath("$.gold").value(0));
        postJson("/api/heroes", "{\"name\": \"Aragorn\", \"heroClass\": \"MAGE\"}").andExpect(status().isConflict());
        postJson("/api/heroes", "{\"name\": \"Al\", \"heroClass\": \"MAGE\"}").andExpect(status().isBadRequest());
        postJson("/api/heroes", "{\"name\": \"Nameless\"}").andExpect(status().isBadRequest());
        mvc.perform(get("/api/heroes/424242")).andExpect(status().isNotFound());
    }

    @Test
    void createQuest() throws Exception {
        postJson("/api/quests", "{\"title\": \"Slay the dragon\", \"difficulty\": \"HARD\", \"deadline\": \"2026-10-20T12:00:00Z\"}")
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value("OPEN"))
                .andExpect(jsonPath("$.rewardXp").value(300))
                .andExpect(jsonPath("$.rewardGold").value(60))
                .andExpect(jsonPath("$.heroId").doesNotExist());
        postJson("/api/quests", "{\"title\": \"Too late\", \"difficulty\": \"EASY\", \"deadline\": \"2026-10-12T00:00:00Z\"}")
                .andExpect(status().isBadRequest());
        postJson("/api/quests", "{\"title\": \"\", \"difficulty\": \"EASY\"}").andExpect(status().isBadRequest());
    }

    @Test
    void fullCycleWithClassBonuses() throws Exception {
        long warrior = hero("Boromir", "WARRIOR"), mage = hero("Gandalf", "MAGE"), rogue = hero("Bilbo", "ROGUE");
        doQuest(warrior, "HARD");
        doQuest(mage, "HARD");
        doQuest(rogue, "HARD");
        mvc.perform(get("/api/heroes/" + warrior)).andExpect(jsonPath("$.xp").value(300)).andExpect(jsonPath("$.gold").value(60))
                .andExpect(jsonPath("$.level").value(2));
        mvc.perform(get("/api/heroes/" + mage)).andExpect(jsonPath("$.xp").value(330)).andExpect(jsonPath("$.gold").value(60));
        mvc.perform(get("/api/heroes/" + rogue)).andExpect(jsonPath("$.xp").value(300)).andExpect(jsonPath("$.gold").value(72));
    }

    @Test
    void acceptRules() throws Exception {
        long h = hero("Frodo", "ROGUE");
        long q1 = quest("Q1", "EASY"), q2 = quest("Q2", "EASY"), q3 = quest("Q3", "EASY"), legendary = quest("Ring", "LEGENDARY");
        accept(q1, h).andExpect(status().isOk()).andExpect(jsonPath("$.status").value("IN_PROGRESS")).andExpect(jsonPath("$.heroId").value(h));
        accept(q1, h).andExpect(status().isConflict());
        accept(q2, h).andExpect(status().isOk());
        accept(q3, h).andExpect(status().isConflict());
        accept(legendary, hero("Sam", "WARRIOR")).andExpect(status().isConflict());
        accept(424242, h).andExpect(status().isNotFound());
        accept(q3, 424242).andExpect(status().isNotFound());
        complete(q3).andExpect(status().isConflict());
        mvc.perform(get("/api/quests").param("status", "IN_PROGRESS")).andExpect(jsonPath("$", hasSize(2)));
        mvc.perform(get("/api/quests").param("status", "OPEN")).andExpect(jsonPath("$[*].title", contains("Q3", "Ring")));
        mvc.perform(get("/api/quests")).andExpect(jsonPath("$", hasSize(4)));
    }

    @Test
    void legendaryNeedsLevel3() throws Exception {
        long h = hero("Aragorn", "WARRIOR");
        doQuest(h, "HARD");      // 300
        doQuest(h, "MEDIUM");    // 420 → level 3
        mvc.perform(get("/api/heroes/" + h)).andExpect(jsonPath("$.level").value(3));
        long ring = quest("Destroy the ring", "LEGENDARY");
        accept(ring, h).andExpect(status().isOk());
        complete(ring).andExpect(status().isOk());
        mvc.perform(get("/api/heroes/" + h)).andExpect(jsonPath("$.xp").value(1420)).andExpect(jsonPath("$.level").value(4));
    }

    @Test
    void abandon() throws Exception {
        long h = hero("Pippin", "WARRIOR");
        doQuest(h, "MEDIUM");   // 25 gold
        long q = quest("Second breakfast", "EASY");
        accept(q, h);
        mvc.perform(post("/api/quests/" + q + "/abandon")).andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("OPEN")).andExpect(jsonPath("$.heroId").doesNotExist());
        mvc.perform(get("/api/heroes/" + h)).andExpect(jsonPath("$.gold").value(15));
        accept(q, h);
        mvc.perform(post("/api/quests/" + q + "/abandon"));
        accept(q, h);
        mvc.perform(post("/api/quests/" + q + "/abandon"));
        mvc.perform(get("/api/heroes/" + h)).andExpect(jsonPath("$.gold").value(0));
        mvc.perform(post("/api/quests/" + q + "/abandon")).andExpect(status().isConflict());
    }

    @Test
    void lateCompletionFails() throws Exception {
        long h = hero("Merry", "WARRIOR");
        long q = quest("Deliver the letter", "MEDIUM", START.plus(Duration.ofDays(1)));
        accept(q, h);
        clock.advance(Duration.ofDays(2));
        complete(q).andExpect(status().isOk()).andExpect(jsonPath("$.status").value("FAILED"));
        mvc.perform(get("/api/heroes/" + h)).andExpect(jsonPath("$.xp").value(0));
        mvc.perform(get("/api/heroes/" + h + "/achievements")).andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void expiryJob() throws Exception {
        long h = hero("Faramir", "WARRIOR");
        long soon = quest("Soon", "EASY", START.plus(Duration.ofHours(1)));
        long taken = quest("Taken", "EASY", START.plus(Duration.ofHours(2)));
        long later = quest("Later", "EASY", START.plus(Duration.ofDays(10)));
        quest("No deadline", "EASY");
        accept(taken, h);
        clock.advance(Duration.ofHours(3));
        mvc.perform(post("/api/admin/expire-quests")).andExpect(status().isOk()).andExpect(jsonPath("$.expired").value(2));
        mvc.perform(get("/api/quests").param("status", "EXPIRED")).andExpect(jsonPath("$[*].title", contains("Soon", "Taken")));
        mvc.perform(post("/api/admin/expire-quests")).andExpect(jsonPath("$.expired").value(0));
        // герой освободился: может взять 2 новых квеста
        accept(later, h).andExpect(status().isOk());
        accept(quest("Another", "EASY"), h).andExpect(status().isOk());
    }

    @Test
    void leaderboardIsFreshAfterChanges() throws Exception {
        long a = hero("Arwen", "MAGE"), b = hero("Eowyn", "WARRIOR"), c = hero("Legolas", "ROGUE");
        doQuest(b, "MEDIUM");
        mvc.perform(get("/api/leaderboard").param("limit", "2"))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name").value("Eowyn"))
                .andExpect(jsonPath("$[0].rank").value(1))
                .andExpect(jsonPath("$[1].name").value("Arwen"));
        doQuest(c, "HARD");
        doQuest(a, "MEDIUM");   // 132 xp у мага
        mvc.perform(get("/api/leaderboard").param("limit", "3"))
                .andExpect(jsonPath("$[*].name", contains("Legolas", "Arwen", "Eowyn")))
                .andExpect(jsonPath("$[0].xp").value(300))
                .andExpect(jsonPath("$[1].xp").value(132))
                .andExpect(jsonPath("$[2].rank").value(3));
        mvc.perform(get("/api/leaderboard").param("limit", "2"))
                .andExpect(jsonPath("$[*].name", contains("Legolas", "Arwen")));
    }

    @Test
    void achievements() throws Exception {
        long h = hero("Gimli", "ROGUE");
        doQuest(h, "EASY");
        mvc.perform(get("/api/heroes/" + h + "/achievements")).andExpect(jsonPath("$", contains("FIRST_QUEST")));
        doQuest(h, "HARD");     // золото: 12 + 72 = 84
        doQuest(h, "MEDIUM");   // + 30 = 114 → RICH, и 3 квеста → HARD_WORKER
        mvc.perform(get("/api/heroes/" + h + "/achievements"))
                .andExpect(jsonPath("$", contains("FIRST_QUEST", "HARD_WORKER", "RICH")));
        long g = hero("Saruman", "MAGE");
        doQuest(g, "HARD");
        doQuest(g, "HARD");     // 660 xp → level 3, золото 120 → RICH
        long ring = quest("Palantir", "LEGENDARY");
        accept(ring, g).andExpect(status().isOk());
        complete(ring);
        mvc.perform(get("/api/heroes/" + g + "/achievements"))
                .andExpect(jsonPath("$", contains("FIRST_QUEST", "RICH", "HARD_WORKER", "LEGEND")));
    }
}
