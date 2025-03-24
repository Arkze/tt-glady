package org.example.domain.ports.output;

import org.example.domain.models.Company;
import org.example.domain.models.Deposit;
import org.example.domain.models.User;
import org.example.domain.models.enums.DepositType;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Output port interface for managing deposits in the persistence layer.
 */
public interface DepositRepository {

    /**
     * Finds a deposit by its unique identifier.
     *
     * @param id the UUID of the deposit
     * @return an optional containing the deposit if found
     */
    Optional<Deposit> findById(UUID id);

    /**
     * Saves a new deposit for a user and company.
     *
     * @param deposit the deposit object
     * @param user the user receiving the deposit
     * @param company the company giving the deposit
     * @param type the type of the deposit (GIFT, MEAL, etc.)
     */
    void save(Deposit deposit, User user, Company company, DepositType type);

    /**
     * Retrieves all deposits assigned to a specific user.
     *
     * @param userId the UUID of the user
     * @return list of all deposits for the user
     */
    List<Deposit> findAllForUser(UUID userId);
}
