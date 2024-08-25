package com.alejjandrodev.accountManager.clients.ClienteDataManager;

import com.alejjandrodev.accountManager.clients.ClienteDataManager.dtos.Cliente;
import com.alejjandrodev.accountManager.clients.ClienteDataManager.errors.ClienteNotFoundError;

public interface ClienteDataManagerClient {
    Cliente getClienteDataByName(String name) throws ClienteNotFoundError;
}
