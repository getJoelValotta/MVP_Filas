package server;

import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;

public class HablaMonitor extends Thread {
    private int PORT = 999;
    // private String IP = "localhost";
    private Socket socketMonitor;
    private LinkedBlockingQueue<DataOutputStream> colaMonitores;

    public HablaMonitor() {
    }

    @Override
    public void run() {
        ServerSocket serverSocket = null;
        colaMonitores = new LinkedBlockingQueue<>();
        try {
            serverSocket = new ServerSocket(PORT);
            while (true) {
                this.socketMonitor = serverSocket.accept();
                DataOutputStream out = new DataOutputStream(this.socketMonitor.getOutputStream());
                colaMonitores.put(out);
            }
        } catch (Exception e) {
            System.out.println("No se pudo conectar el monitor " + e);
        } finally {
            try {
                if (serverSocket != null) {
                    serverSocket.close();
                }
            } catch (Exception e) {
                System.out.println("Error al cerrar el servidor: " + e.getMessage());
            }
        }
    }

    public void actualizaLLamado(Long dni, int numPuesto) {
        try {
            for (DataOutputStream out : colaMonitores) {
                out.writeUTF(Long.toString(dni));
                out.writeUTF(Integer.toString(numPuesto));
            }
            System.out.println("Ahi mande a monitor/es el dni " + Long.toString(dni) + " y el puesto "
                    + Integer.toString(numPuesto));
        } catch (Exception e) {
            System.out.println("Error actualizando el/los monitor/es " + e);
        }
    }
}