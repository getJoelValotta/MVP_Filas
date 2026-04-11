package interfaces;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ClienteGUI extends JFrame {

    private JTextField txtDni;
    private JButton btnRegistrar;
    private JLabel lblMensaje;

    public ClienteGUI() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Terminal Cliente");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Panel contenedor con FlowLayout (alinea de izquierda a derecha centrando el contenido)
        // Los parámetros 15, 15 son los márgenes (gaps) horizontales y verticales.
        JPanel mainPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        
        // Padding invisible para que no quede pegado a los bordes de la ventana
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Inicialización de componentes
        txtDni = new JTextField(15);
        txtDni.setFont(new Font("SansSerif", Font.PLAIN, 18));
        txtDni.setToolTipText("Ingrese DNI sin puntos");

        btnRegistrar = new JButton("Registrar");
        btnRegistrar.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnRegistrar.setFocusPainted(false); // Elimina el recuadro de foco nativo de Java
        btnRegistrar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        lblMensaje = new JLabel(" "); 
        lblMensaje.setHorizontalAlignment(SwingConstants.CENTER); // Centrado
        lblMensaje.setFont(new Font("SansSerif", Font.BOLD, 14));

        // Ensamblado
        mainPanel.add(new JLabel("DNI:"));
        mainPanel.add(txtDni);
        mainPanel.add(btnRegistrar);
        mainPanel.add(lblMensaje, BorderLayout.SOUTH);

        setContentPane(mainPanel);
        pack(); // Ajusta automáticamente el tamaño del JFrame al contenido de sus paneles
        setLocationRelativeTo(null); // Centra la ventana en la pantalla
    }

    // --- Métodos de exposición para el Controlador ---

    public void mostrarError(String mensaje) {
        lblMensaje.setForeground(Color.RED);
        lblMensaje.setText(mensaje);
    }

    public void mostrarExito(String mensaje) {
        lblMensaje.setForeground(Color.GREEN);
        lblMensaje.setText(mensaje);
    }

    public void limpiarMensaje() {
        lblMensaje.setText(" ");
    }

    public String getDniIngresado() {
        return txtDni.getText().trim();
    }

    public JButton getBtnRegistrar() {
        return btnRegistrar;
    }

    public void limpiarCampo() {
        txtDni.setText("");
    }

    // Método main exclusivo para testeo visual aislado
    public static void main(String[] args) {
        // Ejecución segura en el Event Dispatch Thread de Swing
        SwingUtilities.invokeLater(() -> {
            new ClienteGUI().setVisible(true);
        });
    }

    public void setActionListener(ActionListener controlador) {
        btnRegistrar.addActionListener(controlador);
    }
    
}