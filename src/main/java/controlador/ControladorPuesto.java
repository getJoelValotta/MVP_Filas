package controlador;

import java.awt.event.ActionListener;

import vistas.PuestoGUI;
import modelo.Cliente;
import modelo.EmisorDatos;

public class ControladorPuesto implements ActionListener {
    private PuestoGUI vistaPuesto;
    private EmisorDatos emisorDatos;
    //private final int PORT_MONITOR = 1337;            //comentado porque no se si lo necesita
    
    public ControladorPuesto(PuestoGUI vistaPuesto) {
        this.vistaPuesto = vistaPuesto;
        this.emisorDatos = new EmisorDatos();
        //this.vistaPuesto.setActionListener(this);
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
