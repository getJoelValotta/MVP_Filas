package prueba;

import modelo.GestorFila;
import server.EnviaHeartBeat;
import server.EscuchaPuesto;
import server.EscuchaTotem;
import server.HablaGestor;
import server.HablaMonitor;
import java.io.IOException;
import java.util.List;

public class Server {

    public static void main(String[] args) {
        HablaGestor hablaGestor = new HablaGestor();
        GestorFila colaClientes;
        List<Long> dniCola = null;
        try {
            dniCola = hablaGestor.getCola();
        } catch (IOException e) {
            System.err.println("Error al obtener la cola desde el servidor de respaldo: " + e.getMessage());
        }
        if (dniCola.isEmpty()) {
            colaClientes = new GestorFila(hablaGestor);
        } else {
            colaClientes = new GestorFila(hablaGestor, dniCola);
        }
        HablaMonitor hablaMonitor = new HablaMonitor();
        EscuchaPuesto escuchaPuesto = new EscuchaPuesto(colaClientes, hablaMonitor);
        EscuchaTotem escuchaTotem = new EscuchaTotem(colaClientes);
        EnviaHeartBeat enviaHeartBeat = new EnviaHeartBeat();
        hablaMonitor.start();
        new Thread(escuchaPuesto).start();
        new Thread(escuchaTotem).start();
        new Thread(enviaHeartBeat).start();
    }
}
