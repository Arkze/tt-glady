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

