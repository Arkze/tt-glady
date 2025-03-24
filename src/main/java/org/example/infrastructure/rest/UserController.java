package org.example.infrastructure.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Users", description = "User related operations")
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

    @Operation(summary = "Create a new user", responses = {
            @ApiResponse(responseCode = "200", description = "User created"),
            @ApiResponse(responseCode = "500", description = "Internal error")
    })
    @PostMapping
    public ResponseEntity<User> createUser(@RequestParam String name) {
        User user = createUser.create(name);
        return ResponseEntity.ok(user);
    }

    @Operation(summary = "Get user balance", responses = {
            @ApiResponse(responseCode = "200", description = "Balance returned"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "500", description = "Internal error")
    })
    @GetMapping("/{userId}/balance")
    public ResponseEntity<BigDecimal> getBalance(@PathVariable UUID userId) {
        return ResponseEntity.ok(this.getUserBalance.getBalance(userId));
    }

    @Operation(summary = "Get user deposits", responses = {
            @ApiResponse(responseCode = "200", description = "List of deposits"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "500", description = "Internal error")
    })
    @GetMapping("/{userId}/deposits")
    public ResponseEntity<List<DepositDto>> getDeposits(@PathVariable UUID userId) {
        return ResponseEntity.ok(this.getUserDeposits.getAll(userId));
    }

}
