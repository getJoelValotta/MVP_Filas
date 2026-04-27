package modelo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class EmisorDatos {
    private String IP = "localhost";
    private int port = 777;
    private Socket socket;

    public void  conectarAServer() {
        try {
            this.socket = new Socket(IP, port);
        } catch (Exception e) {
            System.out.println("Error conectando al servidor (Totem).");
        }
        
    }

    public boolean enviarDNI(long dni) {
        boolean respuesta = false;
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out.println(dni);
            respuesta = Boolean.parseBoolean(in.readLine());
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respuesta;
    }

    
}
