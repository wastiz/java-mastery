package com.javamastery.spring.security.notes;

import java.time.Instant;

public record Note(long id, String owner, String text, Instant createdAt) {}
