package org.example.domain.ports.input.company;

import org.example.domain.models.Company;

public interface CreateCompanyUseCase {
    Company create(String name);
}

