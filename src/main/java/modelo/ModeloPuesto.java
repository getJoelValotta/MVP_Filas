package modelo;

import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.*;

import javax.xml.crypto.Data;

public class ModeloPuesto {
    // Instancia de puesto, va a tener:
    // - numero de puesto, numero de clientes esperando
    // - metodo para llamar siguiente cliente
    // - un socket
    private int numeroPuesto;
    private Socket socket;
    public int numClientesEsperando;

    public ModeloPuesto() {
        this.socket = null;
        this.numeroPuesto = -1;
        this.numClientesEsperando = -1;
    }

    // Da output en el socket, que es recibido por el SERVER, en manejaPuesto.java,
    // entonces, el server se encarga de llamar cliente.

    public void conectarAServer(String ipServer, int puerto) throws IOException {
        this.socket = new Socket(ipServer, puerto);
        DataInputStream in = new DataInputStream(socket.getInputStream());
        this.numeroPuesto = Integer.parseInt(in.readUTF());
        System.out.println("Conectado al servidor. Numero de puesto asignado: " + numeroPuesto);
    }

    public void desconectarDelServer() throws IOException {
        socket.close();
        System.out.println("Desconectado del servidor.");
    }

    public void llamarCliente(String dni) {
        try {
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.writeBytes(dni + "\n");
        } catch (IOException e) {
            System.out.println("Error enviando información del puesto " + numeroPuesto + ".");
        }
    }

    public DataInputStream getInputStream() throws IOException {
        return new DataInputStream(socket.getInputStream());
    }

    public void setNumClientes(int numClientes) {
        this.numClientesEsperando = numClientes;
    }

    public int getNumPuesto() {
        return this.numeroPuesto;
    }
}
