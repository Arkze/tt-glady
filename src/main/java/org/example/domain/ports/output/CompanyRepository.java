package org.example.domain.ports.output;

import org.example.domain.models.Company;
import org.example.infrastructure.db.entities.CompanyEntity;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

public interface CompanyRepository {
    Optional<Company> findById(UUID id);
    CompanyEntity save(Company company);
}

