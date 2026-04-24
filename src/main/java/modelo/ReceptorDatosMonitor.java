package modelo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReceptorDatosMonitor implements Runnable {
    private String IP = "localhost";
    private int port = 1111;

    public void run() {
        try {
            Socket socket = new Socket(IP, port);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while(true){
                String dniRecibido = in.readLine();
                String puesto = in.readLine();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
