package exceptions;

import modelo.GestorFila;

public class DniRepetidoException extends Exception {
    // private String dni;

    public DniRepetidoException(String dni) {
        super(GestorFila.msgA);
        // this.dni = dni;
    }
}
