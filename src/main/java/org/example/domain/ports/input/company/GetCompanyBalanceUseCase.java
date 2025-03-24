package org.example.domain.ports.input.company;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Input port for retrieving the current balance of a company.
 */
public interface GetCompanyBalanceUseCase {

    /**
     * Retrieves the balance for the specified company.
     *
     * @param companyId the UUID of the company
     * @return the balance as BigDecimal
     */
    BigDecimal getBalance(UUID companyId);
}
