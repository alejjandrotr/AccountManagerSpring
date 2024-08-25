package com.alejjandrodev.accountManager.errors.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NotAmountAvaibleError extends ResponseStatusException {
    public NotAmountAvaibleError() {
        super(HttpStatus.BAD_REQUEST, "Saldo no disponible.");
    }
}
