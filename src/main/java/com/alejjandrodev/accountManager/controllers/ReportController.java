package com.alejjandrodev.accountManager.controllers;

import com.alejjandrodev.accountManager.dtos.ReportDto;
import com.alejjandrodev.accountManager.models.Cuenta;
import com.alejjandrodev.accountManager.models.Movimiento;
import com.alejjandrodev.accountManager.services.CuentaService;
import com.alejjandrodev.accountManager.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/reportes")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping
    public List<ReportDto> getReport(@RequestParam(required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                     @RequestParam(required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
                                     @RequestParam(required = true) String cliente) {
        return this.reportService.getReports(startDate, endDate, cliente);
    }
}
