package ar.com.ada.api.exepciones;

import ar.com.ada.api.entities.*;

/**
 * HuespedEdadException
 */
public class HuespedEdadException extends HuespedInfoException {

    public HuespedEdadException(Huesped huesped, String mensaje) {
        super(huesped, mensaje);
    }

}