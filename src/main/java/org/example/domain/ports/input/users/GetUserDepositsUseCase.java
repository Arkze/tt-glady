package org.example.domain.ports.input.users;


import org.example.application.dtos.DepositDto;

import java.util.List;
import java.util.UUID;

public interface GetUserDepositsUseCase {
    List<DepositDto> getAll(UUID userId);
}
