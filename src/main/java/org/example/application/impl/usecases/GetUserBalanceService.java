package org.example.application.impl.usecases;

import org.example.domain.models.enums.DepositType;
import org.example.domain.ports.input.users.GetUserBalanceUseCase;
import org.example.domain.models.Deposit;
import org.example.domain.ports.output.DepositRepository;
import org.example.infrastructure.mappers.DepositMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

/**
 * Service for calculating the total non-expired balance of a user.
 */
@Service
public class GetUserBalanceService implements GetUserBalanceUseCase {

    private final DepositRepository depositRepository;

    /**
     * Constructs the service with the given deposit repository.
     *
     * @param depositRepository the deposit repository
     */
    public GetUserBalanceService(DepositRepository depositRepository) {
        this.depositRepository = depositRepository;
    }

    /**
     * Calculates the total balance of all valid (non-expired) deposits for a user.
     *
     * @param userId the ID of the user
     * @return the current balance
     */
    @Override
    public BigDecimal getBalance(UUID userId) {
        return depositRepository.findAllForUser(userId).stream()
                .filter(deposit -> !deposit.isExpired(LocalDate.now()))
                .map(Deposit::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * Calculates the total balance of all valid (non-expired) deposits for a user.
     *
     * @param userId the ID of the user
     * @return the current balance
     */
    @Override
    public BigDecimal getBalance(UUID userId, DepositType type) {
        return depositRepository.findAllForUser(userId).stream()
                .filter(deposit -> !deposit.isExpired(LocalDate.now()) && type.equals(DepositMapper.toEntity(deposit, type).getType()))
                .map(Deposit::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
