package modelo;

import exceptions.DniInvalidoException;
import exceptions.DniVacioException;

public class Cliente {
    private long dni;
    public static final String msgA = "El DNI no puede estar vacío.";
    public static final String msgB = "El DNI solo puede contener números.";

    public Cliente(String dni) throws DniVacioException, DniInvalidoException {
        if (dni == null || dni.trim().isEmpty()) {
            throw new DniVacioException(dni);
        }
        if (!dni.matches("\\d+")) {
            throw new DniInvalidoException(dni);
        }
        this.dni = Long.parseLong(dni);
    }

    public long getDni() {
        return dni;
    }
}
