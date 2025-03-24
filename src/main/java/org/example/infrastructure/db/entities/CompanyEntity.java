package org.example.infrastructure.db.entities;


import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
public class CompanyEntity {
    @Id @GeneratedValue
    private UUID id;

    private String name;

    private java.math.BigDecimal balance;

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    public void setBalance(BigDecimal amount) {
        this.balance = amount;
    }
}

