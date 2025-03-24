package org.example.infrastructure.adapters;

import org.example.domain.models.Company;
import org.example.infrastructure.db.entities.CompanyEntity;
import org.example.infrastructure.db.repositories.CompanyJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CompanyRepositoryAdapterTest {

    private CompanyJpaRepository companyJpaRepository;
    private CompanyRepositoryAdapter adapter;

    @BeforeEach
    void setUp() {
        companyJpaRepository = mock(CompanyJpaRepository.class);
        adapter = new CompanyRepositoryAdapter(companyJpaRepository);
    }

    @Test
    void shouldFindCompanyById() {
        UUID id = UUID.randomUUID();
        CompanyEntity entity = new CompanyEntity();
        entity.setId(id);
        entity.setName("Company");
        entity.setBalance(new BigDecimal("1000"));

        when(companyJpaRepository.findById(id)).thenReturn(Optional.of(entity));

        Optional<Company> result = adapter.findById(id);

        assertTrue(result.isPresent());
        assertEquals("Company", result.get().getName());
    }

    @Test
    void shouldSaveCompany() {
        Company company = new Company(UUID.randomUUID(), "C1", new BigDecimal("50"));
        adapter.save(company);
        verify(companyJpaRepository).save(any(CompanyEntity.class));
    }
}
