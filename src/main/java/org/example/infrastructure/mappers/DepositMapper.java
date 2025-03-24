package org.example.infrastructure.mappers;

import org.example.domain.models.Deposit;
import org.example.domain.models.enums.DepositType;
import org.example.infrastructure.db.entities.DepositEntity;

/**
 * Mapper class for converting domain {@link Deposit} objects to persistence {@link DepositEntity} objects.
 */
public class DepositMapper {

    /**
     * Converts a {@link Deposit} domain model and its {@link DepositType} to a {@link DepositEntity} for persistence.
     *
     * @param deposit the domain deposit object
     * @param type    the type of deposit (GIFT or MEAL)
     * @return a mapped DepositEntity object
     */
    public static DepositEntity toEntity(Deposit deposit, DepositType type) {
        DepositEntity entity = new DepositEntity();
        entity.setAmount(deposit.getAmount());
        entity.setDistributionDate(deposit.getDate());
        entity.setExpirationDate(deposit.getExpirationDate());
        entity.setType(type);
        return entity;
    }
}
