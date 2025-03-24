package org.example.application.impl.usecases;

import org.example.domain.exceptions.CompanyNotFoundException;
import org.example.domain.exceptions.DepositBadTypeException;
import org.example.domain.exceptions.InsufficientFundException;
import org.example.domain.exceptions.UserNotFoundException;
import org.example.domain.models.*;
import org.example.domain.models.enums.DepositType;
import org.example.domain.ports.input.deposit.DistributeDepositUseCase;
import org.example.domain.ports.output.CompanyRepository;
import org.example.domain.ports.output.DepositRepository;
import org.example.domain.ports.output.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

/**
 * Service responsible for distributing deposits from companies to users.
 */
@Service
public class DistributeDepositService implements DistributeDepositUseCase {

    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final DepositRepository depositRepository;

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
     * @throws UserNotFoundException    if the user does not exist
     * @throws CompanyNotFoundException if the company does not exist
     * @throws IllegalArgumentException if the company has insufficient balance
     * @throws DepositBadTypeException  if the deposit type is not recognized
     * @throws InsufficientFundException if the balance of the company is too low
     */
    @Override
    @Transactional(rollbackFor = {UserNotFoundException.class, CompanyNotFoundException.class, IllegalArgumentException.class, DepositBadTypeException.class, InsufficientFundException.class})
    public void distribute(UUID companyId, UUID userId, BigDecimal amount, DepositType type) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));

        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new CompanyNotFoundException("Company not found with id: " + companyId));

        try {
            company.debit(amount);
        } catch (InsufficientFundException ife) {
            throw new InsufficientFundException(ife.getMessage());
        }

        if (type == null) {
            throw new DepositBadTypeException("Deposit type must not be null");
        }

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
