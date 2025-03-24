package org.example.application.impl.usecases;


import org.example.application.usecases.users.CreateUserUseCase;
import org.example.domain.models.User;
import org.example.domain.ports.output.UserRepository;
import org.example.infrastructure.mappers.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class CreateUserService implements CreateUserUseCase {

    private final UserRepository userRepository;

    public CreateUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(String name) {
        return UserMapper.toDomain(this.userRepository.save(new User(null, name)));
    }
}
