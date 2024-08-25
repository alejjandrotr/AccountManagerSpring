package com.alejjandrodev.accountManager.integrations.controllers;


import com.alejjandrodev.accountManager.models.Movimiento;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.util.AssertionErrors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.web.servlet.View;

import java.util.Date;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class MovimientoControllerIntegrationTest {

    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private View error;


    @Test
    public void testGetMovimientos() throws Exception {
        mockMvc.perform(get("/movimientos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.size()", greaterThan(3)));
    }

    @Test
    public void testPostMovimientos() throws Exception {
        Movimiento newMovimiento = new Movimiento();
        newMovimiento.setNumeroCuenta((long) 1315134);
        newMovimiento.setFecha(new Date());
        newMovimiento.setValor((float) 800);
        newMovimiento.setTipo("Deposito");
        mockMvc.perform(post("/movimientos", newMovimiento).contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(newMovimiento)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.saldo").value(3300))
                .andExpect(jsonPath("$.valor").value(newMovimiento.getValor()));
    }

    @Test
    public void testPostMovimientos_SALDO_NO_DISPONIBLE() throws Exception {
        Movimiento newMovimiento = new Movimiento();
        newMovimiento.setNumeroCuenta((long) 1315135);
        newMovimiento.setFecha(new Date());
        newMovimiento.setValor((float) -8000);
        newMovimiento.setTipo("Retiro");
        ResultActions resultActions= mockMvc.perform(post("/movimientos", newMovimiento).contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(newMovimiento)));
        String msg = resultActions.andReturn()
                .getResolvedException()
                .getMessage();

        resultActions.andExpect(status().isBadRequest())
                .andExpect(this.matcherSaldoNoDisponible(msg));;
    }

    private ResultMatcher matcherSaldoNoDisponible(String msg) {
        return (result) -> {
            AssertionErrors.assertEquals("MessageError", true, msg.matches("(.*)Saldo no disponible(.*)"));
        };
    }



}
