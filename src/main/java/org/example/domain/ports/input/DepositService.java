package org.example.domain.ports.input;

import java.math.BigDecimal;
import java.util.UUID;

public interface DepositService {
    void distributeGift(UUID companyId, UUID userId, BigDecimal amount);
    void distributeMeal(UUID companyId, UUID userId, BigDecimal amount);
}