package org.example.infrastructure.db.repositories;


import org.example.infrastructure.db.entities.DepositEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DepositJpaRepository extends JpaRepository<DepositEntity, UUID> {
    List<DepositEntity> findAllByUserId(UUID userId);
}
