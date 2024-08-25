package com.alejjandrodev.accountManager.repositories;

import com.alejjandrodev.accountManager.models.Cuenta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import java.util.Date;
import java.util.List;

public interface CuentaRepository extends Repository<Cuenta, Long> {
    Cuenta save(Cuenta cuenta);
    Page<Cuenta> findAll(Pageable page);
    Cuenta findById(Long id);
    void deleteById(Long id);
    void deleteByClienteId(Long clienteId);

    Cuenta findFirstByNumero(Long numero);
    List<Cuenta> findByCliente(String cliente);
    List<Cuenta> findByClienteId(Long clienteId);

    List<Cuenta> findByClienteAndMovimientos_FechaBetween(String cliente, Date startDate, Date endDate);

}

