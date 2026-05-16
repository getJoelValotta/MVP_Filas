package prueba;

import modelo.GestorFila;
import server.EnviaHeartBeat;
import server.EscuchaPuesto;
import server.EscuchaTotem;
import server.HablaMonitor;

public class Server {

    public static void main(String[] args) {
        GestorFila colaClientes = new GestorFila();
        HablaMonitor hablaMonitor = new HablaMonitor(colaClientes);
        hablaMonitor.start();
        EscuchaPuesto escuchaPuesto = new EscuchaPuesto(colaClientes, hablaMonitor);
        EscuchaTotem escuchaTotem = new EscuchaTotem(colaClientes);
        new Thread(escuchaPuesto).start();
        new Thread(escuchaTotem).start();
        EnviaHeartBeat enviaHeartBeat = new EnviaHeartBeat();
        new Thread(enviaHeartBeat).start();
    }
}
