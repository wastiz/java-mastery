package com.javamastery.spring.data.domain;

import jakarta.persistence.*;

// TODO: маппинг сущности
public class Member {

    // TODO: первичный ключ
    private Long id;

    private String name;

    private String email;

    protected Member() {
    }

    public Member(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Long getId() { return id; }

    public String getName() { return name; }

    public String getEmail() { return email; }
}
