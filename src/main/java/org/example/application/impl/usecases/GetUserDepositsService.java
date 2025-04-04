package org.example.application.impl.usecases;

import org.example.application.dtos.DepositDTO;
import org.example.domain.exceptions.UserNotFoundException;
import org.example.domain.models.User;
import org.example.domain.ports.input.users.GetUserDepositsUseCase;
import org.example.domain.models.GiftDeposit;
import org.example.domain.models.enums.DepositStatus;
import org.example.domain.models.enums.DepositType;
import org.example.domain.ports.output.DepositRepository;
import org.example.domain.ports.output.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * Service for retrieving all deposits for a user.
 */
@Service
public class GetUserDepositsService implements GetUserDepositsUseCase {

    private final DepositRepository depositRepository;
    private final UserRepository userRepository;

    /**
     * Constructs the service with the given deposit repository.
     *
     * @param depositRepository the deposit repository
     */
    public GetUserDepositsService(DepositRepository depositRepository, UserRepository userRepository) {
        this.depositRepository = depositRepository;
        this.userRepository = userRepository;
    }


    /**
     * Retrieves all deposits for the given user ID, converting them into DTOs.
     *
     * @param userId the ID of the user
     * @return a list of deposit DTOs
     * @throws UserNotFoundException if the user does not exist
     */
    @Override
    public List<DepositDTO> getAll(UUID userId) {
        User user = this.userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));
        return this.depositRepository.findAllForUser(userId).stream().map(deposit -> {
            DepositType type = (deposit instanceof GiftDeposit) ? DepositType.GIFT : DepositType.MEAL;
            return new DepositDTO(
                    deposit.getAmount(),
                    type,
                    deposit.getDate(),
                    deposit.getExpirationDate(),
                    deposit.isExpired(LocalDate.now()) ? DepositStatus.EXPIRED : DepositStatus.VALID
            );
        }).toList();
    }
}
