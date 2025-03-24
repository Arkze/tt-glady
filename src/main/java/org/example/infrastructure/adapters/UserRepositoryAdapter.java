package org.example.infrastructure.adapters;

import org.example.domain.models.User;
import org.example.domain.ports.output.UserRepository;
import org.example.infrastructure.db.entities.UserEntity;
import org.example.infrastructure.db.repositories.UserJpaRepository;
import org.example.infrastructure.mappers.UserMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Adapter class implementing {@link UserRepository}, used to access user persistence.
 * Bridges the domain model with the JPA infrastructure.
 */
@Repository
public class UserRepositoryAdapter implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    public UserRepositoryAdapter(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    /**
     * Finds a user by its ID.
     *
     * @param id the UUID of the user
     * @return optional of the domain User if found
     */
    @Override
    public Optional<User> findById(UUID id) {
        return userJpaRepository.findById(id).map(UserMapper::toDomain);
    }

    /**
     * Saves a user into the database.
     *
     * @param user the domain user to save
     * @return the saved JPA entity
     */
    @Override
    public UserEntity save(User user) {
        return userJpaRepository.save(UserMapper.toEntity(user));
    }
}
