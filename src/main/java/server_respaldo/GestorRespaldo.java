package server_respaldo;


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
    private GestorFila gestorFila;

    public GestorRespaldo(GestorFila gestorFila) {
        this.gestorFila = gestorFila;
    }

    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            Socket socket = serverSocket.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            while(true){
                String operacion = in.readLine();

                if (operacion.equals("agrega")) {
                    try {
                        String dniRecibido = in.readLine();
                        Cliente cliente = new Cliente(dniRecibido);
                        this.gestorFila.agregarCliente(cliente);
                    } catch (DniVacioException e) {

                    } catch (DniInvalidoException e) {

                    } catch (DniRepetidoException e) {
                        
                    }
                    
                } else if (operacion.equals("llama")) {
                    try {  
                        Cliente dniTemp = this.gestorFila.llamarSiguiente();
                    } catch (Exception e) {

                    }
                }

                
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    
}
