package org.example.infrastructure.rest;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Companies", description = "Company related operations")
public class CompanyController {

    private final GetCompanyBalanceUseCase getCompanyBalance;
    private final CreateCompanyUseCase createCompany;
    private final CreditCompanyBalanceUseCase creditCompanyBalanceUseCase;

    public CompanyController(GetCompanyBalanceUseCase getCompanyBalance, CreateCompanyUseCase createCompany, CreditCompanyBalanceUseCase creditCompanyBalanceUseCase) {
        this.getCompanyBalance = getCompanyBalance;
        this.createCompany = createCompany;
        this.creditCompanyBalanceUseCase = creditCompanyBalanceUseCase;
    }


    @Operation(summary = "Create a new company", responses = {
            @ApiResponse(responseCode = "200", description = "Company created"),
            @ApiResponse(responseCode = "500", description = "Internal error")
    })
    @PostMapping
    public ResponseEntity<Company> createCompany(@RequestParam String name) {
        Company company = createCompany.create(name);
        return ResponseEntity.ok(company);
    }

    @Operation(summary = "Credit a company's balance", responses = {
            @ApiResponse(responseCode = "200", description = "Balance updated"),
            @ApiResponse(responseCode = "404", description = "Company not found"),
            @ApiResponse(responseCode = "500", description = "Internal error")
    })
    @PostMapping("/{companyId}/credit")
    public ResponseEntity<BigDecimal> creditBalance(
            @PathVariable UUID companyId,
            @RequestBody CreditBalanceRequestDTO request
    ) {
        creditCompanyBalanceUseCase.credit(companyId, request.amount());
        return ResponseEntity.ok(getCompanyBalance.getBalance(companyId));
    }

    @Operation(summary = "Get company balance", responses = {
            @ApiResponse(responseCode = "200", description = "Balance returned"),
            @ApiResponse(responseCode = "404", description = "Company not found"),
            @ApiResponse(responseCode = "500", description = "Internal error")
    })
    @GetMapping("/{companyId}/balance")
    public ResponseEntity<BigDecimal> getBalance(@PathVariable UUID companyId) {
        return ResponseEntity.ok(getCompanyBalance.getBalance(companyId));
    }
}
