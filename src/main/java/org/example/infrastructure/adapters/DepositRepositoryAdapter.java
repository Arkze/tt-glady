package org.example.infrastructure.adapters;

import org.example.domain.models.*;
import org.example.domain.models.enums.DepositType;
import org.example.domain.ports.output.DepositRepository;
import org.example.infrastructure.db.entities.DepositEntity;
import org.example.infrastructure.db.repositories.DepositJpaRepository;
import org.example.infrastructure.mappers.CompanyMapper;
import org.example.infrastructure.mappers.DepositMapper;
import org.example.infrastructure.mappers.UserMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Adapter for deposit repository, responsible for storing and retrieving deposits.
 */
@Repository
public class DepositRepositoryAdapter implements DepositRepository {

    private final DepositJpaRepository depositJpaRepository;

    public DepositRepositoryAdapter(DepositJpaRepository depositJpaRepository) {
        this.depositJpaRepository = depositJpaRepository;
    }

    /**
     * Not implemented. Reserved for future use if needed.
     */
    @Override
    public Optional<Deposit> findById(UUID id) {
        return Optional.empty();
    }

    /**
     * Saves a deposit assigned to a user and company.
     *
     * @param deposit the deposit object
     * @param user the user who receives the deposit
     * @param company the company who distributes the deposit
     * @param type the type of the deposit
     */
    public void save(Deposit deposit, User user, Company company, DepositType type) {
        DepositEntity entity = DepositMapper.toEntity(deposit, type);
        entity.setUser(UserMapper.toEntity(user));
        entity.setCompany(CompanyMapper.toEntity(company));
        depositJpaRepository.save(entity);
    }

    /**
     * Retrieves all deposits assigned to a user.
     *
     * @param userId the ID of the user
     * @return list of domain deposits
     */
    @Override
    public List<Deposit> findAllForUser(UUID userId) {
        return depositJpaRepository.findAllByUserId(userId).stream()
                .map(d -> switch (d.getType()) {
                    case GIFT -> new GiftDeposit(d.getAmount(), d.getDistributionDate());
                    case MEAL -> new MealDeposit(d.getAmount(), d.getDistributionDate());
                })
                .collect(Collectors.toList());
    }

    /**
     * Retrieves all non-expired deposits for a user.
     *
     * @param userId the ID of the user
     * @return list of valid (not expired) deposits
     */
    public List<Deposit> findAllValidDepositsForUser(UUID userId) {
        return depositJpaRepository.findAllByUserId(userId).stream()
                .filter(d -> d.getExpirationDate().isAfter(java.time.LocalDate.now()))
                .map(d -> switch (d.getType()) {
                    case GIFT -> new GiftDeposit(d.getAmount(), d.getDistributionDate());
                    case MEAL -> new MealDeposit(d.getAmount(), d.getDistributionDate());
                })
                .collect(Collectors.toList());
    }
}
