package org.example.domain.exceptions;

public class InsufficientFundException extends BadRequestException {
    public InsufficientFundException(String message) {
        super(message);
    }
}
