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
        try {
            Socket socket = new Socket(IP, port);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            while(true){
                System.out.println("Esperando datos del monitor...");
                String dniRecibido = in.readUTF();
                System.out.println("Dni recibido: " + dniRecibido);
                String puesto = in.readUTF();
                System.out.println("Puesto recibido: " + puesto);
                controlador.accionRealizada(dniRecibido,puesto);
                System.out.println("Listo pa");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
