package org.example.domain.models;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Represents a company that can credit or debit funds and distribute deposits.
 */
public class Company {
    private final UUID id;
    private final String name;
    private BigDecimal balance;

    /**
     * Constructs a company with the specified ID, name, and initial balance.
     *
     * @param id      the unique identifier of the company
     * @param name    the name of the company
     * @param balance the initial balance of the company
     */
    public Company(UUID id, String name, BigDecimal balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    /**
     * @return the company ID
     */
    public UUID getId() { return id; }

    /**
     * @return the company name
     */
    public String getName() { return name; }

    /**
     * @return the current balance of the company
     */
    public BigDecimal getBalance() { return balance; }

    /**
     * Debits the specified amount from the company's balance.
     *
     * @param amount the amount to debit
     * @throws IllegalArgumentException if balance is insufficient
     */
    public void debit(BigDecimal amount) {
        if (balance.compareTo(amount) < 0) {
            throw new IllegalArgumentException("Insufficient balance");
        }
        balance = balance.subtract(amount);
    }

    /**
     * Credits the specified amount to the company's balance.
     *
     * @param amount the amount to credit
     * @throws IllegalArgumentException if the amount is negative
     */
    public void credit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Credit amount must be positive");
        }
        balance = balance.add(amount);
    }
}
