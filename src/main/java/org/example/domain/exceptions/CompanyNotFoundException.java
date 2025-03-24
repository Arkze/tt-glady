package org.example.domain.exceptions;

public class CompanyNotFoundException extends NotFoundException {
    public CompanyNotFoundException(String message) {
        super(message);
    }
}
