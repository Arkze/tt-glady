package org.example.application.impl.usecases;

import org.example.domain.models.Deposit;
import org.example.domain.models.GiftDeposit;
import org.example.domain.models.MealDeposit;
import org.example.domain.models.User;
import org.example.domain.models.enums.DepositType;
import org.example.domain.ports.output.DepositRepository;
import org.example.domain.ports.output.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class GetUserBalanceServiceTest {

    private DepositRepository depositRepository;
    private UserRepository userRepository;
    private GetUserBalanceService getUserBalanceService;

    @BeforeEach
    void setUp() {
        depositRepository = mock(DepositRepository.class);
        userRepository = mock(UserRepository.class); // ✅ Add this line
        getUserBalanceService = new GetUserBalanceService(depositRepository, userRepository);
    }


    @Test
    void shouldReturnTotalBalanceOfValidDeposits() {
        UUID userId = UUID.randomUUID();
        List<Deposit> deposits = List.of(
                new GiftDeposit(new BigDecimal("100"), LocalDate.now().minusDays(30)),
                new GiftDeposit(new BigDecimal("50"), LocalDate.now())
        );

        when(depositRepository.findAllForUser(userId)).thenReturn(deposits);
        when(userRepository.findById(userId)).thenReturn(Optional.of(new User(userId, "test-user")));

        BigDecimal result = getUserBalanceService.getBalance(userId);

        assertEquals(new BigDecimal("150"), result);
    }

    @Test
    void shouldExcludeExpiredDepositsFromBalance() {
        UUID userId = UUID.randomUUID();
        List<Deposit> deposits = List.of(
                new GiftDeposit(new BigDecimal("100"), LocalDate.now().minusYears(2)), // expired
                new GiftDeposit(new BigDecimal("50"), LocalDate.now()) // valid
        );

        when(depositRepository.findAllForUser(userId)).thenReturn(deposits);
        when(userRepository.findById(userId)).thenReturn(Optional.of(new User(userId, "test-user")));

        BigDecimal result = getUserBalanceService.getBalance(userId);

        assertEquals(new BigDecimal("50"), result);
    }


    @Test
    void shouldExcludeNonChosenDepositType() {
        UUID userId = UUID.randomUUID();
        List<Deposit> deposits = List.of(
                new MealDeposit(new BigDecimal("100"), LocalDate.now()), // expired
                new GiftDeposit(new BigDecimal("50"), LocalDate.now()) // valid
        );

        when(depositRepository.findAllForUser(userId)).thenReturn(deposits);
        when(userRepository.findById(userId)).thenReturn(Optional.of(new User(userId, "test-user")));

        BigDecimal result = getUserBalanceService.getBalance(userId, DepositType.GIFT);

        assertEquals(new BigDecimal("50"), result);
    }
}
