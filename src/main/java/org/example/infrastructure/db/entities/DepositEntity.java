package org.example.infrastructure.db.entities;


import jakarta.persistence.*;
import org.example.domain.models.enums.DepositType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
public class DepositEntity {
    @Id @GeneratedValue
    private UUID id;

    @Enumerated(EnumType.STRING)
    private DepositType type;

    private BigDecimal amount;

    private LocalDate distributionDate;

    private LocalDate expirationDate;

    @ManyToOne
    private UserEntity user;

    @ManyToOne
    private CompanyEntity company;


    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public DepositType getType() {
        return this.type;
    }

    public void setType(DepositType type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getDistributionDate() {
        return distributionDate;
    }

    public void setDistributionDate(LocalDate distributionDate) {
        this.distributionDate = distributionDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public CompanyEntity getCompany() {
        return company;
    }

    public void setCompany(CompanyEntity company) {
        this.company = company;
    }

}

