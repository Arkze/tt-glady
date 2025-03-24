package org.example.domain.models;

import java.math.BigDecimal;
import java.util.UUID;

public class Company {
    private final UUID id;
    private final String name;
    private BigDecimal balance;

    public Company(UUID id, String name, BigDecimal balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    public UUID getId() { return id; }
    public String getName() { return name; }
    public BigDecimal getBalance() { return balance; }

    public void debit(BigDecimal amount) {
        if (balance.compareTo(amount) < 0) {
            throw new IllegalArgumentException("Insufficient balance");
        }
        balance = balance.subtract(amount);
    }

    public void credit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Credit amount must be positive");
        }
        balance = balance.add(amount);
    }
}