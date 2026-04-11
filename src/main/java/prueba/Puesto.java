package prueba;

import modelo.Llamados;
import controlador.ControladorOperador;
import interfaces.PuestoGUI;


public class Puesto {

    public static void main(String[] args) {
        Llamados modeloLlamados = new Llamados();
        modeloLlamados.iniciarLlamados();
        PuestoGUI vistaPuesto = new PuestoGUI();
        ControladorOperador controladorOperador = new ControladorOperador(vistaPuesto, modeloLlamados);
        vistaPuesto.setVisible(true);
    }

}
