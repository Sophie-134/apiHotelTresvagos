package ar.com.ada.api.services;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.entities.Huesped;
import ar.com.ada.api.entities.Reserva;
import ar.com.ada.api.repos.ReservaRepository;

@Service
public class ReservaService {

    @Autowired
    ReservaRepository reservaRepo;
    @Autowired
    HuespedService huespedService;

    public void grabar(Reserva reserva) {
        huespedService.grabar(reserva.getHuesped());
        // reservaRepo.save(reserva);
    }

    public Reserva buscarPorId(int reservaId) {

        Optional<Reserva> b = reservaRepo.findById(reservaId);

        if (b.isPresent())
            return b.get();

        return null;
    }

    public boolean baja(int reservaId) {

        Reserva reserva = buscarPorId(reservaId);

        if (reserva == null)
            return false;

        return baja(reserva);
    }

    public boolean baja(Reserva reserva) {

        reservaRepo.delete(reserva);
        return true;
    }

    public List<Reserva> getReservas() {

        return reservaRepo.findAll();
    }

    public List<Reserva> buscarReservasPorNombre(String nombre) {

        return reservaRepo.findByNombreHuesped(nombre);
    }

    public List<Reserva> buscarReservasPorDniHuesped(int dni) {

        return reservaRepo.findAllByReservaDniHuesped(dni);
    }

    public boolean actualizarReserva(Reserva reservaOriginal, Reserva reservaConInfoNueva) {
        // Aca solo dejamos que se actualize el nombre y domicilio solamente

        reservaOriginal.setFechaReserva(new Date());
        reservaOriginal.setFechaIngreso(reservaConInfoNueva.getFechaIngreso());
        reservaOriginal.setFechaEgreso(reservaConInfoNueva.getFechaEgreso());
        reservaOriginal.setHabitacion(reservaConInfoNueva.getHabitacion());
        reservaOriginal.setImporteReserva(reservaConInfoNueva.getImporteReserva());
        reservaOriginal.setImportePagado(reservaConInfoNueva.getImportePagado());
        reservaOriginal.setImporteTotal(reservaConInfoNueva.getImporteTotal());
        reservaOriginal.setTipoDeEstadoId(reservaConInfoNueva.getTipoDeEstadoId());

        grabar(reservaOriginal);

        return true;

    }

    public void crearReserva(Reserva reserva) {
        grabar(reserva);
    }

    public Reserva crearReserva(Date fechaReserva, Date fechaIngreso, Date fechaEgreso, BigDecimal importeTotal,
            BigDecimal importeReserva, int huespedId) {

        // Crear el objeto en memoria
        Reserva reserva = new Reserva();

        // setear los atributoss
        reserva.setFechaReserva(fechaReserva);
        reserva.setFechaIngreso(fechaIngreso);
        reserva.setFechaEgreso(fechaEgreso);
        reserva.setImporteTotal(importeTotal);
        reserva.setImporteReserva(importeReserva);

        // buscar el huesped y asignarlo

        Huesped huesped = huespedService.buscarPorId(huespedId);
        huesped.agregarReserva(reserva);// El de la relacion bidireccional

        crearReserva(reserva);

        return reserva;
    }

}