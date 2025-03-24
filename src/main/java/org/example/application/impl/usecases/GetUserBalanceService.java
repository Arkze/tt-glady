package org.example.application.impl.usecases;

import org.example.domain.exceptions.DepositBadTypeException;
import org.example.domain.exceptions.UserNotFoundException;
import org.example.domain.models.GiftDeposit;
import org.example.domain.models.MealDeposit;
import org.example.domain.models.User;
import org.example.domain.models.enums.DepositType;
import org.example.domain.ports.input.users.GetUserBalanceUseCase;
import org.example.domain.models.Deposit;
import org.example.domain.ports.output.DepositRepository;
import org.example.domain.ports.output.UserRepository;
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
    private final UserRepository userRepository;

    /**
     * Constructs the service with the given deposit repository.
     *
     * @param depositRepository the deposit repository
     */
    public GetUserBalanceService(DepositRepository depositRepository, UserRepository userRepository) {
        this.depositRepository = depositRepository;
        this.userRepository = userRepository;
    }

    /**
     * Calculates the total balance of all valid (non-expired) deposits for a user.
     *
     * @param userId the ID of the user
     * @return the current balance
     * @throws UserNotFoundException if the user does not exist
     */
    @Override
    public BigDecimal getBalance(UUID userId) {
        User user = this.userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));
        return depositRepository.findAllForUser(userId).stream()
                .filter(deposit -> !deposit.isExpired(LocalDate.now()))
                .map(Deposit::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * Calculates the total balance of all valid (non-expired) deposits for a specific type for a user.
     *
     * @param userId the ID of the user
     * @param type   the type of deposit to include (GIFT or MEAL)
     * @return the total balance for the given deposit type
     * @throws UserNotFoundException if the user does not exist
     * @throws DepositBadTypeException if the deposit type is not supported
     */
    @Override
    public BigDecimal getBalance(UUID userId, DepositType type) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));

        if (type != DepositType.GIFT && type != DepositType.MEAL) {
            throw new DepositBadTypeException("Unsupported deposit type: " + type);
        }

        return depositRepository.findAllForUser(userId).stream()
                .filter(deposit -> !deposit.isExpired(LocalDate.now()))
                .filter(deposit -> {
                    boolean isGift = deposit instanceof GiftDeposit && type == DepositType.GIFT;
                    boolean isMeal = deposit instanceof MealDeposit && type == DepositType.MEAL;
                    return isGift || isMeal;
                })
                .map(Deposit::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
