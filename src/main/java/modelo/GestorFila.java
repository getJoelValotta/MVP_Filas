package modelo;

import java.util.concurrent.LinkedBlockingQueue;
import server.EscuchaPuesto;

import exceptions.DniRepetidoException;

public class GestorFila { // TODO: PONER PUESTO AL QUE SE LLAMA
    public static final String msgA = "El DNI ya se encuentra registrado.";

    private LinkedBlockingQueue<Cliente> cola = null;

    private EscuchaPuesto escuchaPuesto = null;

    public GestorFila() {
        this.cola = new LinkedBlockingQueue<>();
    }

    public void setEscuchaPuesto(EscuchaPuesto escuchaPuesto) {
        this.escuchaPuesto = escuchaPuesto;
    }

    public void agregarCliente(Cliente cliente) throws DniRepetidoException {
        System.out.println("Agregando cliente con DNI: " + cliente.getDni());
        boolean dniRepetido = false;
        for (Cliente c : this.cola) {
            if (c.getDni() == cliente.getDni()) {
                dniRepetido = true;
                break;
            }
        }
        if (dniRepetido) {
            throw new DniRepetidoException(msgA);
        }
        System.out.println("Cliente con DNI " + cliente.getDni() + " agregado a la fila.");
        this.cola.add(cliente);
        this.escuchaPuesto.actualizarClientesEspera(cola.size());
    }

    public Cliente llamarSiguiente() throws Exception {
        try {
            if (cola.isEmpty()) {
                throw new Exception("No hay clientes en la fila");
            }
        } catch (Exception e) {
            throw e;
        }
        System.out.println("Llamando al siguiente cliente en la fila...");
        return cola.poll();
    }

    public int tamanio() {
        return cola.size();
    }
}