package com.alejjandrodev.accountManager.controllers;

import com.alejjandrodev.accountManager.common.ValidationGroups;
import com.alejjandrodev.accountManager.dtos.MovimientoDto;
import com.alejjandrodev.accountManager.models.Movimiento;
import com.alejjandrodev.accountManager.services.MovimientoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {

    @Autowired
    private MovimientoService movimientoService;

    @GetMapping
    public Page<Movimiento> getClientes(Pageable page) {
        return this.movimientoService.findAll(page);
    }

    @GetMapping("/{id}")
    public Movimiento getOne(@PathVariable Long id) {
        return this.movimientoService.findById(id);
    }

    @PostMapping
    public Movimiento create(@Validated(ValidationGroups.Create.class) @RequestBody MovimientoDto movimiento) {
        return this.movimientoService.create(movimiento);
    }

    @PatchMapping("/{id}")
    public Movimiento update(@PathVariable Long id, @Valid @RequestBody Movimiento movimiento) {
        return this.movimientoService.update(id, movimiento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.movimientoService.delete(id);
        return ResponseEntity.noContent().build();
    }


}