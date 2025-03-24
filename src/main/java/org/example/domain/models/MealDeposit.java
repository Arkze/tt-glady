package org.example.domain.models;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MealDeposit extends Deposit {
    public MealDeposit(BigDecimal amount, LocalDate date) {
        super(amount, date);
    }

    @Override
    public LocalDate getExpirationDate() {
        return LocalDate.of(date.getYear() + 1, 2, 28);
    }
}