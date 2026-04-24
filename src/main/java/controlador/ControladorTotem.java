package controlador;

import java.awt.event.ActionListener;

import modelo.Cliente;
import modelo.EmisorDatos;
import vistas.TotemGUI;

public class ControladorTotem implements ActionListener {
    private TotemGUI vistaCliente;
    private EmisorDatos emisorDatos;
    private int PORT = 777;

    public ControladorTotem(TotemGUI vistaCliente, EmisorDatos emisorDatos) {
        this.vistaCliente = vistaCliente;
        this.emisorDatos = emisorDatos;
        this.vistaCliente.setListener(this);
    }


    //Nota: El manejo de estos errores debe ser manejado por EmisorDatos y Cliente. 
    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        boolean respuesta;
        String dni = vistaCliente.getDNI();
        if (dni == null || dni.trim().isEmpty()) {
            vistaCliente.setGuiaError(Cliente.msgA);
            return;
        }
        if (!dni.matches("\\d+")) {
            vistaCliente.setGuiaError(Cliente.msgB);
            return;
        }
        long dniDepurado = Long.parseLong(dni);
        vistaCliente.setGuiaExito("Dni Ingresado");
        respuesta = emisorDatos.enviarDNI(dniDepurado, PORT);
        if (respuesta == false){
            vistaCliente.setGuiaError("El DNI ya se encuentra registrado.");
        }
        try {
         Thread.sleep(1000);
        }
        catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        vistaCliente.limpiaDNI();
    }   
        
}



/*
vistaCliente.getBtnRegistrar().addActionListener(e -> {
    String dni = vistaCliente.getDniIngresado();
    // 1. Validar nulos o caracteres [cite: 19]
    // 2. Armar el socket emisor y mandar el ticket
    // 3. vistaCliente.limpiarCampo(); -> deberia informar si el envio fue exitoso o no y limpiar el mensaje luego de 5 seg
});        
*/