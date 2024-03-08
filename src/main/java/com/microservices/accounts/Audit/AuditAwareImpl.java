package com.microservices.accounts.Audit;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditAwareImpl implements AuditorAware<String> {
    @Override
    public Optional getCurrentAuditor() {
        return Optional.empty();
    }
}
