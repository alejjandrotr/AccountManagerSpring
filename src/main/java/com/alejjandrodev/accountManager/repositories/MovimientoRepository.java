package com.alejjandrodev.accountManager.repositories;

import com.alejjandrodev.accountManager.models.Movimiento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import java.util.Date;
import java.util.List;

public interface MovimientoRepository extends Repository<Movimiento, Long> {
    Movimiento save(Movimiento movimiento);
    Page<Movimiento> findAll(Pageable page);
    Movimiento findById(Long id);
    void deleteById(Long id);

    List<Movimiento> findByFechaBetweenAndCuenta_Cliente(Date startDate, Date endDate, String cliente);
}
