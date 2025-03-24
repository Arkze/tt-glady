package org.example.infrastructure.rest;

import org.example.domain.ports.input.deposit.DistributeDepositUseCase;
import org.example.infrastructure.rest.dtos.DistributeDepositRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/deposits")
public class DepositController {

    private final DistributeDepositUseCase distributeDepositUseCase;

    public DepositController(DistributeDepositUseCase distributeDepositUseCase) {
        this.distributeDepositUseCase = distributeDepositUseCase;
    }

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

