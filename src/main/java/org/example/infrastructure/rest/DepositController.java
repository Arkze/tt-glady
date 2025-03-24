package org.example.infrastructure.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.domain.ports.input.deposit.DistributeDepositUseCase;
import org.example.infrastructure.rest.dtos.DistributeDepositRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/deposits")
@Tag(name = "Deposits", description = "Deposit related operations")
public class DepositController {

    private final DistributeDepositUseCase distributeDepositUseCase;

    public DepositController(DistributeDepositUseCase distributeDepositUseCase) {
        this.distributeDepositUseCase = distributeDepositUseCase;
    }

    @Operation(summary = "Distribute a deposit to a user", responses = {
            @ApiResponse(responseCode = "200", description = "Deposit distributed"),
            @ApiResponse(responseCode = "404", description = "Company or User not found"),
            @ApiResponse(responseCode = "500", description = "Internal error")
    })
    @PostMapping("/distribute")
    public ResponseEntity<Void> distributeDeposit(@RequestBody DistributeDepositRequestDTO request) {
        distributeDepositUseCase.distribute(
                request.companyId(),
                request.userId(),
                request.amount(),
                request.type()
        );
        return ResponseEntity.ok().build();
    }


}

