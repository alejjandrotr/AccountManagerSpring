package com.alejjandrodev.accountManager.clients.ClienteDataManager;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.alejjandrodev.accountManager.clients.ClienteDataManager.dtos.Cliente;
import com.alejjandrodev.accountManager.clients.ClienteDataManager.errors.ClienteNotFoundError;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Service
public class ClientaDataManagerClientImpl implements ClienteDataManagerClient{

    Logger logger = LoggerFactory.getLogger(ClientaDataManagerClientImpl.class);

    @Value("${cliente-data-manager.url}")
    private String clienteDataManagerUrl;

    private final WebClient webClient;

    @Autowired
    public ClientaDataManagerClientImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Cliente getClienteDataByName(String name) {
        try {
            logger.info("find client by name: "+name);
            return webClient.get()
                    .uri(this.clienteDataManagerUrl+"/clientes/name/"+name)
                    .retrieve()
                    .bodyToMono(Cliente.class)
                    .block();
        } catch (WebClientResponseException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new ClienteNotFoundError(name);
            } else {
                throw new RuntimeException("Unexpected error fetching client data", ex);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error fetching client data", e);
        }
    }
}
