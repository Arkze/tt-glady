package org.example.domain.ports.input.users;

import org.example.domain.models.User;

/**
 * Input port for creating a new user.
 */
public interface CreateUserUseCase {

    /**
     * Creates a new user with the specified name.
     *
     * @param name the name of the user
     * @return the created user domain model
     */
    User create(String name);
}
