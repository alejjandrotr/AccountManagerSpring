package com.alejjandrodev.accountManager.services;

import com.alejjandrodev.accountManager.clients.ClienteDataManager.ClientaDataManagerClientImpl;
import com.alejjandrodev.accountManager.clients.ClienteDataManager.ClienteDataManagerClient;
import com.alejjandrodev.accountManager.clients.ClienteDataManager.dtos.Cliente;
import com.alejjandrodev.accountManager.errors.exceptions.DuplicateKeyException;
import com.alejjandrodev.accountManager.errors.exceptions.NotAmountAvaibleError;
import com.alejjandrodev.accountManager.errors.exceptions.NotFoudCuentaError;
import com.alejjandrodev.accountManager.models.Cuenta;
import com.alejjandrodev.accountManager.repositories.CuentaRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuentaService {

    Logger logger = LoggerFactory.getLogger(CuentaService.class);

    @Autowired
    private CuentaRepository cuentaRepository;

    private ClienteDataManagerClient clienteDataManagerClient;

    @Autowired
    void setClienteDataManagerClient(ClientaDataManagerClientImpl clienteDataManagerClient) {
        this.clienteDataManagerClient = clienteDataManagerClient;
    }

    public Page<Cuenta> findAll(Pageable page) {
        logger.info("Find all cuentas");
        return this.cuentaRepository.findAll(page);
    }

    public Cuenta findById(long id) {
        logger.info("Begin Find cuenta"+id);
        Cuenta cuentaFound =  this.cuentaRepository.findById(id);
        if (cuentaFound == null) {
            logger.error("Cuenta not found "+id);
            throw new NotFoudCuentaError();
        }
        logger.info("End Find cuenta"+id);
        return cuentaFound;
    }

    public Cuenta findByNumero(long numero) {
        logger.info("Begin Find cuenta"+numero);
        Cuenta cuentaFound =  this.cuentaRepository.findFirstByNumero(numero);
        if (cuentaFound == null) {
            logger.error("Cuenta not found "+numero);
            throw new NotFoudCuentaError();
        }
        logger.info("End Find cuenta"+numero);
        return cuentaFound;
    }

    public Cuenta create(Cuenta cuenta) {
        try {
            logger.info("Begin Create cuenta "+cuenta.getNumero());
            this.assingClienteToCuenta(cuenta);
            cuenta.setSaldoDisponible(cuenta.getSaldoInicial());
            Cuenta newCuenta =  cuentaRepository.save(cuenta);
            logger.info("End Create cuenta "+cuenta.getNumero());
            return newCuenta;
        } catch (DataIntegrityViolationException ex) {
            String message = ex.getMessage();
            if (message.toLowerCase().contains("duplicate")) {
                throw new DuplicateKeyException();
            } else {
                throw ex;
            }
        }
    }

    private Cuenta assingClienteToCuenta(Cuenta  cuenta){
        Cliente cliente = this.clienteDataManagerClient.getClienteDataByName(cuenta.getCliente());
        cuenta.setClienteId(cliente.getId());
        return cuenta;
    }

    public Cuenta update(Long id, @Valid Cuenta cuentaDto) {
        logger.info("Begin Update cuenta "+ id + " ");
        Cuenta cuentaFound = this.findById(id);

        if (cuentaDto.getCliente() != null){
            cuentaFound.setCliente(cuentaDto.getCliente());
            this.assingClienteToCuenta(cuentaFound);
        }

        if (cuentaDto.getTipo() != null ){
            cuentaFound.setTipo(cuentaDto.getTipo());
        }

        if (cuentaDto.getEstado() != null ){
            cuentaFound.setEstado(cuentaDto.getEstado());
        }
        Cuenta updatedCuenta = this.cuentaRepository.save(cuentaFound);
        logger.info("End Update cuenta "+id);
        return updatedCuenta;
    }

    public void delete(Long id) {
        logger.info("Begin Delete cuenta "+id);
        this.findById(id);
        this.cuentaRepository.deleteById(id);
        logger.info("End Delete cuenta "+id);
    }

    public Cuenta updateSaldo(Long numeroCuenta, Float valor) {
        String tipo =  ((valor<0)?"retiro":"deposito");
        logger.info("Begin Update saldo "+numeroCuenta+" " +tipo + " de "+ valor);
        Cuenta cuenta = this.findByNumeroCuenta(numeroCuenta);
        float newAmount  = cuenta.getSaldoDisponible() + valor;
        if (newAmount < 0) {
            throw new NotAmountAvaibleError();
        }
        cuenta.setSaldoDisponible(newAmount);
        cuentaRepository.save(cuenta);
        logger.info("Emd Update saldo "+numeroCuenta+" " + ((valor<0)?"retiro":"deposito") + " de "+ valor + " = "+ newAmount);
        return cuenta;
    }

    private Cuenta findByNumeroCuenta(Long numeroCuenta) {
        logger.info("Begin Find cuenta"+numeroCuenta);
        Cuenta cuentaFound =  this.cuentaRepository.findFirstByNumero(numeroCuenta);
        if (cuentaFound == null) {
            throw new NotFoudCuentaError();
        }
        return cuentaFound;
    }

    @Transactional
    public void deleteAll(Long clientId) {
        logger.info("Begin Delete all cuentas");
        this.cuentaRepository.deleteByClienteId(clientId);
        logger.info("End Delete all cuentas");
    }

    @Transactional
    public void updateClientName(Long clientId, String newName) {
        logger.info("Begin Update cliente name "+clientId + " to name "+ newName);
        List<Cuenta> cuentas = this.cuentaRepository.findByClienteId(clientId);
        for (Cuenta cuenta : cuentas) {
            cuenta.setCliente(newName);
            this.cuentaRepository.save(cuenta);
        }
        logger.info("End Update cliente name "+clientId + " to name "+ newName);
    }
}
