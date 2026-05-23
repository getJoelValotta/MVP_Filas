package modelo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

//EMISOR DATOS = ENVIADOR DE DATOS A SERVER DESDE TOTEM
public class EmisorDatos {
    // private String IP = "localhost";
    // private int port = 777;
    private Socket socket;
    private Socket socketMonitor;
    private final int PUERTO_RESPALDO = 2020;
    private final String IP_RESPALDO = "localhost";
    private PrintWriter out;
    private BufferedReader in;

    public EmisorDatos() {
        this.socket = null;
        this.socketMonitor = null;
    }

    public void conectarAServer(String ip, int puerto) {
        int intentos = 0;
        while (intentos < 5) {
            try {
                System.out.println("Intento " + (intentos + 1) + " conectando a " + ip + ":" + puerto);
                this.socket = new Socket(ip, puerto);
                this.out = new PrintWriter(socket.getOutputStream(), true);
                this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                System.out.println("Totem conectado a " + ip + ":" + puerto);
                return;
            } catch (Exception e) {
                intentos++;
                System.out.println("Reintentando conexión... " + intentos);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ie) {
                    break;
                }
            }
        }
        System.out.println("No se pudo conectar al servidor.");
    }

    public BufferedReader conectarAMonitor() {
        try {
            this.socketMonitor = new Socket(IP_RESPALDO, PUERTO_RESPALDO);
            return new BufferedReader(new InputStreamReader(socketMonitor.getInputStream()));
        } catch (Exception e) {
            System.out.println("Error conectando al monitor de respaldo (Totem).");
            return null;
        }
    }

    public boolean enviarDNI(long dni) {
        boolean respuesta = false;
        int intentos = 0;
        while (intentos < 3) {
            try {
                // PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                // BufferedReader in = new BufferedReader(new
                // InputStreamReader(socket.getInputStream()));
                out.println(dni);
                respuesta = Boolean.parseBoolean(in.readLine());
                break;
            } catch (Exception e) {
                intentos++;
                System.out.println("Excepción: " + e.getMessage());
                if (intentos < 3) {
                    System.out.println("Error enviando DNI desde el totem. Reintentando...");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                } else {
                    System.out.println("Falló el envío del DNI al puesto. Revisar servidor e intentar de nuevo.");
                }
            }
        }
        return respuesta;
    }

}
