package prueba;

import controlador.ControladorPuesto;
import vistas.PuestoGUI;

public class Puesto {

    public static void main(String[] args) {
        PuestoGUI vistaPuesto = new PuestoGUI();
        ControladorPuesto controladorPuesto = new ControladorPuesto(vistaPuesto);
        controladorPuesto.arrancaPuesto();
        vistaPuesto.setVisible(true);
    }

}
