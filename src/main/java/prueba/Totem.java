package prueba;

import controlador.ControladorTotem;
import modelo.EmisorDatos;
import vistas.TotemGUI;

public class Totem {
    
    public static void main(String[] args) {
        TotemGUI vistaCliente = new TotemGUI();
        EmisorDatos registros = new EmisorDatos();
        ControladorTotem controladorCliente = new ControladorTotem(vistaCliente, registros);
        vistaCliente.setVisible(true);
    }
}
