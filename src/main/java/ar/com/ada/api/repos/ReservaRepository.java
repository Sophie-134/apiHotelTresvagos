package ar.com.ada.api.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ar.com.ada.api.entities.*;
import ar.com.ada.api.entities.reportes.*;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Integer> {

    // En este caso se hace un JPQL por el nombre del cliente
    @Query("FROM Huesped WHERE nombre like CONCAT('%', :nombre,'%')")
    // List<Reserva> findByNombreHuesped(@Param("nombre") String nombre);
    List<Reserva> findByNombreHuesped(String nombre);

    @Query(value = "SELECT t.id estadoId, t.descripcion, count(r.reserva_id) cantidadReservas, sum(r.importe_reserva) totalImporteReserva, sum(r.importe_pagado) totalImportePagado, sum(r.importe_total) importeTotal FROM reserva r INNER JOIN tipo_de_estado t ON r.estado2 = t.id GROUP BY t.id, t.descripcion", nativeQuery = true)
    // List<ReportePorEstado> getReporteReservasPorEstado();
    List<ReportePorEstado> getReporteReservasPorEstado();

    @Query(value = "select h.huesped_id huespedId, h.nombre, sum(r.importe_reserva) importeReserva, sum(r.importe_pagado) importePagado, sum(r.importe_total) importeTotal from huesped h inner join reserva r on h.huesped_id = r.huesped_id group by h.huesped_id, h.nombre", nativeQuery = true)
    // List<ReportePorHuesped> getReporteReservasPorHuespedes();
    List<ReportePorHuesped> getReporteReservasPorHuespedes();

    @Query("Select r FROM Reserva r WHERE r.huesped.dni = :dni")
    // List<Reserva> findAllByReservaDniHuesped(@Param("dni") int dni);
    List<Reserva> findAllByReservaDniHuesped(int dni);
}
