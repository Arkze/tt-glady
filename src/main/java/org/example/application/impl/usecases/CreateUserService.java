package org.example.application.impl.usecases;

import org.example.domain.ports.input.users.CreateUserUseCase;
import org.example.domain.models.User;
import org.example.domain.ports.output.UserRepository;
import org.example.infrastructure.mappers.UserMapper;
import org.springframework.stereotype.Service;

/**
 * Service implementation of {@link CreateUserUseCase}.
 * Handles logic for creating a new user.
 */
@Service
public class CreateUserService implements CreateUserUseCase {

    private final UserRepository userRepository;

    /**
     * Constructs the service with the required user repository.
     *
     * @param userRepository the user repository
     */
    public CreateUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Creates a new user with the provided name.
     *
     * @param name the user's name
     * @return the created user
     */
    @Override
    public User create(String name) {
        return UserMapper.toDomain(this.userRepository.save(new User(null, name)));
    }
}
