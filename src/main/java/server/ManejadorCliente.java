package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import exceptions.DniInvalidoException;
import exceptions.DniRepetidoException;
import exceptions.DniVacioException;
import modelo.Cliente;
import modelo.GestorFila;

public class ManejadorCliente implements Runnable {
    private Socket socket;
    private GestorFila gestorFila;

    public ManejadorCliente(Socket socket, GestorFila gestorFila) {
        this.socket = socket;
        this.gestorFila = gestorFila;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            while(true){
                String dniRecibido = in.readLine();
                System.out.println("DNI recibido: " + dniRecibido);
                try {
                    Cliente cliente = new Cliente(dniRecibido);
                    gestorFila.agregarCliente(cliente);
                    // Se  lo mando al respaldo.
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
            // TODO: Agregar manejo de cierre de conexión y recursos
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}