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

    public void conectarPuesto() throws IOException {
        modeloPuesto.conectarAServer("localhost", PORT_ESCUCHAPUESTO);
    }

    public void desconectarDelServer() throws IOException {
        modeloPuesto.desconectarDelServer();
    }

    public DataInputStream getInputStream() throws IOException {
        return modeloPuesto.getInputStream();
    }

    public void setNumClientes(int numClientes) {
        modeloPuesto.setNumClientes(numClientes);
        // TODO: actualizar vista
    }

    public void main(String[] args) {
        // ARRANCA EL HILO QUE ESCUCHA AL SERVIDOR.
        new EscuchaServerPuesto(this).start();
    }

    /*
     * @Override
     * public void actionPerformed(java.awt.event.ActionEvent e) {
     * vistaPuesto.limpiarMensaje();
     * vistaPuesto.getBtnLlamarSiguiente().setEnabled(false);
     * try {
     * Cliente cliente = llamados.getGestorFila().llamarSiguiente();
     * emisorDatos.enviarDatos(cliente.getDni(), PORT_MONITOR);
     * }
     * catch (Exception ex) {
     * vistaPuesto.mostrarError();
     * }
     * finally {
     * vistaPuesto.getBtnLlamarSiguiente().setEnabled(true);
     * }
     * }
     */

}
