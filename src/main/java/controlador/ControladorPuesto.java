package controlador;

import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.IOException;

import modelo.ModeloPuesto;
import server.EscuchaServerPuesto;
import vistas.PuestoGUI;

public class ControladorPuesto extends Thread implements ActionListener { /* implements ActionListener */
    private PuestoGUI vistaPuesto;
    private ModeloPuesto modeloPuesto;
    private final int PORT_ESCUCHAPUESTO = 888;

    public ControladorPuesto(PuestoGUI vistaPuesto) {
        this.vistaPuesto = vistaPuesto;
        this.modeloPuesto = new ModeloPuesto();
        this.vistaPuesto.setActionListener(this);
    }

    public void actionPerformed(java.awt.event.ActionEvent e) { 
        vistaPuesto.limpiarClienteActual();
        modeloPuesto.llamarCliente();
    }
    

    public void conectarPuesto() {
        try {
            modeloPuesto.conectarAServer("localhost", PORT_ESCUCHAPUESTO);
        } catch (IOException e) {
            System.out.println("Error conectando el puesto al servidor.");
        } finally {
            actualizaNumClientesVista(modeloPuesto.getNumClientes());
            actualizaNumPuesto(modeloPuesto.getNumPuesto());
        }

    }

    public void atiendeDNI(String DNI){
        modeloPuesto.setDNIActual(DNI);
        
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
    public void setNumPuesto(int numPuesto) {
        modeloPuesto.setNumPuesto(numPuesto);
        actualizaNumPuesto(numPuesto);
    }
    public void actualizaNumPuesto(int numPuesto){
        vistaPuesto.setNumPuesto(numPuesto);
    }

    public void actualizaNumClientesVista(int numClientes) {
        this.vistaPuesto.setCantClientes(numClientes);
    }

    public void arrancaPuesto() {
        // ARRANCA EL HILO QUE ESCUCHA AL SERVIDOR.
        new EscuchaServerPuesto(this).start();
    }

}