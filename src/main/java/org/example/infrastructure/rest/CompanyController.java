package org.example.infrastructure.rest;


import org.example.domain.ports.input.company.CreateCompanyUseCase;
import org.example.domain.ports.input.company.CreditCompanyBalanceUseCase;
import org.example.domain.ports.input.company.GetCompanyBalanceUseCase;
import org.example.domain.models.Company;
import org.example.infrastructure.rest.dtos.CreditBalanceRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    private final GetCompanyBalanceUseCase getCompanyBalance;
    private final CreateCompanyUseCase createCompany;
    private final CreditCompanyBalanceUseCase creditCompanyBalanceUseCase;

    public CompanyController(GetCompanyBalanceUseCase getCompanyBalance, CreateCompanyUseCase createCompany, CreditCompanyBalanceUseCase creditCompanyBalanceUseCase) {
        this.getCompanyBalance = getCompanyBalance;
        this.createCompany = createCompany;
        this.creditCompanyBalanceUseCase = creditCompanyBalanceUseCase;
    }

    @PostMapping
    public ResponseEntity<Company> createCompany(@RequestParam String name) {
        Company company = createCompany.create(name);
        return ResponseEntity.ok(company);
    }


    @PostMapping("/{companyId}/credit")
    public ResponseEntity<BigDecimal> creditBalance(
            @PathVariable UUID companyId,
            @RequestBody CreditBalanceRequestDTO request
    ) {
        creditCompanyBalanceUseCase.credit(companyId, request.amount());
        return ResponseEntity.ok(getCompanyBalance.getBalance(companyId));
    }

    @GetMapping("/{companyId}/balance")
    public ResponseEntity<BigDecimal> getBalance(@PathVariable UUID companyId) {
        return ResponseEntity.ok(getCompanyBalance.getBalance(companyId));
    }
}
