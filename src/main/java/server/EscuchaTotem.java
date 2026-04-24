package server;

import java.net.ServerSocket;
import java.net.Socket;

import modelo.GestorFila;

public class EscuchaTotem implements Runnable{
    private GestorFila gestorFila;
    private int PORT = 777;
    private String IP = "localhost";

    public EscuchaTotem(GestorFila gestorFila) {
        this.gestorFila = gestorFila;
    }

    public void run() { // se inicia: new Thread(new EscuchaTotem()).start();)
        String dniRecibido;
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(new ManejadorCliente(socket, gestorFila)).start();
                socket.close();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
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
