package interfaces;

import javax.swing.*;
import java.awt.*;

public class MonitorGUI extends JFrame {

    private JLabel[] etiquetasLlamados;
    private static final int MAX_HISTORIAL = 5;

    public MonitorGUI() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Monitor de Llamados");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Panel principal con alineación vertical
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(40, 80, 40, 80));
        mainPanel.setBackground(Color.WHITE); // Fondo blanco para mayor contraste minimalista

        etiquetasLlamados = new JLabel[MAX_HISTORIAL];

        // Tamaño base de la fuente
        int fontSizeBase = 50;
        // Tamaño principal (10% más grande)
        int fontSizePrincipal = (int) (fontSizeBase * 1.10);

        for (int i = 0; i < MAX_HISTORIAL; i++) {
            etiquetasLlamados[i] = new JLabel(" "); // Espacio en blanco para que no colapse el layout
            etiquetasLlamados[i].setAlignmentX(Component.CENTER_ALIGNMENT);

            if (i == 0) {
                // Primer elemento: Cliente actual (Más grande y oscuro)
                etiquetasLlamados[i].setFont(new Font("SansSerif", Font.BOLD, fontSizePrincipal));
                etiquetasLlamados[i].setForeground(Color.BLACK);
            } else {
                // Siguientes 4: Historial (Tamaño base y grisado)
                etiquetasLlamados[i].setFont(new Font("SansSerif", Font.PLAIN, fontSizeBase));
                etiquetasLlamados[i].setForeground(Color.GRAY);
            }

            mainPanel.add(etiquetasLlamados[i]);

            // Espaciador entre las filas
            if (i < MAX_HISTORIAL - 1) {
                mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
            }
        }

        setContentPane(mainPanel);
        pack();
        // Forzamos un tamaño mínimo para que la ventana no "salte" al ingresar el primer DNI
        setMinimumSize(new Dimension(450, 500));
        setLocationRelativeTo(null);
    }

    // --- Método de exposición para el Controlador ---

    /**
     * Desplaza el historial hacia abajo e inserta el nuevo llamado en la cabecera.
     * @param nuevoDni El documento extraído por el Puesto de Atención.
     */
    public void registrarLlamado(String nuevoDni) {
        // Desplazamiento desde el final hacia el principio (índices 4 <- 3, 3 <- 2, etc.)
        for (int i = MAX_HISTORIAL - 1; i > 0; i--) {
            etiquetasLlamados[i].setText(etiquetasLlamados[i - 1].getText());
        }
        // Inserción en la posición 0 (Cliente actual)
        etiquetasLlamados[0].setText(nuevoDni);
    }
}