package prueba;

import modelo.GestorFila;
import server.EscuchaPuesto;
import server.EscuchaTotem;
import server.HablaMonitor;

public class ServerRespaldo {

    public static void main(String[] args) {
        GestorFila colaClientes = new GestorFila();
        HablaMonitor hablaMonitor = new HablaMonitor(colaClientes);
        EscuchaPuesto escuchaPuesto = new EscuchaPuesto(colaClientes, hablaMonitor);
        EscuchaTotem escuchaTotem = new EscuchaTotem(colaClientes);
    }
}
