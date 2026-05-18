package prueba;

import modelo.GestorFila;
import server.EnviaHeartBeat;
import server.EscuchaPuesto;
import server.EscuchaTotem;
import server.HablaGestor;
import server.HablaMonitor;

public class Server {

    public static void main(String[] args) {
        HablaGestor hablaGestor = new HablaGestor();
        GestorFila colaClientes = new GestorFila(hablaGestor);
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
