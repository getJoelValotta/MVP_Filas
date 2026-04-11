package interfaces;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


public class PuestoGUI extends JFrame {
    private JLabel lblMensaje;
    private JButton btnLlamarSiguiente;
    private final String mensaje = "No hay clientes en espera";

    public PuestoGUI() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Puesto de Atención");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        lblMensaje = new JLabel(" "); // Inicializado con un espacio para que reserve el alto en el layout
        lblMensaje.setForeground(Color.RED); // Letra roja para errores
        lblMensaje.setFont(new Font("SansSerif", Font.BOLD, 14));

        // GridBagLayout centra automáticamente su único componente si no se le pasan constraints
        JPanel mainPanel = new JPanel(new GridBagLayout());
        
        // Margen amplio para el diseño minimalista
        mainPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        // Inicialización del botón principal
        btnLlamarSiguiente = new JButton("Llamar Siguiente");
        btnLlamarSiguiente.setFont(new Font("SansSerif", Font.BOLD, 22));
        btnLlamarSiguiente.setFocusPainted(false);
        btnLlamarSiguiente.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLlamarSiguiente.setPreferredSize(new Dimension(280, 80)); // Tamaño fijo e imponente

        // Ensamblado
        mainPanel.add(btnLlamarSiguiente);
        mainPanel.add(lblMensaje);

        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(null); // Centra la ventana
    }

    // --- Métodos de exposición para el Controlador ---

    public JButton getBtnLlamarSiguiente() {
        return btnLlamarSiguiente;
    }

    public void mostrarError() {
        lblMensaje.setForeground(Color.RED);
        lblMensaje.setText(mensaje);
    }

    public void limpiarMensaje() {
        lblMensaje.setText(" "); // Volver al espacio en blanco
    }

    // Método main exclusivo para testeo visual aislado
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PuestoGUI().setVisible(true);
        });
    }

    public void setActionListener(ActionListener controlador) {
        btnLlamarSiguiente.addActionListener(controlador);
    }
}

