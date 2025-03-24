package org.example.domain.ports.input.users;

import org.example.domain.models.User;

public interface CreateUserUseCase {
    User create(String name);
}

