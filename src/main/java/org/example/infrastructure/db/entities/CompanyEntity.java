package org.example.infrastructure.db.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * JPA entity representing a company.
 * A company can distribute deposits to users.
 */
@Entity
public class CompanyEntity {
    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    private BigDecimal balance;

    /**
     * @return the unique identifier of the company
     */
    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * @return the name of the company
     */
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the current balance of the company
     */
    public BigDecimal getBalance() {
        return this.balance;
    }

    public void setBalance(BigDecimal amount) {
        this.balance = amount;
    }
}
