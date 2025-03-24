package org.example.infrastructure.db.entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

/**
 * JPA entity representing a user in the system.
 * A user can receive multiple deposits.
 */
@Entity
public class UserEntity {
    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<DepositEntity> deposits;

    /**
     * @return the unique identifier of the user
     */
    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * @return the name of the user
     */
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return list of deposits assigned to the user
     */
    public List<DepositEntity> getDeposits() {
        return this.deposits;
    }
}
