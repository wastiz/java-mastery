package com.javamastery.spring.security.users;

import java.util.Set;

/** Учётная запись. Пароль хранится только в виде BCrypt-хэша. Готово. */
public record UserAccount(String username, String passwordHash, Set<String> roles) {}
