package modelo;

import java.util.concurrent.LinkedBlockingQueue;
import exceptions.*;
import server.EscuchaPuesto;
import server.HablaGestor;
import java.util.List;

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

    // Constructor para la reconstruccion de la fila en el servidor principal a
    // partir del respaldo
    public GestorFila(HablaGestor hablaGestor, List<Long> colaDNI) {
        this.hablaGestor = hablaGestor;
        respaldo = false;
        this.cola = new LinkedBlockingQueue<>();
        for (Long dni : colaDNI) {
            try {
                Cliente cliente = new Cliente(Long.toString(dni));
                this.cola.add(cliente);
            } catch (DniVacioException | DniInvalidoException e) {
                System.err.println("DNI invalido recuperado desde el server de resplado: " + e.getMessage());
            }
        }
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

    public LinkedBlockingQueue<Cliente> getCola() {
        return cola;
    }

}