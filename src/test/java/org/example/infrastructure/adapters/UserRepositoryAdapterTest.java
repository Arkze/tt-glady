package org.example.infrastructure.adapters;

import org.example.domain.models.User;
import org.example.infrastructure.db.entities.UserEntity;
import org.example.infrastructure.db.repositories.UserJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserRepositoryAdapterTest {

    private UserJpaRepository userJpaRepository;
    private UserRepositoryAdapter adapter;

    @BeforeEach
    void setUp() {
        userJpaRepository = mock(UserJpaRepository.class);
        adapter = new UserRepositoryAdapter(userJpaRepository);
    }

    @Test
    void shouldFindUserById() {
        UUID id = UUID.randomUUID();
        UserEntity entity = new UserEntity();
        entity.setId(id);
        entity.setName("Test");

        when(userJpaRepository.findById(id)).thenReturn(Optional.of(entity));

        Optional<User> result = adapter.findById(id);

        assertTrue(result.isPresent());
        assertEquals("Test", result.get().getName());
    }

    @Test
    void shouldSaveUser() {
        User user = new User(UUID.randomUUID(), "David");

        adapter.save(user);

        verify(userJpaRepository, times(1)).save(any(UserEntity.class));
    }
}
