package server;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import exceptions.DniInvalidoException;
import exceptions.DniRepetidoException;
import exceptions.DniVacioException;
import modelo.Cliente;
import modelo.GestorFila;

public class GestorRespaldo implements Runnable{
    private final int PORT = 1010;
    private Socket socket;
    private GestorFila gestorFila;

    public void GestionRespaldo(Socket socket, GestorFila gestorFila) {
        this.socket = new Socket()  
        this.gestorFila = gestorFila;
    }

    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            Socket socket = serverSocket.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            while(true){
                String dniRecibido = in.readLine();
                try {
                    Cliente cliente = new Cliente(dniRecibido);
                    gestorFila.agregarCliente(cliente);
                    out.println("true");
                } catch (DniVacioException e) {
                    System.err.println("Error al procesar cliente: " + e.getMessage());
                } catch (DniInvalidoException e) {
                    System.err.println("Error al procesar cliente: " + e.getMessage());
                } catch (DniRepetidoException e) {
                    out.println("false");
                    System.err.println("Error al procesar cliente: " + e.getMessage());
                }
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    
}
