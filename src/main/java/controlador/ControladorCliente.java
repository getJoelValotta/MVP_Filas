package controlador;

import java.awt.event.ActionListener;

import exceptions.DniInvalidoException;
import exceptions.DniVacioException;
import interfaces.ClienteGUI;
import modelo.EmisorDatos;
import modelo.Cliente;

public class ControladorCliente implements ActionListener {
    private ClienteGUI vistaCliente;
    private EmisorDatos registros;
    private int PORT = 777;

    public ControladorCliente(ClienteGUI vistaCliente, EmisorDatos registros) {
        this.vistaCliente = vistaCliente;
        this.registros = registros;
        this.vistaCliente.setActionListener(this);
    }


    //Nota: El manejo de estos errores debe ser manejado por EmisorDatos y Cliente. 
    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {

        String dni = vistaCliente.getDniIngresado();
        if (dni == null || dni.trim().isEmpty()) {
            vistaCliente.mostrarError(Cliente.msgA);
            return;
        }
        if (!dni.matches("\\d+")) {
            vistaCliente.mostrarError(Cliente.msgB);
            return;
        }
        long dniDepurado = Long.parseLong(dni);
        vistaCliente.mostrarExito("Dni Ingresado");
        registros.enviarDatos(dniDepurado, PORT);
        try {
         Thread.sleep(1000);
        }
        catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        vistaCliente.limpiarMensaje();
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