package com.alejjandrodev.accountManager.clients.ClienteDataManager.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ClienteNotFoundError extends ResponseStatusException {
  public ClienteNotFoundError(String nombre) {
    super(HttpStatus.NOT_FOUND, "El cliente no existe: " + nombre);
  }
}
