package server_respaldo;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import modelo.Cliente;
import modelo.GestorFila;
import server.EscuchaPuesto;
import server.EscuchaTotem;
import server.HablaMonitor;

public class GestorRespaldo implements Runnable{
    private final int PORT = 1010;
    private GestorFila colaClientes;

    public GestorRespaldo(GestorFila colaClientes) {
        this.colaClientes = colaClientes;
    }

    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            Socket socket = serverSocket.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            while(true){
                String operacion = in.readLine();

                if (operacion == null) { // conexión cerrada
                    System.out.println("Gestor desconectado.");
                    break;
                }

                if (operacion.equals("agrega")) {
                    try {
                        String dniRecibido = in.readLine();
                        Cliente cliente = new Cliente(dniRecibido);
                        this.colaClientes.agregarCliente(cliente);
                    } catch (Exception e) {

                    }
                } else if (operacion.equals("llama")) {
                    try {  
                        Cliente dniTemp = this.colaClientes.llamarSiguiente();
                    } catch (Exception e) {

                    }
                }

                
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void arrancarComoServidor() {
        // Arranca los mismos componentes que el servidor principal
        HablaMonitor hablaMonitor = new HablaMonitor();
        EscuchaPuesto escuchaPuesto = new EscuchaPuesto(colaClientes, hablaMonitor);
        EscuchaTotem escuchaTotem = new EscuchaTotem(colaClientes);

        colaClientes.setEscuchaPuesto(escuchaPuesto);

        hablaMonitor.start();
        new Thread(escuchaPuesto).start();
        new Thread(escuchaTotem).start();

        // No arranca EnviaHeartBeat porque el respaldo 
        // no necesita enviar heartbeat a nadie
        
        System.out.println("Servidor de respaldo activo.");
    }
}

    
    

