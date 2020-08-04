package ar.com.ada.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.com.ada.api.entities.reportes.*;
import ar.com.ada.api.services.ReporteService;

@RestController
public class ReporteController {

    @Autowired
    ReporteService reporteService;

    @GetMapping("/reportes")
    public ResponseEntity<?> getReportes(@RequestParam(value = "nombre", required = false) String nombre) {

        switch (nombre) {
            case "ResumenReservasPorEstados":

                List<ReportePorEstado> re = reporteService.getReporteReservasPorEstado();

                return ResponseEntity.ok(re);

            case "ResumenReservasPorHuespedes":

                List<ReportePorHuesped> rh = reporteService.getReporteReservasPorHuespedes();

                return ResponseEntity.ok(rh);

            default:

                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    
}