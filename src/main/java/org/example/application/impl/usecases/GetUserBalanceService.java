package org.example.application.impl.usecases;

import org.example.application.usecases.users.GetUserBalanceUseCase;
import org.example.domain.models.Deposit;
import org.example.domain.ports.output.DepositRepository;
import org.example.domain.ports.output.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class GetUserBalanceService implements GetUserBalanceUseCase {

    private final DepositRepository depositRepository;

    public GetUserBalanceService(DepositRepository depositRepository) {
        this.depositRepository = depositRepository;
    }

    @Override
    public BigDecimal getBalance(UUID userId) {
        return depositRepository.findAllForUser(userId).stream()
                .filter(deposit -> !deposit.isExpired(LocalDate.now()))
                .map(Deposit::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
