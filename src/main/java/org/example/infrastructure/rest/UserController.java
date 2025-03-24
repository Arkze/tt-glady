package org.example.infrastructure.rest;

import org.example.application.dtos.DepositDto;
import org.example.domain.ports.input.users.CreateUserUseCase;
import org.example.domain.ports.input.users.GetUserBalanceUseCase;
import org.example.domain.ports.input.users.GetUserDepositsUseCase;
import org.example.domain.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final GetUserBalanceUseCase getUserBalance;
    private final GetUserDepositsUseCase getUserDeposits;
    private final CreateUserUseCase createUser;

    public UserController(GetUserBalanceUseCase getUserBalance,
                          GetUserDepositsUseCase getUserDeposits, CreateUserUseCase createUser) {
        this.getUserBalance = getUserBalance;
        this.getUserDeposits = getUserDeposits;
        this.createUser = createUser;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestParam String name) {
        User user = createUser.create(name);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{userId}/balance")
    public ResponseEntity<BigDecimal> getBalance(@PathVariable UUID userId) {
        return ResponseEntity.ok(this.getUserBalance.getBalance(userId));
    }

    @GetMapping("/{userId}/deposits")
    public ResponseEntity<List<DepositDto>> getDeposits(@PathVariable UUID userId) {
        return ResponseEntity.ok(this.getUserDeposits.getAll(userId));
    }

}
