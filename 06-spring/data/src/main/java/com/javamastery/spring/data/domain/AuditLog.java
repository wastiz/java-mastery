package com.javamastery.spring.data.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

// TODO: маппинг сущности
public class AuditLog {

    // TODO: первичный ключ
    private Long id;

    private String action;

    private String details;

    private LocalDateTime createdAt;

    protected AuditLog() {
    }

    public AuditLog(String action, String details, LocalDateTime createdAt) {
        this.action = action;
        this.details = details;
        this.createdAt = createdAt;
    }

    public Long getId() { return id; }

    public String getAction() { return action; }

    public String getDetails() { return details; }

    public LocalDateTime getCreatedAt() { return createdAt; }
}
