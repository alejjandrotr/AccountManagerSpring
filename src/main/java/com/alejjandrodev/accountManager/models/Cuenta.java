package com.alejjandrodev.accountManager.models;

import com.alejjandrodev.accountManager.common.ValidationGroups;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.List;

@Entity
@Table(name = "cuentas",
        indexes = {
                @Index(name = "index_client", columnList = "clienteId", unique = false),
                @Index(name = "index_numero", columnList = "numero", unique = false)
        })
public class Cuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(groups = ValidationGroups.Create.class)
    @Null(groups = ValidationGroups.Update.class)
    @Min(0)
    @Column(nullable = false, unique = true)
    Long numero;

    @NotBlank(groups = ValidationGroups.Create.class)
    @Size(min = 1, max = 50)
    @Column(nullable = false)
    String tipo;

    @NotNull(groups = ValidationGroups.Create.class)
    @Null(groups = ValidationGroups.Update.class)
    @Min(0)
    @Column(nullable = false)
    Float saldoInicial;

    @Min(0)
    @Column(nullable = false)
    Float saldoDisponible;

    @NotNull(groups = ValidationGroups.Create.class)
    @Column(nullable = false)
    Boolean estado;

    @NotBlank(groups = ValidationGroups.Create.class)
    @Size(min = 1, max = 50)
    @Column(nullable = false)
    String cliente;

    @Column(nullable = false)
    Long clienteId;

    @OneToMany(mappedBy = "cuenta",
                cascade = CascadeType.REMOVE,
                fetch = FetchType.LAZY,
                orphanRemoval = true)
    List<Movimiento> movimientos;



    public Cuenta() {
    }

    public Cuenta(Long id, Long numero, String tipo, Float saldoInicial, Float saldoDisponible, Boolean estado, String cliente, Long clienteId) {
        this.id = id;
        this.numero = numero;
        this.tipo = tipo;
        this.saldoInicial = saldoInicial;
        this.saldoDisponible = saldoDisponible;
        this.estado = estado;
        this.cliente = cliente;
        this.clienteId = clienteId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull(groups = ValidationGroups.Create.class) @Null(groups = ValidationGroups.Update.class) @Min(0) Long getNumero() {
        return numero;
    }

    public void setNumero(@NotNull(groups = ValidationGroups.Create.class) @Null(groups = ValidationGroups.Update.class) @Min(0) Long numero) {
        this.numero = numero;
    }

    public @NotBlank(groups = ValidationGroups.Create.class) @Size(min = 1, max = 50) String getTipo() {
        return tipo;
    }

    public void setTipo(@NotBlank(groups = ValidationGroups.Create.class) @Size(min = 1, max = 50) String tipo) {
        this.tipo = tipo;
    }

    public @NotNull(groups = ValidationGroups.Create.class) @Null(groups = ValidationGroups.Update.class) @Min(0) Float getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(@NotNull(groups = ValidationGroups.Create.class) @Null(groups = ValidationGroups.Update.class) @Min(0) Float saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public @Min(0) Float getSaldoDisponible() {
        return saldoDisponible;
    }

    public void setSaldoDisponible(@Min(0) Float saldoDisponible) {
        this.saldoDisponible = saldoDisponible;
    }

    public @NotNull(groups = ValidationGroups.Create.class) Boolean getEstado() {
        return estado;
    }

    public void setEstado(@NotNull(groups = ValidationGroups.Create.class) Boolean estado) {
        this.estado = estado;
    }

    public @NotBlank(groups = ValidationGroups.Create.class) @Size(min = 1, max = 50) String getCliente() {
        return cliente;
    }

    public void setCliente(@NotBlank(groups = ValidationGroups.Create.class) @Size(min = 1, max = 50) String cliente) {
        this.cliente = cliente;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public List<Movimiento> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(List<Movimiento> movimientos) {
        this.movimientos = movimientos;
    }
}
