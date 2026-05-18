package modelo;

import java.io.BufferedReader;
import java.io.IOException;

import controlador.ControladorTotem;

public class EscuchaMonitorTotem extends Thread {
    private ControladorTotem controlador;
    private BufferedReader in;

    public EscuchaMonitorTotem(ControladorTotem controlador, BufferedReader in) {
        this.controlador = controlador;
        this.in = in;
    }

    @Override
    public void run() {
        try {
            String mensaje;
            while ((mensaje = in.readLine()) != null) {
                if ("CAMBIAR_SERVIDOR".equals(mensaje)) {
                    controlador.cambiarAServidorRespaldo();
                }
            }
        } catch (IOException e) {
            System.out.println("Se perdió la conexión con el monitor.");
        }
    }
}
