package org.example.application.impl.services;


import org.example.domain.models.Company;
import org.example.domain.models.GiftDeposit;
import org.example.domain.models.MealDeposit;
import org.example.domain.models.User;
import org.example.domain.ports.input.DepositService;
import org.example.domain.ports.output.CompanyRepository;
import org.example.domain.ports.output.UserRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class DepositServiceImpl implements DepositService {
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;

    public DepositServiceImpl(UserRepository userRepository, CompanyRepository companyRepository) {
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
    }

    @Override
    public void distributeGift(UUID companyId, UUID userId, BigDecimal amount) {
        Company company = companyRepository.findById(companyId).orElseThrow();
        User user = userRepository.findById(userId).orElseThrow();

        company.debit(amount);
        user.addDeposit(new GiftDeposit(amount, LocalDate.now()));

        companyRepository.save(company);
        userRepository.save(user);
    }

    @Override
    public void distributeMeal(UUID companyId, UUID userId, BigDecimal amount) {
        Company company = companyRepository.findById(companyId).orElseThrow();
        User user = userRepository.findById(userId).orElseThrow();

        company.debit(amount);
        user.addDeposit(new MealDeposit(amount, LocalDate.now()));

        companyRepository.save(company);
        userRepository.save(user);
    }
}