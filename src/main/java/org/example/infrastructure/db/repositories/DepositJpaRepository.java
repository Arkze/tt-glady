package org.example.infrastructure.db.repositories;

import org.example.infrastructure.db.entities.DepositEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * JPA repository interface for {@link DepositEntity}.
 * Provides basic CRUD operations and custom queries for deposits.
 */
public interface DepositJpaRepository extends JpaRepository<DepositEntity, UUID> {

    /**
     * Finds all deposits made to a specific user.
     *
     * @param userId the ID of the user
     * @return a list of deposits associated with the given user
     */
    List<DepositEntity> findAllByUserId(UUID userId);
}
