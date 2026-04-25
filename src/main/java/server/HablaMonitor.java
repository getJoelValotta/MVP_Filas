package server;

import java.net.ServerSocket;
import java.net.Socket;

import modelo.GestorFila;

public class HablaMonitor extends Thread{
    private GestorFila gestorFila;
    private int PORT = 999;
    private String IP = "localhost";

    public HablaMonitor(GestorFila gestorFila) {
        this.gestorFila = gestorFila;
    }

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            Socket socket = serverSocket.accept();
            while (true) {
                socket.close();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}