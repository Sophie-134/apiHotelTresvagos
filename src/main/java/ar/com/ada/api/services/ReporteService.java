package ar.com.ada.api.services;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.entities.*;
import ar.com.ada.api.repos.*;
import ar.com.ada.api.entities.reportes.*;

@Service
public class ReporteService {

    @Autowired
    ReservaRepository reporteRepo;

    public List<ReportePorEstado> getReporteReservasPorEstado() {

        return reporteRepo.getReporteReservasPorEstado();
    }

    public List<ReportePorHuesped> getReporteReservasPorHuespedes() {

        return reporteRepo.getReporteReservasPorHuespedes();
    }

}