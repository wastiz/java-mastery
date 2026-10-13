package com.javamastery.spring.data.repo;

import com.javamastery.spring.data.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
