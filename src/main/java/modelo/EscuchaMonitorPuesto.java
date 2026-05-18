package modelo;

import java.io.BufferedReader;
import java.io.IOException;

import controlador.ControladorPuesto;


public class EscuchaMonitorPuesto extends Thread {
    private ControladorPuesto controlador;
    private BufferedReader in;

    public EscuchaMonitorPuesto(ControladorPuesto controlador, BufferedReader in) {
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