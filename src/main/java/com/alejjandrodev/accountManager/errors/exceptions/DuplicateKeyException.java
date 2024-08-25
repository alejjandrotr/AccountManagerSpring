package com.alejjandrodev.accountManager.errors.exceptions;

public class DuplicateKeyException extends RuntimeException {
    public DuplicateKeyException() {
        super("El valor ingresado para el campo 'número' se encuentra en uso para otra cuenta. Le solicitamos que utilice un valor diferente para identificar la cuenta.");
    }
}
