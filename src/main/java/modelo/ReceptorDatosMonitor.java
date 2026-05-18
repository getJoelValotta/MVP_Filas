package modelo;

import java.io.DataInputStream;
import java.net.Socket;

import interfaces.EscuchadorDeSocket;
public class ReceptorDatosMonitor implements Runnable {
    private String IP = "localhost";
    private int port = 999;
    private EscuchadorDeSocket controlador;

    public ReceptorDatosMonitor(EscuchadorDeSocket controlador){
        this.controlador = controlador;
    }

    public void run() {
    while (true) {
        try {
            Socket socket = new Socket(IP, port);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            System.out.println("Conectado al servidor.");
            while (true) {
                String dniRecibido = in.readUTF();
                String puesto = in.readUTF();
                controlador.accionRealizada(dniRecibido, puesto);
            }
        } catch (Exception e) {
            System.out.println("Se perdió conexión con el servidor, reintentando...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }
}
}
