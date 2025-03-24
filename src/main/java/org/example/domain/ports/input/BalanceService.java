package org.example.domain.ports.input;

import java.math.BigDecimal;
import java.util.UUID;

public interface BalanceService {
    BigDecimal getUserBalance(UUID userId);
}

