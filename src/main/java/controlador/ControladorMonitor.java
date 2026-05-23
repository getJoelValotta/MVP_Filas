package controlador;

import javax.swing.SwingUtilities;

import interfaces.EscuchadorDeSocket;
//import modelo.ReceptorDatosMonitor;
import vistas.MonitorGUI;

public class ControladorMonitor implements EscuchadorDeSocket {

    private MonitorGUI vista;
    // private ReceptorDatosMonitor receptorDatosMonitor;
    // private final int PORT = 999;

    public ControladorMonitor(MonitorGUI vista) {
        this.vista = vista;
    }

    public void accionRealizada(String dniRecibido, String puesto) {
        if (dniRecibido != null && !dniRecibido.isEmpty() && puesto != null && !puesto.isEmpty()) {
            // Sincronizamos con el hilo de la GUI para actualizar las etiquetas
            SwingUtilities.invokeLater(() -> {
                vista.registrarLlamado(dniRecibido, puesto);
            });
        }
    }

}