package modelo;

import java.io.DataInputStream;
import java.net.Socket;

import interfaces.EscuchadorDeSocket;

public class ReceptorDatosMonitor implements Runnable {
    private String IP = "localhost";
    private int port = 999;
    private EscuchadorDeSocket controlador;

    public ReceptorDatosMonitor(EscuchadorDeSocket controlador) {
        this.controlador = controlador;
    }

    public void run() {
        Socket socket = null;
        while (true) {
            try {
                socket = new Socket(IP, port);
                DataInputStream in = new DataInputStream(socket.getInputStream());
                System.out.println("Conectado al servidor.");
                while (true) {
                    String dniRecibido = in.readUTF();
                    String puesto = in.readUTF();
                    controlador.accionRealizada(dniRecibido, puesto);
                }
            } catch (Exception e) {
                System.out.println("Se perdió conexión con el servidor");
            } finally {
                try {
                    if (socket != null) {
                        System.out.println("Cerrando socket. (ReceptorDatosMonitor)...");
                        socket.close();
                    }
                } catch (Exception e) {
                    System.out.println("Error al cerrar la conexión con el servidor");
                }
                try {
                    System.out.println("Intentando reconectar en 3...");
                    Thread.sleep(1000);
                    System.out.println("Intentando reconectar en 2...");
                    Thread.sleep(1000);
                    System.out.println("Intentando reconectar en 1...");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("Error durante el tiempo de espera para reconectar: " + e.getMessage());
                }
            }
        }
    }
}
