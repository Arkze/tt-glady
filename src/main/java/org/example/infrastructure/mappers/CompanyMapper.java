package org.example.infrastructure.mappers;


import org.example.domain.models.Company;
import org.example.infrastructure.db.entities.CompanyEntity;

public class CompanyMapper {
    public static Company toDomain(CompanyEntity entity) {
        return new Company(entity.getId(), entity.getName(), entity.getBalance());
    }

    public static CompanyEntity toEntity(Company company) {
        CompanyEntity entity = new CompanyEntity();
        entity.setId(company.getId());
        entity.setName(company.getName());
        entity.setBalance(company.getBalance());
        return entity;
    }
}

