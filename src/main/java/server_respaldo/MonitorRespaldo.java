package server_respaldo;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class MonitorRespaldo implements Runnable {
    private final int PORT = 2020;
    private final List<PrintWriter> clientes = new ArrayList<>();
    private GestorRespaldo gestorRespaldo;

    public MonitorRespaldo(GestorRespaldo gestorRespaldo) {
        this.gestorRespaldo = gestorRespaldo;
    }

    // Los tótems se conectan aca al arrancar
    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Monitor esperando conexiones...");
            while (true) {
                Socket socket = serverSocket.accept();
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                synchronized (clientes) {
                    clientes.add(out);
                }
                System.out.println("Cliente registrado. Total: " + clientes.size());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // EscuchaHeartBeat llama a este método cuando detecta la caída
    public void notificarCaida() {
        System.out.println("Avisando a " + clientes.size() + " clientes...");
        gestorRespaldo.arrancarComoServidor();
        synchronized (clientes) {
            for (PrintWriter out : clientes) {
                out.println("CAMBIAR_SERVIDOR");
            }
        }
    }

}

    
