package org.example.infrastructure.rest.dtos;

import java.math.BigDecimal;

/**
 * DTO representing a request to credit a company's balance.
 * This is used in the REST API to specify the amount of money
 * to be added to the company's account.
 *
 * @param amount the amount to credit to the company's balance
 */
public record CreditBalanceRequestDTO(BigDecimal amount) {}
