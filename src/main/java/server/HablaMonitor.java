package server;

import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class HablaMonitor extends Thread {
    private int PORT = 999;
    // private String IP = "localhost";
    private Socket socketMonitor;
    private DataOutputStream out;

    public HablaMonitor() {
    }

    @Override
    public void run() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);
            this.socketMonitor = serverSocket.accept();
            this.out = new DataOutputStream(this.socketMonitor.getOutputStream());
        } catch (Exception e) {
            System.out.println("No se pudo conectar el monitor " + e);
        } finally {
            // Cerramos el serverSocket, ya que ya cumplió su función (aceptar la conexión
            // del monitor)
            // El socketMonitor se mantiene abierto para enviar datos al monitor
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
            out.writeUTF(Long.toString(dni));
            out.writeUTF(Integer.toString(numPuesto));
            System.out.println("Ahi mande al monitor el dni " + Long.toString(dni) + " y el puesto "
                    + Integer.toString(numPuesto));
        } catch (Exception e) {
            System.out.println("Error actualizando el monitor " + e);
        }
    }
}