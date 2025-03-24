package org.example.domain.ports.input.users;

import org.example.application.dtos.DepositDto;

import java.util.List;
import java.util.UUID;

/**
 * Input port for retrieving all deposits associated with a user.
 */
public interface GetUserDepositsUseCase {

    /**
     * Retrieves all deposits for a given user.
     *
     * @param userId the UUID of the user
     * @return a list of deposit DTOs
     */
    List<DepositDto> getAll(UUID userId);
}
