package com.alejjandrodev.accountManager.services;

import com.alejjandrodev.accountManager.dtos.MovimientoDto;
import com.alejjandrodev.accountManager.errors.exceptions.NotFoudCuentaError;
import com.alejjandrodev.accountManager.models.Cuenta;
import com.alejjandrodev.accountManager.models.Movimiento;
import com.alejjandrodev.accountManager.repositories.MovimientoRepository;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovimientoService {

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Autowired
    private CuentaService cuentaService;

    Logger logger = LoggerFactory.getLogger(CuentaService.class);

    public Page<Movimiento> findAll(Pageable page) {
        logger.info("Find all movimientos");
        return this.movimientoRepository.findAll(page);
    }

    public Movimiento findById(long id) {
        logger.info("Find movimiento by id: " + id);
        Movimiento movimientoFound = this.movimientoRepository.findById(id);
        if (movimientoFound == null) {
            logger.error("Movimiento with id: " + id + " not found");
            throw new NotFoudCuentaError();
        }
        return movimientoFound;
    }

    public Movimiento create(MovimientoDto movimiento) {
        /*
        * Se puede hacer un uso mas robusto del seguimiento de la trassacciones en base de datos
        * Pero se espera mejor podernos integrar luego a sistemas como new Relic que nos permitan
        * analizar los logs de las trascciones en sus distintos puntos donde la misma se genera
        * */
        logger.info("Create movimiento: " + movimiento.getValor() + " en cuenta "+movimiento.getNumeroCuenta());
        Movimiento newMovimiento = new Movimiento();
        newMovimiento.setFecha(movimiento.getFecha());
        newMovimiento.setTipo(movimiento.getTipo());
        newMovimiento.setValor(movimiento.getValor());
        newMovimiento.setFecha(movimiento.getFecha());

        Cuenta cuenta = this.cuentaService.updateSaldo(movimiento.getNumeroCuenta(), newMovimiento.getValor());
        newMovimiento.setCuenta(cuenta);
        newMovimiento.setNumeroCuenta(movimiento.getNumeroCuenta());
        newMovimiento.setSaldo(cuenta.getSaldoDisponible());
        logger.info("Movimiento created: " + newMovimiento.getId());
        return movimientoRepository.save(newMovimiento);
    }



    public Movimiento update(Long id, @Valid Movimiento movimiento) {
        logger.info("Update movimiento with id: " + id);
        Movimiento movimientoFound = this.findById(id);

        if (movimiento.getFecha() != null) {
            movimientoFound.setFecha(movimiento.getFecha());
        }

        if (movimiento.getTipo() != null) {
            movimientoFound.setTipo(movimiento.getTipo());
        }

        /*
            Se considero que la modificacion del valor altera
            los reportes por lo que seria mejor introducir transacciones
            nueva con los nuevos valores
         */
        Movimiento upMovimiento =  this.movimientoRepository.save(movimientoFound);
        logger.info("Movimiento updated: " + upMovimiento.getId());
        return upMovimiento;
    }

    public void delete(Long id) {
         /*
            La eliminacion de un movimiento alteraria el reporte y cualquier reporte
            este endpoint fue colocado por el test pero deberia generar se un endpoint
            reverso que que devuelva el movimiento y lo introdusca como una nuevo movimiento
            que corrija el saldo
         */
        logger.info("Delete movimiento with id: " + id);
        this.findById(id);
        this.movimientoRepository.deleteById(id);
        logger.info("Movimiento deleted: " + id);
    }
}
