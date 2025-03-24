package org.example.infrastructure.db.repositories;

import org.example.infrastructure.db.entities.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * JPA repository interface for {@link CompanyEntity}.
 * Provides basic CRUD operations for companies.
 */
public interface CompanyJpaRepository extends JpaRepository<CompanyEntity, UUID> {}
