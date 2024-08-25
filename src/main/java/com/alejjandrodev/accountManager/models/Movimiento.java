package com.alejjandrodev.accountManager.models;

import com.alejjandrodev.accountManager.common.ValidationGroups;
import jakarta.persistence.*;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.*;
import org.springframework.context.annotation.Lazy;

import java.util.Date;

@Entity
@Table(name = "movimientos", indexes = { @Index(name = "index_numero", columnList = "numeroCuenta", unique = false)})
public class Movimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(groups = ValidationGroups.Create.class)
    @Column(nullable = false)
    Date fecha;

    @NotBlank(groups = ValidationGroups.Create.class)
    @Size(min = 1, max = 50)
    @Column(nullable = false)
    String tipo;

    @NotNull(groups = ValidationGroups.Create.class)
    @Column(nullable = false)
    Float valor;

    @Column(nullable = false)
    Float saldo;

    @NotNull
    @Min(0)
    private Long numeroCuenta;


    @ManyToOne(
            fetch = FetchType.LAZY,
            optional = false,
        targetEntity = Cuenta.class)
    Cuenta cuenta;


    public Movimiento(Long id, Date fecha, String tipo, Float valor, Float saldo, Long numeroCuenta) {
        this.id = id;
        this.fecha = fecha;
        this.tipo = tipo;
        this.valor = valor;
        this.saldo = saldo;
    }

    public Movimiento() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Float getSaldo() {
        return saldo;
    }

    public void setSaldo(Float saldo) {
        this.saldo = saldo;
    }



    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public @NotNull Long getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(@NotNull Long numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }
}
