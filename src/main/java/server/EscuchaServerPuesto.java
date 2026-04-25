package server;

import java.io.DataInputStream;
import controlador.ControladorPuesto;
import modelo.ModeloPuesto;

public class EscuchaServerPuesto extends Thread {
    ControladorPuesto controladorPuesto;

    public EscuchaServerPuesto(ControladorPuesto controladorPuesto) {
        this.controladorPuesto = controladorPuesto;
    }

    public void run() {
        try {
            controladorPuesto.conectarPuesto();
            DataInputStream in = controladorPuesto.getInputStream();
            while (true) {
                String numClientesEsperaStr = in.readUTF();
                int numClientesEspera = Integer.parseInt(numClientesEsperaStr);
                controladorPuesto.setNumClientes(numClientesEspera);
            }

        } catch (Exception e) {
            System.out.println("Error obteniendo el input stream del servidor.");
        } finally {
            try {
                controladorPuesto.desconectarDelServer();
            } catch (Exception e) {
                System.out.println("Error desconectando del servidor.");
            }
        }

    }
}
