package org.example.domain.ports.input.company;


import java.math.BigDecimal;
import java.util.UUID;

public interface CreditCompanyBalanceUseCase {
    void credit(UUID companyId, BigDecimal amount);
}
