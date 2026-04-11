package prueba;

import modelo.MonitorUltimosClientes;
import controlador.ControladorMonitor;
import interfaces.MonitorGUI;

public class Monitor {
    public static void main(String[] args) {
    MonitorGUI vistaMonitor = new MonitorGUI();
    // El controlador inicia el hilo del servidor automáticamente
    new ControladorMonitor(vistaMonitor); 
    vistaMonitor.setVisible(true);
}
}

