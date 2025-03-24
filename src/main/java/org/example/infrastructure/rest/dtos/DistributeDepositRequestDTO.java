package org.example.infrastructure.rest.dtos;

import org.example.domain.models.enums.DepositType;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * DTO representing a request to distribute a deposit from a company to a user.
 * This object is used in REST APIs to transfer the required information
 * for allocating a gift or meal deposit.
 *
 * @param companyId the UUID of the company providing the deposit
 * @param userId the UUID of the user receiving the deposit
 * @param amount the amount of the deposit
 * @param type the type of the deposit (e.g. GIFT or MEAL)
 */
public record DistributeDepositRequestDTO(
        UUID companyId,
        UUID userId,
        BigDecimal amount,
        DepositType type
) {}
