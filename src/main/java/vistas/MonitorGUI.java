package vistas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.Timer;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MonitorGUI extends JFrame {

    private JLabel[] lblTurnos;
    private JLabel[] lblPuestos;
    private JPanel[] panelesFila;
    private static final int MAX_HISTORIAL = 5;

    public MonitorGUI() {
        setLayout(new GridLayout(MAX_HISTORIAL, 2, 5, 5));
        this.setSize(400,700);
        this.setLocationRelativeTo(null);
        lblTurnos = new JLabel[MAX_HISTORIAL];
        lblPuestos = new JLabel[MAX_HISTORIAL];
        panelesFila = new JPanel[MAX_HISTORIAL * 2]; // Para guardar los paneles y cambiarles el color

        int panelIndex = 0;

        for (int i = 0; i < MAX_HISTORIAL; i++) {
            // PUESTO (Columna Izquierda)
            JPanel panelPuesto = new JPanel();
            lblPuestos[i] = new JLabel(i == 0 ? "PUESTO" : "-"); // El primero puede estar vacío al inicio
            lblPuestos[i].setFont(new Font("Tahoma", i == 0 ? Font.BOLD : Font.PLAIN, i == 0 ? 30 : 20));
            panelPuesto.add(lblPuestos[i]);
            add(panelPuesto);
            panelesFila[panelIndex++] = panelPuesto;

            // TURNO (Columna Derecha)
            JPanel panelTurno = new JPanel();
            lblTurnos[i] = new JLabel(i == 0 ? "TURNO" : "-");
            lblTurnos[i].setFont(new Font("Tahoma", i == 0 ? Font.BOLD : Font.PLAIN, i == 0 ? 40 : 25));
            panelTurno.add(lblTurnos[i]);
            add(panelTurno);
            panelesFila[panelIndex++] = panelTurno;
        }
    }

    public void registrarLlamado(String dni, String puesto) {
        int posicionExistente = -1;
        int i=0;
        int limite;

        // 1. Buscar si el DNI ya está en pantalla
        while (i < MAX_HISTORIAL && posicionExistente == -1) {
            if (lblTurnos[i].getText().equals(dni)) {
                posicionExistente = i; 
            }
            i++;
        }

        
        limite = (posicionExistente != -1) ? posicionExistente : MAX_HISTORIAL - 1;

        for (i = limite; i > 0; i--) {
            lblTurnos[i].setText(lblTurnos[i - 1].getText());
            lblPuestos[i].setText(lblPuestos[i - 1].getText());
        }

        lblTurnos[0].setText(dni);
        lblPuestos[0].setText(puesto);

        ejecutarAnimacionPulso(lblTurnos[0], lblPuestos[0]);
    }

    private void ejecutarAnimacionPulso(JLabel labelDni, JLabel labelPuesto) {
        Font fuenteOriginal = new Font("Tahoma", Font.BOLD, 15); 
        Font fuenteGrande = new Font("Tahoma", Font.BOLD, 25);   
        
        Timer timer = new Timer(200, null); 
        timer.addActionListener(new ActionListener() {
            int contador = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                if (contador % 2 == 0) {
                    labelDni.setFont(fuenteGrande);
                    labelPuesto.setFont(fuenteGrande);
                } else {
                    labelDni.setFont(fuenteOriginal);
                    labelPuesto.setFont(fuenteOriginal);
                }
                
                contador++;
                if (contador >= 6) { 
                    timer.stop();
                    labelDni.setFont(fuenteOriginal); 
                    labelPuesto.setFont(fuenteOriginal);
                }
            }
        });
        timer.start();
    }
}
