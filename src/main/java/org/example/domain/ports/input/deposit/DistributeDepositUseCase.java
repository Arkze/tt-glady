package org.example.domain.ports.input.deposit;

import org.example.domain.models.enums.DepositType;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Input port for distributing a deposit from a company to a user.
 */
public interface DistributeDepositUseCase {

    /**
     * Distributes a deposit to a user from a company.
     *
     * @param companyId the ID of the company issuing the deposit
     * @param userId the ID of the user receiving the deposit
     * @param amount the amount to deposit
     * @param type the type of deposit (GIFT or MEAL)
     */
    void distribute(UUID companyId, UUID userId, BigDecimal amount, DepositType type);
}
