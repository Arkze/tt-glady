package org.example.application.impl.usecases;

import org.example.domain.exceptions.UserNotFoundException;
import org.example.domain.models.GiftDeposit;
import org.example.domain.models.User;
import org.example.domain.ports.output.DepositRepository;
import org.example.domain.ports.output.UserRepository;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GetUserDepositsServiceTest {

    private final DepositRepository depositRepository = mock(DepositRepository.class);
    private final UserRepository userRepository = mock(UserRepository.class);

    private final GetUserDepositsService service = new GetUserDepositsService(depositRepository, userRepository);

    @Test
    void shouldReturnDepositsForUser() {
        UUID userId = UUID.randomUUID();
        when(userRepository.findById(userId)).thenReturn(Optional.of(new User(userId, "Test")));
        when(depositRepository.findAllForUser(userId)).thenReturn(List.of(new GiftDeposit(BigDecimal.TEN, LocalDate.now())));

        var result = service.getAll(userId);

        assertEquals(1, result.size());
        verify(userRepository).findById(userId);
    }

    @Test
    void shouldThrowIfUserNotFound() {
        UUID userId = UUID.randomUUID();
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> service.getAll(userId));
    }
}
