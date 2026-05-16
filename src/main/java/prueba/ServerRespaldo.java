package prueba;

import modelo.GestorFila;
import server.EscuchaHeartBeat;
import server.GestorRespaldo;

public class ServerRespaldo {

    public static void main(String[] args) {
        GestorFila colaClientes = new GestorFila();
        EscuchaHeartBeat escuchaHeartBeat = new EscuchaHeartBeat();
        GestorRespaldo gestorRespaldo = new GestorRespaldo();
        new Thread(gestorRespaldo).start();
        new Thread(escuchaHeartBeat).start();
       
    }
}
