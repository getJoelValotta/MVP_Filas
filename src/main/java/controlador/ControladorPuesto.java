package controlador;

import java.awt.event.ActionListener;

import vistas.PuestoGUI;
import modelo.Cliente;
import modelo.EmisorDatos;
import modelo.ModeloPuesto;
import java.io.DataInputStream;

public class ControladorPuesto extends Thread { /* implements ActionListener */
    private PuestoGUI vistaPuesto;
    private ModeloPuesto modeloPuesto;
    private final int PORT_ESCUCHAPUESTO = 888; // comentado porque no se si lo
    // necesita

    public ControladorPuesto(PuestoGUI vistaPuesto) {
        this.vistaPuesto = vistaPuesto;
        this.modeloPuesto = new ModeloPuesto();
        // this.vistaPuesto.setActionListener(this);
    }

    public void run() { // Puede estar mal? Hice un thread que corre para recibir input del server en el
                        // controlador. revisar.
        try {
            modeloPuesto.conectarAServer("localhost", PORT_ESCUCHAPUESTO);
        } catch (Exception e) {
            System.out.println("Error conectando al servidor.");
        }
        try {
            DataInputStream in = modeloPuesto.getInputStream();
            while (true) {
                String numClientesEsperaStr = in.readUTF();
                int numClientesEspera = Integer.parseInt(numClientesEsperaStr);
                modeloPuesto.setNumClientes(numClientesEspera);
                // TODO: vistaPuesto.mostrarNumClientesEspera(numClientesEspera);
            }

        } catch (Exception e) {
            System.out.println("Error obteniendo el input stream del servidor.");
        } finally {
            try {
                modeloPuesto.desconectarDelServer();
            } catch (Exception e) {
                System.out.println("Error desconectando del servidor.");
            }
        }

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
