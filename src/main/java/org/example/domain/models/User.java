package org.example.domain.models;

import java.util.*;

public class User {
    private final UUID id;
    private final String name;
    private final List<Deposit> deposits = new ArrayList<>();

    public User(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() { return id; }
    public String getName() { return name; }

    public void addDeposit(Deposit deposit) {
        deposits.add(deposit);
    }

    public List<Deposit> getDeposits() {
        return deposits;
    }
}
