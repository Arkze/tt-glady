package org.example.application.impl.usecases;

import org.example.domain.models.User;
import org.example.domain.ports.output.UserRepository;
import org.example.infrastructure.mappers.UserMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateUserServiceTest {

    private final UserRepository userRepository = mock(UserRepository.class);
    private final CreateUserService service = new CreateUserService(userRepository);

    @Test
    void shouldCreateUser() {
        String name = "John";
        User expected = new User(null, name);
        when(userRepository.save(any())).thenReturn(UserMapper.toEntity(expected));

        User created = service.create(name);

        assertEquals(name, created.getName());
        verify(userRepository).save(any());
    }
}
