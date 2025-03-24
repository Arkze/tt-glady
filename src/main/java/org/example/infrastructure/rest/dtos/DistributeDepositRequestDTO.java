package org.example.infrastructure.rest.dtos;

import org.example.domain.models.enums.DepositType;

import java.math.BigDecimal;
import java.util.UUID;

public record DistributeDepositRequestDTO(
        UUID companyId,
        UUID userId,
        BigDecimal amount,
        DepositType type
) {}