package org.example.application.impl.usecases;

import org.example.domain.ports.input.deposit.DistributeDepositUseCase;
import org.example.domain.models.*;
import org.example.domain.models.enums.DepositType;
import org.example.domain.ports.output.CompanyRepository;
import org.example.domain.ports.output.DepositRepository;
import org.example.domain.ports.output.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

/**
 * Service that handles the distribution of a deposit from a company to a user.
 * Implements the {@link DistributeDepositUseCase} interface.
 */
@Service
public class DistributeDepositService implements DistributeDepositUseCase {

    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final DepositRepository depositRepository;

    /**
     * Constructs the service with required repositories.
     *
     * @param userRepository    the user repository
     * @param companyRepository the company repository
     * @param depositRepository the deposit repository
     */
    public DistributeDepositService(UserRepository userRepository,
                                    CompanyRepository companyRepository,
                                    DepositRepository depositRepository) {
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
        this.depositRepository = depositRepository;
    }

    /**
     * Distributes a deposit of a specific type and amount from a company to a user.
     * <p>
     * This method performs the following:
     * <ul>
     *     <li>Fetches the user and company from the repositories</li>
     *     <li>Debits the amount from the company’s balance</li>
     *     <li>Creates a new {@link GiftDeposit} or {@link MealDeposit}</li>
     *     <li>Adds the deposit to the user</li>
     *     <li>Saves the updated company, user, and deposit</li>
     * </ul>
     *
     * @param companyId the UUID of the company
     * @param userId    the UUID of the user
     * @param amount    the amount to deposit
     * @param type      the type of deposit (GIFT or MEAL)
     * @throws java.util.NoSuchElementException if the user or company does not exist
     * @throws IllegalArgumentException if the company does not have sufficient balance
     */
    @Override
    public void distribute(UUID companyId, UUID userId, BigDecimal amount, DepositType type) {
        User user = userRepository.findById(userId).orElseThrow();
        Company company = companyRepository.findById(companyId).orElseThrow();

        company.debit(amount);

        Deposit deposit = switch (type) {
            case GIFT -> new GiftDeposit(amount, LocalDate.now());
            case MEAL -> new MealDeposit(amount, LocalDate.now());
        };

        user.addDeposit(deposit);

        companyRepository.save(company);
        userRepository.save(user);
        depositRepository.save(deposit, user, company, type);
    }
}
