package com.javamastery.spring.data.repo;

import com.javamastery.spring.data.domain.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {

    default List<AuditLog> findAllByOrderByIdAsc() { throw new UnsupportedOperationException("TODO: derived query"); }
}
