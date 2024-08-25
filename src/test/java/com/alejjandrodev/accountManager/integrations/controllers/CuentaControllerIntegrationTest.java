package com.alejjandrodev.accountManager.integrations.controllers;


import com.alejjandrodev.accountManager.clients.ClienteDataManager.ClientaDataManagerClientImpl;
import com.alejjandrodev.accountManager.clients.ClienteDataManager.dtos.Cliente;
import com.alejjandrodev.accountManager.models.Cuenta;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.hamcrest.Matchers.greaterThan;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CuentaControllerIntegrationTest {

    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientaDataManagerClientImpl clientaDataManagerClient;

    @Test
    public void testGetCuentas() throws Exception {
        mockMvc.perform(get("/cuentas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.size()", greaterThan(3)));
    }

    @Test
    public void testGetOneCuenta() throws Exception {
        mockMvc.perform(get("/cuentas/361"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tipo").exists())
                .andExpect(jsonPath("$.numero").exists())
                .andExpect(jsonPath("$.clienteId").exists())
                .andExpect(jsonPath("$.cliente").exists())
                .andExpect(jsonPath("$.saldoInicial").exists())
                .andExpect(jsonPath("$.saldoDisponible").exists());
    }

    @Test
    public void testGetOneCuenta_NOTFOUND() throws Exception {
        mockMvc.perform(get("/cuentas/36212"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetOneCuentaByNumber() throws Exception {
        mockMvc.perform(get("/cuentas/numero/1315133"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tipo").exists())
                .andExpect(jsonPath("$.numero").exists())
                .andExpect(jsonPath("$.clienteId").exists())
                .andExpect(jsonPath("$.cliente").exists())
                .andExpect(jsonPath("$.saldoInicial").exists())
                .andExpect(jsonPath("$.saldoDisponible").exists());
    }

    @Test
    public void testGetOneCuentaByNumber_NOTFOUND() throws Exception {
        mockMvc.perform(get("/cuentas/numero/131513354"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testPostClientes_allDataOK() throws Exception {
        Cuenta cuenta = this.createFakeMockCuentaObject();
        Mockito.when(clientaDataManagerClient.getClienteDataByName(cuenta.getCliente())).thenReturn(createFakeMockClientObject());

        mockMvc.perform(post("/cuentas", cuenta).contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(cuenta)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numero").value(cuenta.getNumero()))
                .andExpect(jsonPath("$.saldoDisponible").value(cuenta.getSaldoInicial()))
                .andExpect(jsonPath("$.tipo").value(cuenta.getTipo()))
                .andExpect(jsonPath("$.cliente").value(cuenta.getCliente()))
                .andExpect(jsonPath("$.saldoInicial").value(cuenta.getSaldoInicial()));
    }





    private Cuenta createFakeMockCuentaObject() {
        Cuenta cuenta = new Cuenta();
        cuenta.setCliente("Alejandro");
        cuenta.setSaldoInicial( (float) 2500);
        cuenta.setNumero(Math.round(Math.random() * 10000));
        cuenta.setTipo("ahorro");
        cuenta.setEstado(true);
        return cuenta;
    }

    private Cliente createFakeMockClientObject() {
        Cliente client = new Cliente();
        client.setNombre("Alejandro " + UUID.randomUUID());
        client.setEstado(true);
        client.setTelefono("04123515554");
        client.setGenero("M");
        client.setEdad(29);
        client.setPassword("123456");
        client.setIdentificacion("Alejandro " + UUID.randomUUID());
        client.setDireccion("Venezuela");
        client.setId((long) 1);


        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonString = objectMapper.writeValueAsString(client);
            System.out.println(jsonString);
        } catch (JsonProcessingException e) {
            System.out.println("ERROR");
        }

        return client;
    }


}
