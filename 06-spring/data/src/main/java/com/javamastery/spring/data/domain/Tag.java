package com.javamastery.spring.data.domain;

import jakarta.persistence.*;

// TODO: маппинг сущности
public class Tag {

    // TODO: первичный ключ
    private Long id;

    private String name;

    protected Tag() {
    }

    public Tag(String name) {
        this.name = name;
    }

    public Long getId() { return id; }

    public String getName() { return name; }
}
