package org.example.domain.ports.input.company;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Input port for crediting (adding funds to) a company's balance.
 */
public interface CreditCompanyBalanceUseCase {

    /**
     * Adds the specified amount to the company's balance.
     *
     * @param companyId the ID of the company
     * @param amount the amount to credit
     */
    void credit(UUID companyId, BigDecimal amount);
}
