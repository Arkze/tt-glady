package org.example.domain.models;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Represents a meal deposit, which expires on February 28 of the following year.
 */
public class MealDeposit extends Deposit {

    /**
     * Constructs a meal deposit with the specified amount and date.
     *
     * @param amount the deposit amount
     * @param date   the distribution date
     */
    public MealDeposit(BigDecimal amount, LocalDate date) {
        super(amount, date);
    }

    /**
     * @return the expiration date of the meal deposit (February 28 of next year)
     */
    @Override
    public LocalDate getExpirationDate() {
        return LocalDate.of(date.getYear() + 1, 2, 28);
    }
}
