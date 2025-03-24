package org.example.domain.ports.output;

import org.example.domain.models.Company;
import org.example.domain.models.Deposit;
import org.example.domain.models.User;
import org.example.domain.models.enums.DepositType;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface DepositRepository {
    Optional<Deposit> findById(UUID id);
    void save(Deposit deposit, User user, Company company, DepositType type); // ✅ updated signature
    // ✅ ADD THIS:
    List<Deposit> findAllForUser(UUID userId);
}
