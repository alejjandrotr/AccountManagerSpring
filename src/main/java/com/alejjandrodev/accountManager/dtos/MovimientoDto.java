package com.alejjandrodev.accountManager.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;

public class MovimientoDto  {
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fecha;

    @NotBlank
    @Size(min = 1, max = 50)
    private String tipo;

    @NotNull
    private Float valor;

    @NotNull
    @Min(0)
    private Long numeroCuenta;

    public MovimientoDto(Date fecha, String tipo, Float valor, Long numeroCuenta) {
        this.fecha = fecha;
        this.tipo = tipo;
        this.valor = valor;
        this.numeroCuenta = numeroCuenta;
    }

    public @NotNull Date getFecha() {
        return fecha;
    }

    public void setFecha(@NotNull Date fecha) {
        this.fecha = fecha;
    }

    public @NotBlank @Size(min = 1, max = 50) String getTipo() {
        return tipo;
    }

    public void setTipo(@NotBlank @Size(min = 1, max = 50) String tipo) {
        this.tipo = tipo;
    }

    public @NotNull Float getValor() {
        return valor;
    }

    public void setValor(@NotNull Float valor) {
        this.valor = valor;
    }

    public @NotNull Long getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(@NotNull Long numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }
}