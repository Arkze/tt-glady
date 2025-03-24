package org.example.infrastructure.mappers;


import org.example.domain.models.User;
import org.example.infrastructure.db.entities.UserEntity;

public class UserMapper {
    public static User toDomain(UserEntity entity) {
        return new User(entity.getId(), entity.getName());
    }

    public static UserEntity toEntity(User user) {
        UserEntity entity = new UserEntity();
        entity.setId(user.getId());
        entity.setName(user.getName());
        return entity;
    }
}

