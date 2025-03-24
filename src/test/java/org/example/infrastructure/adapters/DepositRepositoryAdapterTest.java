package org.example.infrastructure.adapters;

import org.example.domain.models.Company;
import org.example.domain.models.GiftDeposit;
import org.example.domain.models.User;
import org.example.domain.models.enums.DepositType;
import org.example.infrastructure.db.entities.DepositEntity;
import org.example.infrastructure.db.repositories.DepositJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DepositRepositoryAdapterTest {

    private DepositJpaRepository depositJpaRepository;
    private DepositRepositoryAdapter adapter;

    @BeforeEach
    void setUp() {
        depositJpaRepository = mock(DepositJpaRepository.class);
        adapter = new DepositRepositoryAdapter(depositJpaRepository);
    }

    @Test
    void shouldSaveDeposit() {
        GiftDeposit deposit = new GiftDeposit(new BigDecimal("100"), LocalDate.now());
        User user = new User(UUID.randomUUID(), "David");
        Company company = new Company(UUID.randomUUID(), "Wedoogift", new BigDecimal("1000"));

        adapter.save(deposit, user, company, DepositType.GIFT);

        verify(depositJpaRepository).save(any(DepositEntity.class));
    }

    @Test
    void shouldFindAllDepositsForUser() {
        UUID userId = UUID.randomUUID();
        DepositEntity entity = new DepositEntity();
        entity.setType(DepositType.GIFT);
        entity.setAmount(new BigDecimal("50"));
        entity.setDistributionDate(LocalDate.now());
        entity.setExpirationDate(LocalDate.now().plusDays(365));

        when(depositJpaRepository.findAllByUserId(userId)).thenReturn(List.of(entity));

        List<?> result = adapter.findAllForUser(userId);
        assertEquals(1, result.size());
    }
}
