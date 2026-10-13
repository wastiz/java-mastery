package com.javamastery.spring.data.service;

import com.javamastery.spring.data.domain.AuditLog;
import com.javamastery.spring.data.repo.AuditLogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.LocalDateTime;

/**
 * S05 · Аудит должен сохраняться ВСЕГДА — даже если внешняя транзакция потом откатится.
 * Какой propagation для этого нужен? И почему этот метод должен быть в ОТДЕЛЬНОМ бине, а не в LoanService?
 */
@Service
public class AuditService {

    private final AuditLogRepository repository;
    private final Clock clock;

    public AuditService(AuditLogRepository repository, Clock clock) {
        this.repository = repository;
        this.clock = clock;
    }

    // TODO: транзакционная аннотация
    public void log(String action, String details) {
        repository.save(new AuditLog(action, details, LocalDateTime.now(clock)));
    }
}
