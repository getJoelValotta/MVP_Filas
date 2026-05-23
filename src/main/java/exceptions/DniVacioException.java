package exceptions;

import modelo.Cliente;

public class DniVacioException extends Exception {
    // private String dni;

    public DniVacioException(String dni) {
        super(Cliente.msgA);
        // this.dni = dni;
    }
}
