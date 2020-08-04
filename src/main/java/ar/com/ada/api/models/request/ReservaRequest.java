package ar.com.ada.api.models.request;

import java.math.BigDecimal;
import java.util.Date;

import ar.com.ada.api.entities.Huesped;

public class ReservaRequest {

    public boolean isOk = false;
    public Date fechaActual;
    public Date fechaIngreso;
    public Date fechaEgreso;
    public BigDecimal importeTotal;
    public BigDecimal importeReserva;
    public int huespedId;
    public String message = "";

}