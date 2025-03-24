package org.example.application.impl.services;



import org.example.domain.ports.input.BalanceService;
import org.example.domain.ports.output.UserRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class BalanceServiceImpl implements BalanceService {
    private final UserRepository userRepository;

    public BalanceServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public BigDecimal getUserBalance(UUID userId) {
        return userRepository.findById(userId).orElseThrow()
                .getDeposits().stream()
                .filter(d -> !d.isExpired(LocalDate.now()))
                .map(d -> d.getAmount())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}

