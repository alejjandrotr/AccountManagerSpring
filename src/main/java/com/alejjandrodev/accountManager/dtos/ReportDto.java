package com.alejjandrodev.accountManager.dtos;

import com.alejjandrodev.accountManager.models.Cuenta;
import com.alejjandrodev.accountManager.models.Movimiento;

import java.util.Date;

public class ReportDto {

    Date fecha;
    String cliente;
    Long numero;
    String tipo;
    Float saldoInicial;
    Boolean estado;
    Float movimiento;
    Float saldoDisponible;

    public ReportDto() {}

    public ReportDto(Cuenta cuenta, Movimiento  movimiento) {
        this.fecha = movimiento.getFecha();
        this.cliente = cuenta.getCliente();
        this.numero =   cuenta.getNumero();
        this.tipo =   cuenta.getTipo();
        this.saldoInicial = cuenta.getSaldoInicial();
        this.estado =   cuenta.getEstado();
        this.movimiento = movimiento.getValor();
        this.saldoDisponible = movimiento.getSaldo();
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Float getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(Float saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Float getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(Float movimiento) {
        this.movimiento = movimiento;
    }

    public Float getSaldoDisponible() {
        return saldoDisponible;
    }

    public void setSaldoDisponible(Float saldoDisponible) {
        this.saldoDisponible = saldoDisponible;
    }
}
