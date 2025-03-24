package org.example.application.impl.usecases;

import org.example.domain.ports.input.company.CreateCompanyUseCase;
import org.example.domain.models.Company;
import org.example.domain.ports.output.CompanyRepository;
import org.example.infrastructure.mappers.CompanyMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * Service implementation of {@link CreateCompanyUseCase}.
 * Handles logic for creating a new company with an initial balance.
 */
@Service
public class CreateCompanyService implements CreateCompanyUseCase {

    private final CompanyRepository companyRepository;

    /**
     * Constructs the service with the required company repository.
     *
     * @param companyRepository the company repository
     */
    public CreateCompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    /**
     * Creates a new company with a zero balance.
     *
     * @param name the company's name
     * @return the created company
     */
    @Override
    @Transactional
    public Company create(String name) {
        return CompanyMapper.toDomain(this.companyRepository.save(new Company(null, name, new BigDecimal(0))));
    }
}
