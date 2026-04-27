package prueba;

import modelo.GestorFila;
import server.EscuchaPuesto;
import server.EscuchaTotem;
import server.HablaMonitor;

public class Server {

    public static void main(String[] args) {
        GestorFila colaClientes = new GestorFila();
        HablaMonitor hablaMonitor = new HablaMonitor(colaClientes);
        EscuchaPuesto escuchaPuesto = new EscuchaPuesto(colaClientes, hablaMonitor);
        EscuchaTotem escuchaTotem = new EscuchaTotem(colaClientes);
        hablaMonitor.start();
        new Thread(escuchaPuesto).start();
        new Thread(escuchaTotem).start();
    }
}
