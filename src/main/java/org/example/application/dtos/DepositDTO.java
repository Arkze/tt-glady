package org.example.application.dtos;

import org.example.domain.models.enums.DepositStatus;
import org.example.domain.models.enums.DepositType;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Data Transfer Object representing a deposit given to a user.
 * It encapsulates deposit information such as the amount, type, dates, and current status.
 *
 * @param amount           the value of the deposit
 * @param type             the type of the deposit (e.g., GIFT, MEAL)
 * @param distributionDate the date the deposit was issued
 * @param expirationDate   the date when the deposit will expire
 * @param status           the current status of the deposit (VALID or EXPIRED)
 */
public record DepositDTO(
        BigDecimal amount,
        DepositType type,
        LocalDate distributionDate,
        LocalDate expirationDate,
        DepositStatus status
) {}
