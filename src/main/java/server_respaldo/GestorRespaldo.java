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

public class GestorRespaldo implements Runnable {
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
            while (true) {
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
                        System.err.println("Error al agregar cliente: " + e.getMessage());
                    }
                } else if (operacion.equals("llama")) {
                    try {
                        // Necesito almacenar el cliente por la naturaleza de la función. No lo uso.
                        @SuppressWarnings("unused")
                        Cliente temp = this.colaClientes.llamarSiguiente();
                    } catch (Exception e) {
                        System.err.println("Error al llamar al siguiente cliente: " + e.getMessage());
                    }
                } else if (operacion.equals("getCola")) {
                    try {
                        out.println(this.colaClientes.tamanio());
                        for (Cliente cliente : this.colaClientes.getCola()) {
                            out.println(cliente.getDni());
                        }
                    } catch (Exception e) {
                        System.err.println("Error al enviar la cola al servidor principal: " + e.getMessage());
                    }
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void arrancarComoServidor() {
        // TODO: ESTO SOLO FUNCIONA SI EL SERVIDOR PRINCIPAL SE TERMINÓ, SI ESTÁ
        // SATURADO, ESTOS PUESTOS QUE OCUPAN LOS
        // MÓDULOS ESTARÍAN OCUPADOS!!! DECIDIR QUE HACER
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

    // Importante para inicializacion de servidor principal a partir del respaldo
    public boolean colaVacia() {
        return this.colaClientes.tamanio() == 0;
    }
}
