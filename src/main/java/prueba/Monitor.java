package prueba;

import controlador.ControladorMonitor;
import modelo.ReceptorDatosMonitor;
import vistas.MonitorGUI;

public class Monitor {
    public static void main(String[] args) {
    MonitorGUI vistaMonitor = new MonitorGUI();
    ReceptorDatosMonitor receptor = new ReceptorDatosMonitor();
    new Thread(receptor).start();
    new ControladorMonitor(vistaMonitor); 
    vistaMonitor.setVisible(true);
}
}

