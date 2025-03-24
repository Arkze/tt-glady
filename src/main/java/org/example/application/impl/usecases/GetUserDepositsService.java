package org.example.application.impl.usecases;

import org.example.application.dtos.DepositDto;
import org.example.domain.ports.input.users.GetUserDepositsUseCase;
import org.example.domain.models.GiftDeposit;
import org.example.domain.models.enums.DepositStatus;
import org.example.domain.models.enums.DepositType;
import org.example.domain.ports.output.DepositRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class GetUserDepositsService implements GetUserDepositsUseCase {

    private final DepositRepository depositRepository;

    public GetUserDepositsService(DepositRepository depositRepository) {
        this.depositRepository = depositRepository;
    }

    @Override
    public List<DepositDto> getAll(UUID userId) {
        return this.depositRepository.findAllForUser(userId).stream().map(deposit -> {
            DepositType type = (deposit instanceof GiftDeposit) ? DepositType.GIFT : DepositType.MEAL;
            return new DepositDto(
                    deposit.getAmount(),
                    type,
                    deposit.getDate(),
                    deposit.getExpirationDate(),
                    deposit.isExpired(LocalDate.now()) ? DepositStatus.EXPIRED : DepositStatus.VALID
            );
        }).toList();
    }

}