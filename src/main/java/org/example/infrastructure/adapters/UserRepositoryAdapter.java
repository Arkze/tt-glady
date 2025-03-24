package org.example.infrastructure.adapters;

import org.example.domain.models.User;
import org.example.domain.ports.output.UserRepository;
import org.example.infrastructure.db.entities.UserEntity;
import org.example.infrastructure.db.repositories.UserJpaRepository;
import org.example.infrastructure.mappers.UserMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class UserRepositoryAdapter implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    public UserRepositoryAdapter(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public Optional<User> findById(UUID id) {
        return userJpaRepository.findById(id).map(UserMapper::toDomain);
    }

    @Override
    public UserEntity save(User user) {
        return userJpaRepository.save(UserMapper.toEntity(user));
    }
}
