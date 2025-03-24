package org.example.domain.models;

import java.math.BigDecimal;
import java.time.LocalDate;

public class GiftDeposit extends Deposit {
    public GiftDeposit(BigDecimal amount, LocalDate date) {
        super(amount, date);
    }

    @Override
    public LocalDate getExpirationDate() {
        return date.plusDays(365);
    }
}
