package exceptions;

public class ColaVaciaException extends Exception {
    public ColaVaciaException() {
        super("No hay clientes en la fila");
    }
}