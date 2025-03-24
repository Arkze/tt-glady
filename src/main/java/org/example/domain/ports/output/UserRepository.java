package org.example.domain.ports.output;

import org.example.domain.models.User;
import org.example.infrastructure.db.entities.UserEntity;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    Optional<User> findById(UUID id);
    UserEntity save(User user);
}