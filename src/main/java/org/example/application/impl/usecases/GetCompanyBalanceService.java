package org.example.application.impl.usecases;
import org.example.application.usecases.company.GetCompanyBalanceUseCase;
import org.example.domain.exceptions.CompanyNotFoundException;
import org.example.domain.ports.output.CompanyRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class GetCompanyBalanceService implements GetCompanyBalanceUseCase {

    private final CompanyRepository companyRepository;

    public GetCompanyBalanceService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public BigDecimal getBalance(UUID companyId) {
        return companyRepository.findById(companyId)
                .orElseThrow(() -> new CompanyNotFoundException("Company not found"))
                .getBalance();
    }

}