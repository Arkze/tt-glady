package org.example.application.impl.usecases;

import org.example.domain.ports.input.company.GetCompanyBalanceUseCase;
import org.example.domain.exceptions.CompanyNotFoundException;
import org.example.domain.ports.output.CompanyRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Service for retrieving the balance of a company.
 */
@Service
public class GetCompanyBalanceService implements GetCompanyBalanceUseCase {

    private final CompanyRepository companyRepository;

    /**
     * Constructs the service with the given company repository.
     *
     * @param companyRepository the company repository
     */
    public GetCompanyBalanceService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    /**
     * Gets the current balance of a company.
     *
     * @param companyId the ID of the company
     * @return the current company balance
     * @throws CompanyNotFoundException if the company does not exist
     */
    @Override
    public BigDecimal getBalance(UUID companyId) {
        return companyRepository.findById(companyId)
                .orElseThrow(() -> new CompanyNotFoundException("Company not found"))
                .getBalance();
    }
}
