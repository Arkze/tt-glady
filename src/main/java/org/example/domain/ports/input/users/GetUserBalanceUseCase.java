package org.example.domain.ports.input.users;


import java.math.BigDecimal;
import java.util.UUID;

public interface GetUserBalanceUseCase {
    BigDecimal getBalance(UUID userId);
}

