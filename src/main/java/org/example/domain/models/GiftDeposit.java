package org.example.domain.models;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Represents a gift deposit, which expires 365 days after distribution.
 */
public class GiftDeposit extends Deposit {

    /**
     * Constructs a gift deposit with the specified amount and date.
     *
     * @param amount the deposit amount
     * @param date   the distribution date
     */
    public GiftDeposit(BigDecimal amount, LocalDate date) {
        super(amount, date);
    }

    /**
     * @return the expiration date of the gift deposit (365 days after distribution)
     */
    @Override
    public LocalDate getExpirationDate() {
        return date.plusDays(365);
    }
}
