package com.javamastery.spring.data.repo;

import com.javamastery.spring.data.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
