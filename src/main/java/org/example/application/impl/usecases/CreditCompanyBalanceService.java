package org.example.application.impl.usecases;

import org.example.domain.exceptions.CompanyNotFoundException;
import org.example.domain.ports.input.company.CreditCompanyBalanceUseCase;
import org.example.domain.models.Company;
import org.example.domain.ports.output.CompanyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Service implementation of {@link CreditCompanyBalanceUseCase}.
 * Handles logic for crediting a company's balance.
 */
@Service
public class CreditCompanyBalanceService implements CreditCompanyBalanceUseCase {

    private final CompanyRepository companyRepository;

    /**
     * Constructs the service with the required company repository.
     *
     * @param companyRepository the company repository
     */
    public CreditCompanyBalanceService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    /**
     * Credits the specified amount to the company's balance.
     *
     * @param companyId the UUID of the company
     * @param amount    the amount to credit
     * @throws CompanyNotFoundException if the amount is negative
     */
    @Override
    @Transactional
    public void credit(UUID companyId, BigDecimal amount) {
        Company company = this.companyRepository.findById(companyId)
                .orElseThrow(() -> new CompanyNotFoundException("Company not found with id: " + companyId));

        company.credit(amount);
        companyRepository.save(company);
    }
}
