package controlador;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;

import modelo.EscuchaMonitorPuesto;
import modelo.ModeloPuesto;
import server.EscuchaServerPuesto;
import vistas.PuestoGUI;

public class ControladorPuesto extends Thread implements ActionListener { 
    private PuestoGUI vistaPuesto;
    private ModeloPuesto modeloPuesto;
    private final int PORT_ESCUCHAPUESTO = 888;
    private final int PORT_RESPALDO = 2020;
    private final String IP = "localhost";
    private final String IP_RESPALDO = "localhost";

    public ControladorPuesto(PuestoGUI vistaPuesto) {
        this.vistaPuesto = vistaPuesto;
        this.modeloPuesto = new ModeloPuesto();
        this.vistaPuesto.setActionListener(this);
    }

    public void actionPerformed(java.awt.event.ActionEvent e) {

        switch (e.getActionCommand()) {
            case "LLAMAR":
                vistaPuesto.limpiarClienteActual();
                modeloPuesto.llamarCliente();
                break;
            case "RENOTIFICAR":
                modeloPuesto.reNotificar();
                break;
        }
    }

    public void conectarPuesto() {
        try {
            modeloPuesto.conectarAServer(IP, PORT_ESCUCHAPUESTO);
        } catch (IOException e) {
            System.out.println("Error conectando el puesto al servidor.");
        } finally {
            actualizaNumClientesVista(modeloPuesto.getNumClientes());
            actualizaNumPuesto(modeloPuesto.getNumPuesto());
        }

        try {
            BufferedReader inMonitor = modeloPuesto.conectarAMonitor(IP, PORT_RESPALDO);
            new EscuchaMonitorPuesto(this, inMonitor).start();
        } catch (IOException e) {
            System.out.println("Error conectando al monitor de respaldo.");
        }
    }

    public void atiendeDNI(String DNI) {
        modeloPuesto.setDNIActual(DNI);
        this.vistaPuesto.setClienteActual(Long.parseLong(DNI));
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

    public void actualizaNumPuesto(int numPuesto) {
        vistaPuesto.setNumPuesto(numPuesto);
    }

    public void actualizaNumClientesVista(int numClientes) {
        this.vistaPuesto.setCantClientes(numClientes);
    }

    public void arrancaPuesto() {
        // ARRANCA EL HILO QUE ESCUCHA AL SERVIDOR.
        new EscuchaServerPuesto(this).start();
    }


    public void cambiarAServidorRespaldo() {
    System.out.println("Cambiando al servidor de respaldo...");
    try {
        desconectarDelServer();
        modeloPuesto.conectarAServer(IP_RESPALDO, PORT_ESCUCHAPUESTO);
        arrancaPuesto(); // reinicia el hilo que escucha al servidor
    } catch (IOException e) {
        System.out.println("Error al conectar con el servidor de respaldo.");
    }
    }

}