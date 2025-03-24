package org.example.infrastructure.mappers;

import org.example.domain.models.Company;
import org.example.infrastructure.db.entities.CompanyEntity;

/**
 * Mapper class for converting between {@link Company} domain models and {@link CompanyEntity} persistence entities.
 */
public class CompanyMapper {

    /**
     * Converts a {@link CompanyEntity} to a {@link Company} domain object.
     *
     * @param entity the persistence entity to convert
     * @return the mapped domain Company object
     */
    public static Company toDomain(CompanyEntity entity) {
        return new Company(entity.getId(), entity.getName(), entity.getBalance());
    }

    /**
     * Converts a {@link Company} domain object to a {@link CompanyEntity} for persistence.
     *
     * @param company the domain object to convert
     * @return the mapped persistence CompanyEntity object
     */
    public static CompanyEntity toEntity(Company company) {
        CompanyEntity entity = new CompanyEntity();
        entity.setId(company.getId());
        entity.setName(company.getName());
        entity.setBalance(company.getBalance());
        return entity;
    }
}
