package org.example.application.impl.usecases;

import org.example.domain.exceptions.CompanyNotFoundException;
import org.example.domain.models.Company;
import org.example.domain.ports.output.CompanyRepository;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CreditCompanyBalanceServiceTest {

    private final CompanyRepository companyRepository = mock(CompanyRepository.class);
    private final CreditCompanyBalanceService service = new CreditCompanyBalanceService(companyRepository);

    @Test
    void shouldCreditCompany() {
        UUID companyId = UUID.randomUUID();
        Company company = spy(new Company(companyId, "Co", BigDecimal.ZERO));

        when(companyRepository.findById(companyId)).thenReturn(Optional.of(company));

        service.credit(companyId, BigDecimal.TEN);

        verify(company).credit(BigDecimal.TEN);
        verify(companyRepository).save(company);
    }

    @Test
    void shouldThrowIfCompanyNotFound() {
        UUID id = UUID.randomUUID();
        when(companyRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(CompanyNotFoundException.class, () -> service.credit(id, BigDecimal.TEN));
    }
}
