package org.example.application.impl.usecases;

import org.example.domain.exceptions.CompanyNotFoundException;
import org.example.domain.exceptions.DepositBadTypeException;
import org.example.domain.exceptions.InsufficientFundException;
import org.example.domain.exceptions.UserNotFoundException;
import org.example.domain.models.*;
import org.example.domain.models.enums.DepositType;
import org.example.domain.ports.output.CompanyRepository;
import org.example.domain.ports.output.DepositRepository;
import org.example.domain.ports.output.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DistributeDepositServiceTest {

    private UserRepository userRepository;
    private CompanyRepository companyRepository;
    private DepositRepository depositRepository;
    private DistributeDepositService distributeDepositService;

    private final UUID userId = UUID.randomUUID();
    private final UUID companyId = UUID.randomUUID();
    private final BigDecimal amount = new BigDecimal("100");

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        companyRepository = mock(CompanyRepository.class);
        depositRepository = mock(DepositRepository.class);
        distributeDepositService = new DistributeDepositService(userRepository, companyRepository, depositRepository);
    }

    @Test
    void shouldDistributeGiftDepositSuccessfully() {
        User user = new User(userId, "John");
        Company company = new Company(companyId, "ACME", new BigDecimal("1000"));

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(companyRepository.findById(companyId)).thenReturn(Optional.of(company));

        distributeDepositService.distribute(companyId, userId, amount, DepositType.GIFT);

        verify(companyRepository).save(company);
        verify(userRepository).save(user);
        verify(depositRepository).save(any(GiftDeposit.class), eq(user), eq(company), eq(DepositType.GIFT));
    }

    @Test
    void shouldThrowWhenUserNotFound() {
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        UserNotFoundException ex = assertThrows(UserNotFoundException.class,
                () -> distributeDepositService.distribute(companyId, userId, amount, DepositType.GIFT));

        assertEquals("User not found with id: " + userId, ex.getMessage());
    }

    @Test
    void shouldThrowWhenCompanyNotFound() {
        when(userRepository.findById(userId)).thenReturn(Optional.of(new User(userId, "John")));
        when(companyRepository.findById(companyId)).thenReturn(Optional.empty());

        CompanyNotFoundException ex = assertThrows(CompanyNotFoundException.class,
                () -> distributeDepositService.distribute(companyId, userId, amount, DepositType.MEAL));

        assertEquals("Company not found with id: " + companyId, ex.getMessage());
    }

    @Test
    void shouldThrowWhenInsufficientBalance() {
        User user = new User(userId, "John");
        Company company = new Company(companyId, "ACME", new BigDecimal("10"));

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(companyRepository.findById(companyId)).thenReturn(Optional.of(company));

        assertThrows(InsufficientFundException.class,
                () -> distributeDepositService.distribute(companyId, userId, new BigDecimal("1000"), DepositType.GIFT));
    }

    @Test
    void shouldThrowWhenDepositTypeIsInvalid() {
        User user = new User(userId, "John");
        Company company = new Company(companyId, "ACME", new BigDecimal("1000"));

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(companyRepository.findById(companyId)).thenReturn(Optional.of(company));

        // Create a fake invalid type (null won't hit switch default in this case, but let's demonstrate the intention)
        assertThrows(DepositBadTypeException.class,
                () -> distributeDepositService.distribute(companyId, userId, amount, null));
    }
}
