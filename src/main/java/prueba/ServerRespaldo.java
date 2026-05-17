package prueba;

import modelo.GestorFila;
import server_respaldo.EscuchaHeartBeat;
import server_respaldo.GestorRespaldo;

public class ServerRespaldo {

    public static void main(String[] args) {
        GestorFila colaClientes = new GestorFila();
        EscuchaHeartBeat escuchaHeartBeat = new EscuchaHeartBeat();
        GestorRespaldo gestorRespaldo = new GestorRespaldo(colaClientes);
        new Thread(gestorRespaldo).start();
        new Thread(escuchaHeartBeat).start();
    }
}
