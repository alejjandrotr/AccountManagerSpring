package com.alejjandrodev.accountManager.errors.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NotFoudCuentaError extends ResponseStatusException {
    public NotFoudCuentaError() {
        super(HttpStatus.NOT_FOUND, "No se ha encontrado la cuenta.");
    }
}
