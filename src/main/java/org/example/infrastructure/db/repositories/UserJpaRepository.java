package org.example.infrastructure.db.repositories;

import org.example.infrastructure.db.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserJpaRepository extends JpaRepository<UserEntity, UUID> {}
