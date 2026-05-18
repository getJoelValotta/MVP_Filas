package prueba;

import java.io.BufferedReader;

import controlador.ControladorTotem;
import modelo.EmisorDatos;
import modelo.EscuchaMonitorTotem;
import vistas.TotemGUI;

public class Totem {
    
    public static void main(String[] args) {
        TotemGUI vistaCliente = new TotemGUI();
        EmisorDatos emisorDatos = new EmisorDatos();
        emisorDatos.conectarAServer("localhost", 777);
        ControladorTotem controladorTotem = new ControladorTotem(vistaCliente, emisorDatos);
    
        BufferedReader inMonitor = emisorDatos.conectarAMonitor();
        if (inMonitor != null) {
            new EscuchaMonitorTotem(controladorTotem, inMonitor).start();
        }
    
        vistaCliente.setVisible(true);
    }
}
