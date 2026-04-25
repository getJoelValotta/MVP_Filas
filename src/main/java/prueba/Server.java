package prueba;

import modelo.GestorFila;
import server.EscuchaPuesto;
import server.EscuchaTotem;
import server.HablaMonitor;

public class Server {

    public static void main(String[] args) {
        GestorFila colaClientes = new GestorFila();
        EscuchaPuesto escuchaPuesto = new EscuchaPuesto(colaClientes);
        HablaMonitor hablaMonitor = new HablaMonitor(colaClientes);
        hablaMonitor.start();
        new Thread(escuchaPuesto).start();
        // EscuchaTotem escuchaTotem = new EscuchaTotem(colaClientes);
        // new Thread(escuchaTotem).start();
    }
}
