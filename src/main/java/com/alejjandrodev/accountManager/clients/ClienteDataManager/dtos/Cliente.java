package com.alejjandrodev.accountManager.clients.ClienteDataManager.dtos;


import com.alejjandrodev.accountManager.common.ValidationGroups;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Cliente extends Persona {

    private Long id;

    @NotBlank(groups = ValidationGroups.Create.class)
    @Size(min = 5, max = 100)
    private String password;

    private Boolean estado;

    public Cliente() {
    }

    public Cliente(Long id, String nombre, String contraseña, Boolean estado) {
        this.id = id;
        this.password = contraseña;
        this.estado = estado;
    }

    public Cliente(Long id, String nombre, String genero, String direccion, String telefono, String identificacion, Integer edad,   String contraseña, Boolean estado) {
        super(id, nombre, genero, direccion, telefono, identificacion, edad);
        this.password = contraseña;
        this.estado = estado;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

}
