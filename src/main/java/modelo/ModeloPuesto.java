package modelo;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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
    private Integer cantLLamadas;
    private Socket socketMonitor;

    public ModeloPuesto() {
        this.socket = null;
        this.socketMonitor = null;
        this.numPuesto = -1;
        this.numClientesEsperando = -1;
        this.DNI = "";
    }

    // Da output en el socket, que es recibido por el SERVER, en manejaPuesto.java,
    // entonces, el server se encarga de llamar cliente.

    public void conectarAServer(String ipServer, int puerto) throws IOException {
        this.socket = new Socket(ipServer, puerto);
    }

    public BufferedReader conectarAMonitor(String ipServer, int puerto) throws IOException {
        this.socketMonitor = new Socket(ipServer, puerto);
        BufferedReader inMonitor = new BufferedReader(new InputStreamReader(socketMonitor.getInputStream()));
        return inMonitor;
        
    }

    public void setDNIActual(String DNI) {
        System.out.println("Puesto " + numPuesto + " atendiendo a cliente con DNI: " + DNI);
        this.DNI = DNI;
    }

    public void desconectarDelServer() throws IOException {
        socket.close();
        System.out.println("Desconectado del servidor.");
    }

    public void llamarCliente() {
        // Intenta 3 veces y sino manda error
        DataOutputStream out;
        int cantIntentos = 0;
        while(cantIntentos<3){
            try {
                out = new DataOutputStream(socket.getOutputStream());
                out.writeUTF("SIG");
                this.cantLLamadas = 0;
                break;
            } catch (IOException e) {
                cantIntentos++;
                if(cantIntentos < 3){
                    System.out.println("Error enviando llamando al cliente al puesto" + numPuesto + ". Reintentando..."); 
                    try{
                        Thread.sleep(1000);
                    } catch (InterruptedException ie){
                        Thread.currentThread().interrupt();
                        break;
                    }
                } else
                    System.out.println("Falló el llamado del puesto " + numPuesto + ".");
            }
        }
    }

   public void reNotificar() {
        if (this.cantLLamadas < 3) {
            int intentos = 0;
            while (intentos < 3) {
                try {
                    DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                    out.writeUTF("REN");
                    out.writeUTF(DNI);
                    this.cantLLamadas += 1;
                    break;
                } catch (IOException e) {
                    intentos++;
                    if (intentos < 3) {
                        System.out.println("Error renotificando puesto " + numPuesto + ". Reintentando...");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException ie) {
                            Thread.currentThread().interrupt();
                            break;
                        }
                    } else {
                        System.out.println("Falló la renotificación del puesto " + numPuesto + ".");
                    }
                }
            }
        }
    }
    public DataInputStream getInputStream() throws IOException {
        return new DataInputStream(socket.getInputStream());
    }

    public void setNumClientes(int numClientes) {
        this.numClientesEsperando = numClientes;
    }

    public void setNumPuesto(int numPuesto) {
        this.numPuesto = numPuesto;
    }

    public int getNumClientes() {
        return this.numClientesEsperando;
    }

    public int getNumPuesto() {
        return this.numPuesto;
    }

    
}
