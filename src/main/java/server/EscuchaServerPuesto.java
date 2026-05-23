package server;

import java.io.DataInputStream;

import controlador.ControladorPuesto;

/// RECIBE TODO EL INPUT PARA EL PUESTO
public class EscuchaServerPuesto extends Thread {
    ControladorPuesto controladorPuesto;

    public EscuchaServerPuesto(ControladorPuesto controladorPuesto) {
        this.controladorPuesto = controladorPuesto;
    }

    public void run() {
        String inputString;
        try {
            controladorPuesto.conectarPuesto();
            DataInputStream in = controladorPuesto.getInputStream();
            while (true) {
                inputString = in.readUTF();
                if (inputString.equals("CLI")) {
                    int numClientesEspera = Integer.parseInt(in.readUTF());
                    System.out.println("Puestos tienen " + numClientesEspera
                            + " clientes esperando.");
                    controladorPuesto.setNumClientesModelo(numClientesEspera);
                } else if (inputString.equals("DNI")) {
                    String DNI = in.readUTF();
                    controladorPuesto.atiendeDNI(DNI);
                } else if (inputString.equals("PUE")) {
                    int numPuesto = Integer.parseInt(in.readUTF());
                    controladorPuesto.setNumPuesto(numPuesto);

                } else {
                    System.out.println("Codigo desconocido: " + inputString);
                    // Consume el siguiente UTF para evitar desincronización
                    @SuppressWarnings("unused")
                    String buffer = in.readUTF();
                }

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
