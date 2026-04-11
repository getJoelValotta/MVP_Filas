package modelo;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;


public class GestorFila {

    private Queue<Cliente> cola = null;

    public GestorFila() {
        this.cola = new LinkedBlockingQueue<>();
    }

    public void agregarCliente(Cliente cliente) {
        cola.offer(cliente);
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