package com.microservices.eazybank.Accounts.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("auditAwareImpl")
public class AuditAwareImpl implements AuditorAware<String> {
    /**
     * @return Optional<String>
     */
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("Accounts_MicroService");
    }
}
