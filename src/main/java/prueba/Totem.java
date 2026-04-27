package prueba;

import controlador.ControladorTotem;
import modelo.EmisorDatos;
import vistas.TotemGUI;

public class Totem {
    
    public static void main(String[] args) {
        TotemGUI vistaCliente = new TotemGUI();
        EmisorDatos emisorDatos = new EmisorDatos();
        emisorDatos.conectarAServer();
        ControladorTotem controladorTotem = new ControladorTotem(vistaCliente, emisorDatos);
        vistaCliente.setVisible(true);
    }
}
