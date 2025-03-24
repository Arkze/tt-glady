package org.example.infrastructure.db.entities;

import jakarta.persistence.*;
import org.example.domain.models.enums.DepositType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

/**
 * JPA entity representing a deposit (gift or meal).
 * A deposit is associated with both a user and a company.
 */
@Entity
public class DepositEntity {
    @Id
    @GeneratedValue
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

    /**
     * @return the unique identifier of the deposit
     */
    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * @return the type of deposit (GIFT or MEAL)
     */
    public DepositType getType() {
        return this.type;
    }

    public void setType(DepositType type) {
        this.type = type;
    }

    /**
     * @return the monetary value of the deposit
     */
    public BigDecimal getAmount() {
        return this.amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * @return the date the deposit was made
     */
    public LocalDate getDistributionDate() {
        return distributionDate;
    }

    public void setDistributionDate(LocalDate distributionDate) {
        this.distributionDate = distributionDate;
    }

    /**
     * @return the expiration date of the deposit
     */
    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    /**
     * @return the user associated with this deposit
     */
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    /**
     * @return the company that distributed this deposit
     */
    public CompanyEntity getCompany() {
        return company;
    }

    public void setCompany(CompanyEntity company) {
        this.company = company;
    }
}
