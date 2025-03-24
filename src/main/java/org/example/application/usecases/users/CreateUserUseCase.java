package org.example.application.usecases.users;

import org.example.domain.models.User;

public interface CreateUserUseCase {
    User create(String name);
}

