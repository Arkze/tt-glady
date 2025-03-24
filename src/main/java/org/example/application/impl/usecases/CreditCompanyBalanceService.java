package org.example.application.impl.usecases;

import org.example.application.usecases.company.CreditCompanyBalanceUseCase;
import org.example.domain.models.Company;
import org.example.domain.ports.output.CompanyRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class CreditCompanyBalanceService implements CreditCompanyBalanceUseCase {

    private final CompanyRepository companyRepository;

    public CreditCompanyBalanceService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public void credit(UUID companyId, BigDecimal amount) {
        Company company = this.companyRepository.findById(companyId).orElseThrow();
        company.credit(amount);
        companyRepository.save(company);
    }
}

