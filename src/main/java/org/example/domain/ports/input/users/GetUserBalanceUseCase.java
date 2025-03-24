package org.example.domain.ports.input.users;

import org.example.domain.models.enums.DepositType;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Input port for retrieving the balance of a user based on valid (non-expired) deposits.
 */
public interface GetUserBalanceUseCase {

    /**
     * Gets the total balance of all valid deposits for the given user.
     *
     * @param userId the UUID of the user
     * @return the total balance as BigDecimal
     */
    BigDecimal getBalance(UUID userId);

    /**
     * Gets the total balance of all valid deposits for the given user.
     *
     * @param userId the UUID of the user
     * @param type The Deposit Type balance to retrieve
     * @return the total balance as BigDecimal
     */
    BigDecimal getBalance(UUID userId, DepositType type);
}
