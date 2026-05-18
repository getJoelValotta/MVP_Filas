package prueba;

import modelo.GestorFila;
import server_respaldo.EscuchaHeartBeat;
import server_respaldo.GestorRespaldo;
import server_respaldo.MonitorRespaldo;

public class ServerRespaldo {

    public static void main(String[] args) {
        GestorFila colaClientes = new GestorFila();
        GestorRespaldo gestorRespaldo = new GestorRespaldo(colaClientes);
        MonitorRespaldo monitorRespaldo = new MonitorRespaldo(gestorRespaldo);
        new Thread(monitorRespaldo).start();
        EscuchaHeartBeat escuchaHeartBeat = new EscuchaHeartBeat(monitorRespaldo);
        new Thread(gestorRespaldo).start();
        new Thread(escuchaHeartBeat).start();
    }
}
