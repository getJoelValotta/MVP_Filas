package controlador;

import vistas.PuestoGUI;
import modelo.ModeloPuesto;
import java.io.DataInputStream;
import java.io.IOException;
import server.EscuchaServerPuesto;

public class ControladorPuesto extends Thread { /* implements ActionListener */
    private PuestoGUI vistaPuesto;
    private ModeloPuesto modeloPuesto;
    private final int PORT_ESCUCHAPUESTO = 888;

    public ControladorPuesto(PuestoGUI vistaPuesto) {
        this.vistaPuesto = vistaPuesto;
        this.modeloPuesto = new ModeloPuesto();
        // this.vistaPuesto.setActionListener(this);
    }

    public void conectarPuesto() {
        try {
            modeloPuesto.conectarAServer("localhost", PORT_ESCUCHAPUESTO);
        } catch (IOException e) {
            System.out.println("Error conectando al servidor.");
        } finally {
            actualizaNumClientesVista(modeloPuesto.getNumClientes());
        }

    }

    public void desconectarDelServer() throws IOException {
        modeloPuesto.desconectarDelServer();
    }

    public DataInputStream getInputStream() throws IOException {
        return modeloPuesto.getInputStream();
    }

    public void setNumClientesModelo(int numClientes) {
        modeloPuesto.setNumClientes(numClientes);
        actualizaNumClientesVista(numClientes);
    }

    public void actualizaNumClientesVista(int numClientes) {
        // TODO: MILI
    }

    public void main(String[] args) {
        // ARRANCA EL HILO QUE ESCUCHA AL SERVIDOR.
        new EscuchaServerPuesto(this).start();
    }

}
