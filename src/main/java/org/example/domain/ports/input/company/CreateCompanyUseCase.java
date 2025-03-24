package org.example.domain.ports.input.company;

import org.example.domain.models.Company;

/**
 * Input port for creating a new company.
 */
public interface CreateCompanyUseCase {

    /**
     * Creates a new company with the given name.
     *
     * @param name the name of the company
     * @return the created company domain object
     */
    Company create(String name);
}
