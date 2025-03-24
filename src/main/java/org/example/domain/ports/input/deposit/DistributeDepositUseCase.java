package org.example.domain.ports.input.deposit;



import org.example.domain.models.enums.DepositType;

import java.math.BigDecimal;
import java.util.UUID;

public interface DistributeDepositUseCase {
    void distribute(UUID companyId, UUID userId, BigDecimal amount, DepositType type);
}

