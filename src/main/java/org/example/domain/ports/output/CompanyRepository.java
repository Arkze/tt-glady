package org.example.domain.ports.output;

import org.example.domain.models.Company;
import org.example.infrastructure.db.entities.CompanyEntity;

import java.util.Optional;
import java.util.UUID;

/**
 * Output port interface for accessing and persisting Company data.
 */
public interface CompanyRepository {

    /**
     * Finds a company by its unique identifier.
     *
     * @param id the UUID of the company
     * @return an optional containing the company if found
     */
    Optional<Company> findById(UUID id);

    /**
     * Saves a company to the persistence layer.
     *
     * @param company the company domain object to persist
     * @return the saved JPA entity
     */
    CompanyEntity save(Company company);
}
