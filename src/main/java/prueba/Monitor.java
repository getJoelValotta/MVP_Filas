package prueba;

import controlador.ControladorMonitor;
import modelo.ReceptorDatosMonitor;
import vistas.MonitorGUI;

public class Monitor {
    public static void main(String[] args) {
        MonitorGUI vistaMonitor = new MonitorGUI();
        ControladorMonitor controladorMonitor = new ControladorMonitor(vistaMonitor); 
        ReceptorDatosMonitor receptor = new ReceptorDatosMonitor(controladorMonitor);
        new Thread (receptor).start();
        vistaMonitor.setVisible(true);
    }
}

