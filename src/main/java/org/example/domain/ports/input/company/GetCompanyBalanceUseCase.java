package org.example.domain.ports.input.company;

import java.math.BigDecimal;
import java.util.UUID;

public interface GetCompanyBalanceUseCase {
    BigDecimal getBalance(UUID companyId);
}
