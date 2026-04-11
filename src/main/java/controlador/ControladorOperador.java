package controlador;

import java.awt.event.ActionListener;

import interfaces.PuestoGUI;
import modelo.Llamados;
import modelo.Cliente;
import modelo.EmisorDatos;

public class ControladorOperador implements ActionListener {
    private PuestoGUI vistaPuesto;
    private Llamados llamados;
    private EmisorDatos emisorDatos;
    private final int PORT_MONITOR = 1337;
    
    public ControladorOperador(PuestoGUI vistaPuesto, Llamados llamados) {
        this.vistaPuesto = vistaPuesto;
        this.llamados = llamados;
        this.emisorDatos = new EmisorDatos();
        this.vistaPuesto.setActionListener(this);
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        vistaPuesto.limpiarMensaje();
        vistaPuesto.getBtnLlamarSiguiente().setEnabled(false);
        try {
        Cliente cliente = llamados.getGestorFila().llamarSiguiente();
        emisorDatos.enviarDatos(cliente.getDni(), PORT_MONITOR);
        }
        catch (Exception ex) {
            vistaPuesto.mostrarError();
        }
        finally {
            vistaPuesto.getBtnLlamarSiguiente().setEnabled(true);
        }
    }

}
