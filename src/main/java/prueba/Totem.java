package prueba;

import controlador.ControladorCliente;
import modelo.EmisorDatos;
import interfaces.ClienteGUI;

public class Totem {
    
    public static void main(String[] args) {
        ClienteGUI vistaCliente = new ClienteGUI();
        EmisorDatos registros = new EmisorDatos();
        ControladorCliente controladorCliente = new ControladorCliente(vistaCliente, registros);
        vistaCliente.setVisible(true);
    }
}
