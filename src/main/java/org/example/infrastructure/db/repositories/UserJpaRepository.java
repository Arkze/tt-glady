package org.example.infrastructure.db.repositories;

import org.example.infrastructure.db.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * JPA repository interface for {@link UserEntity}.
 * Provides basic CRUD operations for users.
 */
public interface UserJpaRepository extends JpaRepository<UserEntity, UUID> {}
