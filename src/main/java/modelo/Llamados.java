package modelo;

import modelo.GestorFila;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import exceptions.*;
import modelo.Cliente;

public class Llamados {
    private GestorFila gestorFila;
    private int PORT = 777;
    private String IP = "localhost";

    public Llamados() {
        this.gestorFila = new GestorFila();
    }

    public void iniciarLlamados() { //Convertir a Runnable 
        Thread hiloLlamados = new Thread(() -> {
            String dniRecibido;
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            while (true) {
                Socket socket = serverSocket.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                dniRecibido = in.readLine();
                try {
                    Cliente cliente = new Cliente(dniRecibido);
                    gestorFila.agregarCliente(cliente);
                }
                catch (DniVacioException e) {
                    System.err.println("Error al procesar cliente: " + e.getMessage());
                }
                catch (DniInvalidoException e) {
                    System.err.println("Error al procesar cliente: " + e.getMessage());
                }
                in.close();
                socket.close();
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
            });
        hiloLlamados.start();
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
