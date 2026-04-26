package server;

import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import modelo.GestorFila;

public class HablaMonitor extends Thread{
    private GestorFila gestorFila;
    private int PORT = 999;
    private String IP = "localhost";
    private Socket socketMonitor;
    private DataOutputStream out;

    public HablaMonitor(GestorFila gestorFila) {
        this.gestorFila = gestorFila;
    }

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            this.socketMonitor = serverSocket.accept();
            DataOutputStream out = new DataOutputStream(this.socketMonitor.getOutputStream());
        }
        catch (Exception e) {
            System.out.println("No se pudo conectar el monitor " + e);
        }
    }

    public void actualizaLLamado(Long dni, int numPuesto) {
        try {
            // ENVIA AL MONITOR UN STRING FORMATO "DNIXXXXX NUMPUESTO"
            out.writeUTF(Long.toString(dni));    
            out.writeUTF(Integer.toString(numPuesto));
        } catch (Exception e) {
            System.out.println("Error actualizando el monitor " + e);
        }
    }
}