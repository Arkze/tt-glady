package org.example.application.impl.usecases;

import org.example.domain.exceptions.CompanyNotFoundException;
import org.example.domain.models.Company;
import org.example.domain.ports.output.CompanyRepository;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GetCompanyBalanceServiceTest {

    private final CompanyRepository companyRepository = mock(CompanyRepository.class);
    private final GetCompanyBalanceService service = new GetCompanyBalanceService(companyRepository);

    @Test
    void shouldReturnBalance() {
        UUID id = UUID.randomUUID();
        when(companyRepository.findById(id)).thenReturn(Optional.of(new Company(id, "TestCo", BigDecimal.TEN)));

        BigDecimal result = service.getBalance(id);

        assertEquals(BigDecimal.TEN, result);
    }

    @Test
    void shouldThrowWhenCompanyNotFound() {
        UUID id = UUID.randomUUID();
        when(companyRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(CompanyNotFoundException.class, () -> service.getBalance(id));
    }
}
