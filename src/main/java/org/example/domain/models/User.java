package org.example.domain.models;

import java.util.*;

/**
 * Represents a user who can receive deposits.
 */
public class User {
    private final UUID id;
    private final String name;
    private final List<Deposit> deposits = new ArrayList<>();

    /**
     * Constructs a new user with the given ID and name.
     *
     * @param id   the unique identifier of the user
     * @param name the name of the user
     */
    public User(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * @return the unique identifier of the user
     */
    public UUID getId() { return id; }

    /**
     * @return the name of the user
     */
    public String getName() { return name; }

    /**
     * Adds a deposit to the user's list of deposits.
     *
     * @param deposit the deposit to add
     */
    public void addDeposit(Deposit deposit) {
        deposits.add(deposit);
    }

    /**
     * @return the list of all deposits the user has received
     */
    public List<Deposit> getDeposits() {
        return deposits;
    }
}
