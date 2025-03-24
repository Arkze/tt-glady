package org.example.infrastructure.adapters;


import org.example.domain.models.Company;
import org.example.domain.ports.output.CompanyRepository;
import org.example.infrastructure.db.entities.CompanyEntity;
import org.example.infrastructure.db.repositories.CompanyJpaRepository;
import org.example.infrastructure.mappers.CompanyMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class CompanyRepositoryAdapter implements CompanyRepository {

    private final CompanyJpaRepository companyJpaRepository;

    public CompanyRepositoryAdapter(CompanyJpaRepository companyJpaRepository) {
        this.companyJpaRepository = companyJpaRepository;
    }

    @Override
    public Optional<Company> findById(UUID id) {
        return companyJpaRepository.findById(id).map(CompanyMapper::toDomain);
    }

    @Override
    public CompanyEntity save(Company company) {
        return companyJpaRepository.save(CompanyMapper.toEntity(company));
    }
}
