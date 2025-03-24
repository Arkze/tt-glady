package org.example.application.usecases.company;

import org.example.domain.models.Company;

public interface CreateCompanyUseCase {
    Company create(String name);
}

