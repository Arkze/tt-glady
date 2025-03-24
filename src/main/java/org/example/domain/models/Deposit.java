package org.example.domain.models;

import java.math.BigDecimal;
import java.time.LocalDate;

public abstract class Deposit {
    protected BigDecimal amount;
    protected LocalDate date;

    public Deposit(BigDecimal amount, LocalDate date) {
        this.amount = amount;
        this.date = date;
    }

    public abstract LocalDate getExpirationDate();

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public boolean isExpired(LocalDate today) {
        return today.isAfter(this.getExpirationDate());
    }
}
