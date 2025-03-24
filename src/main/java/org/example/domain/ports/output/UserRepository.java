package org.example.domain.ports.output;

import org.example.domain.models.User;
import org.example.infrastructure.db.entities.UserEntity;

import java.util.Optional;
import java.util.UUID;

/**
 * Output port interface for managing users in the persistence layer.
 */
public interface UserRepository {

    /**
     * Finds a user by its unique identifier.
     *
     * @param id the UUID of the user
     * @return an optional containing the user if found
     */
    Optional<User> findById(UUID id);

    /**
     * Saves a user to the persistence layer.
     *
     * @param user the user domain object to persist
     * @return the saved JPA entity
     */
    UserEntity save(User user);
}
