package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import exceptions.DniInvalidoException;
import exceptions.DniVacioException;
import modelo.Cliente;
import modelo.GestorFila;

//La usamos para avisarle a todos los puestos conectados al server un evento de su interes.

public class ManejaPuesto extends Thread {
    private Socket socket;
    private int numPuesto;
    private GestorFila gestorfila;
    private EscuchaPuesto escuchaPuesto;
    private DataInputStream inStream;
    private DataOutputStream outStream;

    public ManejaPuesto(Socket socket, int numPuesto, GestorFila gestorfila, EscuchaPuesto escuchaPuesto) {
        this.socket = socket;
        this.numPuesto = numPuesto;
        this.gestorfila = gestorfila;
        this.escuchaPuesto = escuchaPuesto;
        try {
            this.inStream = new DataInputStream(socket.getInputStream());
            this.outStream = new DataOutputStream(socket.getOutputStream());
        } catch (Exception e) {
            System.out.println("Error obteniendo los canales IO del puesto");
        }
    }

    // ESTE METODO ESTA HECHO PARA LLAMAR DESDE ESCUCHA PUESTO, PARA ACTUALIZAR A
    // LOS PUESTOS
    public synchronized void mandaNumClientesEspera(int numClientesEspera) {
        try {
            this.outStream.writeUTF(String.valueOf(numClientesEspera));
            this.outStream.flush();
        } catch (Exception e) {
            System.out.println("Error enviando el num de clientes al puesto " + numPuesto + ".");
        }
    }

    public void run() { // Esto se corre cuando se conecta a un nuevo puesto, y queda esuchando su
                        // output.
        String dniRecibido;
        System.out.println("Se ha conectado el puesto " + numPuesto + ".");
        try {
            this.outStream.writeUTF(String.valueOf(numPuesto)); // Le digo al puesto su numero
            while (true) {
                // RECIBE INPUT DEL PUESTO
                dniRecibido = inStream.readUTF();
                try {
                    Cliente cliente = new Cliente(dniRecibido);
                    this.gestorfila.agregarCliente(cliente);
                } catch (DniVacioException | DniInvalidoException e) {
                    System.err.println("Error al procesar cliente: " + e.getMessage());
                }

            }
        } catch (Exception e) {
            System.out.println("Error leyendo el puesto");
        } finally {
            try {
                this.socket.close();
            } catch (Exception e) {
                System.out.println("Error cerrando el puesto");
            }
            escuchaPuesto.sacaNumPuesto(numPuesto);
        }

    }
}