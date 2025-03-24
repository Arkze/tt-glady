package org.example.infrastructure.adapters;

import org.example.domain.models.Company;
import org.example.domain.ports.output.CompanyRepository;
import org.example.infrastructure.db.entities.CompanyEntity;
import org.example.infrastructure.db.repositories.CompanyJpaRepository;
import org.example.infrastructure.mappers.CompanyMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Adapter for company repository, responsible for saving and retrieving companies.
 */
@Repository
public class CompanyRepositoryAdapter implements CompanyRepository {

    private final CompanyJpaRepository companyJpaRepository;

    public CompanyRepositoryAdapter(CompanyJpaRepository companyJpaRepository) {
        this.companyJpaRepository = companyJpaRepository;
    }

    /**
     * Finds a company by its ID.
     *
     * @param id the UUID of the company
     * @return optional of the domain Company if found
     */
    @Override
    public Optional<Company> findById(UUID id) {
        return companyJpaRepository.findById(id).map(CompanyMapper::toDomain);
    }

    /**
     * Saves a company to the database.
     *
     * @param company the domain company
     * @return the saved JPA entity
     */
    @Override
    public CompanyEntity save(Company company) {
        return companyJpaRepository.save(CompanyMapper.toEntity(company));
    }
}
