package com.alejjandrodev.accountManager.clients.ClienteDataManager.dtos;

import com.alejjandrodev.accountManager.common.ValidationGroups;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public abstract class Persona {
    private Long id;

    @NotBlank(groups = ValidationGroups.Create.class)
    private String nombre;

    @Size(min = 1)
    private String genero;

    @NotBlank(groups = ValidationGroups.Create.class)
    @Size(min = 5, max = 100)
    private String direccion;

    @NotBlank(groups = ValidationGroups.Create.class)
    @Size(min = 5, max = 100)
    private String telefono;

    @Size( min = 1, max = 50)
    private String identificacion;


    @Min(1)
    private Integer edad;

    public Persona() {
    }
    public Persona(Long id, String nombre, String genero, String direccion, String telefono, String identificacion, Integer edad) {
        this.id = id;
        this.nombre = nombre;
        this.genero = genero;
        this.direccion = direccion;
        this.telefono = telefono;
        this.identificacion = identificacion;
        this.edad = edad;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

}
