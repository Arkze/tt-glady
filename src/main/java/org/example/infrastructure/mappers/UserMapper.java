package org.example.infrastructure.mappers;

import org.example.domain.models.User;
import org.example.infrastructure.db.entities.UserEntity;

/**
 * Mapper class for converting between User domain models and UserEntity JPA entities.
 */
public class UserMapper {

    /**
     * Converts a {@link UserEntity} (from the database layer) to a {@link User} domain model.
     *
     * @param entity the UserEntity to convert
     * @return a User domain object
     */
    public static User toDomain(UserEntity entity) {
        return new User(entity.getId(), entity.getName());
    }

    /**
     * Converts a {@link User} domain model to a {@link UserEntity} suitable for persistence.
     *
     * @param user the User domain object to convert
     * @return a UserEntity object
     */
    public static UserEntity toEntity(User user) {
        UserEntity entity = new UserEntity();
        entity.setId(user.getId());
        entity.setName(user.getName());
        return entity;
    }
}
