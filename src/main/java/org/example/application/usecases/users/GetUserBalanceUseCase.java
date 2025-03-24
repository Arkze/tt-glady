package org.example.application.usecases.users;


import java.math.BigDecimal;
import java.util.UUID;

public interface GetUserBalanceUseCase {
    BigDecimal getBalance(UUID userId);
}

