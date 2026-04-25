package modelo;

import java.util.concurrent.LinkedBlockingQueue;

import exceptions.DniRepetidoException;

public class GestorFila { // TODO: PONER PUESTO AL QUE SE LLAMA
    public static final String msgA = "El DNI ya se encuentra registrado.";

    private LinkedBlockingQueue<Cliente> cola = null;

    public GestorFila() {
        this.cola = new LinkedBlockingQueue<>();
    }

    public void agregarCliente(Cliente cliente) throws DniRepetidoException {
        if (this.cola.contains(cliente)) {
            throw new DniRepetidoException(msgA);
        }
        this.cola.add(cliente);
    }

    public Cliente llamarSiguiente() throws Exception {
        try {
            if (cola.isEmpty()) {
                throw new Exception("No hay clientes en la fila");
            }
        } catch (Exception e) {
            throw e;
        }
        return cola.poll();
    }

    public int tamanio() {
        return cola.size();
    }
}