package ar.com.ada.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ar.com.ada.api.entities.Reserva;
import ar.com.ada.api.models.request.ReservaRequest;
import ar.com.ada.api.models.response.GenericResponse;
import ar.com.ada.api.services.ReservaService;

@RestController
public class ReservaController {

    @Autowired
ReservaService reservaService;

    @PostMapping("/reservas")
    public ResponseEntity<GenericResponse> postReservas(@RequestBody ReservaRequest req) {

        GenericResponse r = new GenericResponse();

        Reserva reserva = reservaService.crearReserva(req.fechaActual, req.fechaEgreso, req.fechaIngreso, req.importeReserva, req.importeTotal, req.huespedId);

            r.isOk = true;
            r.id = reserva.getReservaId();
            r.message = "Creaste una reserva con éxito.";
            return ResponseEntity.ok(r);
        
    }

    @GetMapping("/reservas")
    public List<Reserva> getReservas(@RequestParam(value = "nombre", required = false) String nombre) {
        List<Reserva> lh;

        if (nombre == null) {
            lh = reservaService.getReservas();
        } else {
            lh = reservaService.buscarReservasPorNombre(nombre);
        }

        return lh;
    }

    @GetMapping("/reservas/{id}")
    public ResponseEntity<Reserva> getHuespedById(@PathVariable int id) {
        Reserva r = reservaService.buscarPorId(id);

        if (r == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(r);
    }
    
    @PutMapping("/reservas/{id}")
    public ResponseEntity<?> postHuesped(@PathVariable int id, @RequestBody Reserva reserva) {

        GenericResponse r = new GenericResponse();

        Reserva reservaOriginal = reservaService.buscarPorId(id);

        if (reservaOriginal == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        boolean resultado = false;
        resultado = reservaService.actualizarReserva(reservaOriginal, reserva);

        if (resultado) {
            r.isOk = true;
            r.id = reserva.getReservaId();
            r.message = "Reserva actualizado con éxito.";
            return ResponseEntity.ok(r);
        } else {

            r.isOk = false;
            r.message = "No se pudo actualizar la reserva.";

            return ResponseEntity.badRequest().body(r);
        }

    }

}