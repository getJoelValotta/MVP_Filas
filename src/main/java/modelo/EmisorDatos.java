package modelo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class EmisorDatos {
    private String IP = "localhost";

    public void enviarDatos(long dni, int port) {
        try {
            Socket socket = new Socket(IP, port);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            //BufferedReader in = new BufferedReader(new
            //InputStreamReader(socket.getInputStream()));
            out.println(dni);
            out.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
