package modelo;

import java.util.concurrent.LinkedBlockingQueue;

import exceptions.DniRepetidoException;
import exceptions.ColaVaciaException;
import server.EscuchaPuesto;
import server.HablaGestor;

public class GestorFila { // TODO: PONER PUESTO AL QUE SE LLAMA
    public static final String msgA = "El DNI ya se encuentra registrado.";
    public HablaGestor hablaGestor;
    public boolean respaldo;

    private LinkedBlockingQueue<Cliente> cola = null;

    private EscuchaPuesto escuchaPuesto = null;

    public GestorFila(HablaGestor hablaGestor) {
        this.hablaGestor = hablaGestor;
        this.cola = new LinkedBlockingQueue<>();
        respaldo = false;
    }

    public GestorFila() {
        respaldo = true;
        this.cola = new LinkedBlockingQueue<>();
    }

    public void setEscuchaPuesto(EscuchaPuesto escuchaPuesto) {
        this.escuchaPuesto = escuchaPuesto;
    }

    public void agregarCliente(Cliente cliente) throws DniRepetidoException {
        System.out.println("Agregando cliente con DNI: " + cliente.getDni());
        boolean dniRepetido = false;
        if (!respaldo) {
            hablaGestor.enviaDNI(cliente.getDni());
        }
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
        if (this.escuchaPuesto != null) {
            this.escuchaPuesto.actualizarClientesEspera(cola.size());
        }
    }

    public Cliente llamarSiguiente() throws ColaVaciaException {
        try {
            if (cola.isEmpty()) {
                throw new ColaVaciaException();
            }
            if (!respaldo) {
                hablaGestor.llamaSiguiente();
            }
        } catch (Exception e) {
            throw e;
        }
        System.out.println("Llamando al siguiente cliente en la fila...");
        if (this.escuchaPuesto != null) {
            this.escuchaPuesto.actualizarClientesEspera(cola.size() - 1);
        }
        return cola.poll();
    }

    public int tamanio() {
        return cola.size();
    }
}