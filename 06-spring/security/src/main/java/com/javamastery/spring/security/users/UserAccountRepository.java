package com.javamastery.spring.security.users;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

/** Демо-хранилище пользователей. Готово. */
@Repository
public class UserAccountRepository {

    private final Map<String, UserAccount> users;

    public UserAccountRepository() {
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        users = Map.of(
                "alice", new UserAccount("alice", bcrypt.encode("alice-pass"), Set.of("USER")),
                "admin", new UserAccount("admin", bcrypt.encode("admin-pass"), Set.of("USER", "ADMIN")));
    }

    public Optional<UserAccount> findByUsername(String username) {
        return Optional.ofNullable(users.get(username));
    }

    public int count() {
        return users.size();
    }
}
