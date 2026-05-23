package server;

import java.io.PrintWriter;
//import java.lang.reflect.Array;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;

public class HablaGestor {
    // HablaGestor = Habla a GestorRespaldo del paquete server_respaldo
    private final String IP = "localhost";
    private final int PORT = 1010;
    private Socket socket;

    public HablaGestor() {
        try {
            this.socket = new Socket(IP, PORT);
        } catch (Exception e) {
            System.err.println("Error al conectar con el servidor de respaldo: " + e.getMessage());
        }

    }

    public void enviaDNI(Long dni) {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println("agrega");
            out.println(dni);
        } catch (Exception e) {
            System.err.println("Error al enviar el DNI al servidor de respaldo: " + e.getMessage());
        }
    }

    public void llamaSiguiente() {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println("llama");
        } catch (Exception e) {
            System.err.println("Error al llamar al siguiente cliente: " + e.getMessage());
        }
    }

    public List<Long> getCola() throws IOException {
        List<Long> listaDni = new ArrayList<>();
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println("getCola");
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            int tamanio = Integer.parseInt(in.readLine());
            for (int i = 0; i < tamanio; i++) {
                listaDni.add(Long.parseLong(in.readLine()));
            }
            return listaDni;
        } catch (IOException e) {
            System.err.println("Error obteniendo la cola desde el servidor de respaldo: " + e.getMessage());
            throw new IOException("Error obteniendo la cola desde el servidor de respaldo", e);
        }
    }
}
