package org.example.infrastructure.db.repositories;


import org.example.infrastructure.db.entities.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CompanyJpaRepository extends JpaRepository<CompanyEntity, UUID> {}
