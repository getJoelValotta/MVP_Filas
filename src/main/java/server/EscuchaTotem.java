package server;

import java.net.ServerSocket;
import java.net.Socket;

import modelo.GestorFila;

public class EscuchaTotem implements Runnable {
    private GestorFila gestorFila;
    private int PORT = 777;
    private String IP = "localhost";

    public EscuchaTotem(GestorFila gestorFila) {
        this.gestorFila = gestorFila;
    }

    public void run() { // se inicia: new Thread(new EscuchaTotem()).start();)
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("EscuchaTotem escuchando en puerto " + PORT);
            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(new ManejadorCliente(socket, gestorFila)).start();
            }
        } catch (Exception e) {
            System.out.println("Error en EscuchaTotem: " + e.getMessage());
        } finally {
            try {
                if (serverSocket != null) {
                    System.out.println("Cerrando servidor EscuchaTotem...");
                    serverSocket.close();
                }
            } catch (Exception e) {
                System.out.println("Error al cerrar el servidor: " + e.getMessage());
            }
        }
    }

    public GestorFila getGestorFila() {
        return gestorFila;
    }

    public int getPORT() {
        return PORT;
    }

    public String getIP() {
        return IP;
    }

    public void setGestorFila(GestorFila gestorFila) {
        this.gestorFila = gestorFila;
    }

    public void setPORT(int pORT) {
        PORT = pORT;
    }

    public void setIP(String iP) {
        IP = iP;
    }

}
