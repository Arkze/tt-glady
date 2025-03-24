package org.example.application.impl.usecases;

import org.example.domain.models.Company;
import org.example.domain.ports.output.CompanyRepository;
import org.example.infrastructure.mappers.CompanyMapper;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateCompanyServiceTest {

    private final CompanyRepository companyRepository = mock(CompanyRepository.class);
    private final CreateCompanyService service = new CreateCompanyService(companyRepository);

    @Test
    void shouldCreateCompany() {
        String name = "Comp";
        Company comp = new Company(null, name, BigDecimal.ZERO);
        when(companyRepository.save(any())).thenReturn(CompanyMapper.toEntity(comp));

        Company created = service.create(name);

        assertEquals(name, created.getName());
        verify(companyRepository).save(any());
    }
}
