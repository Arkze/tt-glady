package org.example.application.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.example.domain.models.enums.DepositStatus;
import org.example.domain.models.enums.DepositType;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DepositDto(
        BigDecimal amount,
        DepositType type,
        LocalDate distributionDate,
        LocalDate expirationDate,
        DepositStatus status
) {


}
