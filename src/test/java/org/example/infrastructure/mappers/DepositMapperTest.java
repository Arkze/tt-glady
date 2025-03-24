package org.example.infrastructure.mappers;

import org.example.domain.models.GiftDeposit;
import org.example.domain.models.MealDeposit;
import org.example.domain.models.enums.DepositType;
import org.example.infrastructure.db.entities.DepositEntity;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DepositMapperTest {

    @Test
    void toEntityShouldMapGiftDepositCorrectly() {
        GiftDeposit deposit = new GiftDeposit(new BigDecimal("100"), LocalDate.of(2024, 1, 1));
        DepositEntity entity = DepositMapper.toEntity(deposit, DepositType.GIFT);

        assertEquals(deposit.getAmount(), entity.getAmount());
        assertEquals(deposit.getDate(), entity.getDistributionDate());
        assertEquals(deposit.getExpirationDate(), entity.getExpirationDate());
        assertEquals(DepositType.GIFT, entity.getType());
    }

    @Test
    void toEntityShouldMapMealDepositCorrectly() {
        MealDeposit deposit = new MealDeposit(new BigDecimal("50"), LocalDate.of(2024, 3, 1));
        DepositEntity entity = DepositMapper.toEntity(deposit, DepositType.MEAL);

        assertEquals(deposit.getAmount(), entity.getAmount());
        assertEquals(deposit.getDate(), entity.getDistributionDate());
        assertEquals(deposit.getExpirationDate(), entity.getExpirationDate());
        assertEquals(DepositType.MEAL, entity.getType());
    }
}

