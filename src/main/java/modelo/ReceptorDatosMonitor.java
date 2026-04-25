package modelo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
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
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while(true){
                String dniRecibido = in.readLine();
                String puesto = in.readLine();
                controlador.accionRealizada(dniRecibido,puesto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
