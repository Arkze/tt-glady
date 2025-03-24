package org.example.domain.models;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Abstract base class representing a deposit given to a user.
 */
public abstract class Deposit {
    protected BigDecimal amount;
    protected LocalDate date;

    /**
     * Constructs a new deposit with the given amount and distribution date.
     *
     * @param amount the amount of the deposit
     * @param date   the distribution date of the deposit
     */
    public Deposit(BigDecimal amount, LocalDate date) {
        this.amount = amount;
        this.date = date;
    }

    /**
     * Returns the expiration date of the deposit.
     *
     * @return the expiration date
     */
    public abstract LocalDate getExpirationDate();

    /**
     * @return the amount of the deposit
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * @return the distribution date of the deposit
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Checks if the deposit is expired as of the given date.
     *
     * @param today the date to compare against
     * @return true if the deposit is expired, false otherwise
     */
    public boolean isExpired(LocalDate today) {
        return today.isAfter(this.getExpirationDate());
    }
}
