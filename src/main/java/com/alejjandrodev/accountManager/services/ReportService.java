package com.alejjandrodev.accountManager.services;

import com.alejjandrodev.accountManager.dtos.ReportDto;
import com.alejjandrodev.accountManager.models.Cuenta;
import com.alejjandrodev.accountManager.models.Movimiento;
import com.alejjandrodev.accountManager.repositories.CuentaRepository;
import com.alejjandrodev.accountManager.repositories.MovimientoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ReportService {

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    Logger logger = LoggerFactory.getLogger(ReportService.class);

    public List<ReportDto> getReports(Date startDate,
                                   Date endDate,
                                   String cliente) {
        logger.info("Begin getReports");
        List<ReportDto> reporte = new ArrayList<>();
        List<Cuenta> cuentas = this.cuentaRepository.findByClienteAndMovimientos_FechaBetween(cliente, startDate, endDate);
        for (Cuenta cuenta : cuentas) {
            for (Movimiento movimiento : cuenta.getMovimientos()) {
                reporte.add(new ReportDto(cuenta, movimiento));
            }
        }
        logger.info("End getReports");
        return reporte;
    }


}
