package org.example.domain.ports.input.users;

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
}
