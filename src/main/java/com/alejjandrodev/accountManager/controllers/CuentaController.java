package com.alejjandrodev.accountManager.controllers;

import com.alejjandrodev.accountManager.common.ValidationGroups;
import com.alejjandrodev.accountManager.models.Cuenta;
import com.alejjandrodev.accountManager.services.CuentaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    @GetMapping
    public Page<Cuenta> get(Pageable pageable) {
        return this.cuentaService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public Cuenta getOne(@PathVariable Long id) {
        return this.cuentaService.findById(id);
    }

    @GetMapping("/numero/{numero}")
    public Cuenta getOneByNumero(@PathVariable Long numero) {
        return this.cuentaService.findByNumero(numero);
    }

    @PostMapping
    public Cuenta create(@Validated(ValidationGroups.Create.class) @RequestBody Cuenta cuenta) {
        return this.cuentaService.create(cuenta);
    }


    @PatchMapping("/renameAll/{id}")
    public ResponseEntity<Void> updateName(@PathVariable Long id, @RequestParam(required = true) String name) {
        this.cuentaService.updateClientName(id, name);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public Cuenta update(@PathVariable Long id, @Valid @RequestBody Cuenta cuenta) {
        return this.cuentaService.update(id, cuenta);
    }

    @DeleteMapping("/deleteAll/{clientId}")
    public ResponseEntity<Void> deleteAllByClientID(@PathVariable Long clientId) {
        this.cuentaService.deleteAll(clientId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.cuentaService.delete(id);
        return ResponseEntity.noContent().build();
    }


}