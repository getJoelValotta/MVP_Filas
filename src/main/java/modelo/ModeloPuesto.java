package modelo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ModeloPuesto {
    // Instancia de puesto, va a tener:
    // - numero de puesto, numero de clientes esperando
    // - metodo para llamar siguiente cliente
    // - un socket
    private int numPuesto;
    private Socket socket;
    public int numClientesEsperando;
    private String DNI;

    public ModeloPuesto() {
        this.socket = null;
        this.numPuesto = -1;
        this.numClientesEsperando = -1;
        this.DNI = "";
    }

    // Da output en el socket, que es recibido por el SERVER, en manejaPuesto.java,
    // entonces, el server se encarga de llamar cliente.

    public void conectarAServer(String ipServer, int puerto) throws IOException {
        this.socket = new Socket(ipServer, puerto);
    }

    public void setDNIActual(String DNI){
        this.DNI = DNI;
    }

    public void desconectarDelServer() throws IOException {
        socket.close();
        System.out.println("Desconectado del servidor.");
    }

    public void llamarCliente() {
        try {
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF("SIG");
        } catch (IOException e) {
            System.out.println("Error enviando información del puesto " + numPuesto + ".");
        }
    }

    public void reNotificar() {
        try {
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF("REN");
            out.writeUTF(DNI);
            
        } catch (IOException e) {
            System.out.println("Error renotificando puesto " + numPuesto + ".");
        }
    }

    public DataInputStream getInputStream() throws IOException {
        return new DataInputStream(socket.getInputStream());
    }

    public void setNumClientes(int numClientes) {
        this.numClientesEsperando = numClientes;
    }
    public void setNumPuesto(int numPuesto){
        this.numPuesto = numPuesto;
    }

    public int getNumClientes() {
        return this.numClientesEsperando;
    }

    public int getNumPuesto() {
        return this.numPuesto;
    }
}
