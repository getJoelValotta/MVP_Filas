package prueba;

import modelo.Llamados;
import controlador.ControladorPuesto;
import vistas.PuestoGUI;


public class Puesto {

    public static void main(String[] args) {
        PuestoGUI vistaPuesto = new PuestoGUI();
        ControladorPuesto controladorPuesto = new ControladorPuesto(vistaPuesto, modeloLlamados);
        vistaPuesto.setVisible(true);
    }

}
