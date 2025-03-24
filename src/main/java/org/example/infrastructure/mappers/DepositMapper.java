package org.example.infrastructure.mappers;


import org.example.domain.models.Deposit;
import org.example.domain.models.enums.DepositType;
import org.example.infrastructure.db.entities.DepositEntity;

public class DepositMapper {
    public static DepositEntity toEntity(Deposit deposit, DepositType type) {
        DepositEntity entity = new DepositEntity();
        entity.setAmount(deposit.getAmount());
        entity.setDistributionDate(deposit.getDate());
        entity.setExpirationDate(deposit.getExpirationDate());
        entity.setType(type);
        return entity;
    }
}