package modelo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class EmisorDatos {
    private String IP = "localhost";

    public boolean enviarDNI(long dni, int port) {
        boolean respuesta = false;
        try {
            Socket socket = new Socket(IP, port);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out.println(dni);
            respuesta = Boolean.parseBoolean(in.readLine());
            out.close();
            in.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respuesta;
    }

    public long enviarSolicitudSiguiente(int port) {
        Long respuesta = null;
        try {
            Socket socket = new Socket(IP, port);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            respuesta = Long.parseLong(in.readLine());
            out.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respuesta;
    }
}
