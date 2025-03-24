package org.example.application.impl.usecases;


import org.example.application.usecases.company.CreateCompanyUseCase;
import org.example.domain.models.Company;
import org.example.domain.ports.output.CompanyRepository;
import org.example.infrastructure.mappers.CompanyMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CreateCompanyService implements CreateCompanyUseCase {

    private final CompanyRepository companyRepository;

    public CreateCompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public Company create(String name) {
        return CompanyMapper.toDomain(this.companyRepository.save(new Company(null, name, new BigDecimal(0))));
    }
}
