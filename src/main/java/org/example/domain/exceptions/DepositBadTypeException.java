package org.example.domain.exceptions;


public class DepositBadTypeException extends BadRequestException {
    public DepositBadTypeException(String message) {
        super(message);
    }
}
