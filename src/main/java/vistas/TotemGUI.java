package vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class TotemGUI extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panelAuxCampoDNI;
	private JPanel panelAuxTxtDNI;
	private JPanel panelAuxBtnRegistrar;
	private JTextField campoDNI;
	private JTextPane textDNI;
	private JButton btnRegistrar;
	private JPanel panelAuxTxtGuia;
	private JLabel labelGuia;
	public TotemGUI() {
		setLayout(new BorderLayout(0, 0));
		
		this.panelAuxBtnRegistrar = new JPanel();
		add(this.panelAuxBtnRegistrar, BorderLayout.SOUTH);
		
		this.btnRegistrar = new JButton("Registrar");
		this.panelAuxBtnRegistrar.add(this.btnRegistrar);
		
		this.panelAuxCampoDNI = new JPanel();
		add(this.panelAuxCampoDNI, BorderLayout.CENTER);
		
		this.panelAuxTxtDNI = new JPanel();
		this.panelAuxCampoDNI.add(this.panelAuxTxtDNI);
		
		this.textDNI = new JTextPane();
		this.textDNI.setText("DNI");
		this.panelAuxTxtDNI.add(this.textDNI);
		
		this.campoDNI = new JTextField();
		this.panelAuxCampoDNI.add(this.campoDNI);
		this.campoDNI.setColumns(10);
		
		this.panelAuxTxtGuia = new JPanel();
		add(this.panelAuxTxtGuia, BorderLayout.NORTH);
		
		this.labelGuia = new JLabel("");
		this.panelAuxTxtGuia.add(this.labelGuia);
	}
	
	public void setGuiaIngresar(String guia) {
		this.labelGuia.setText(guia);
		this.labelGuia.setForeground(Color.BLACK);
	}
	public void setGuiaError(String guia) {
		this.labelGuia.setText(guia);
		this.labelGuia.setForeground(Color.RED);
	}
	
	public void setGuiaExito(String guia) {
		this.labelGuia.setText(guia);
		this.labelGuia.setForeground(Color.GREEN);
	}
	
	public void setListener(ActionListener actionListener) {
		this.btnRegistrar.addActionListener(actionListener);
	}
	
	public String getDNI() {
		return this.campoDNI.getText().trim();
	}

	public void limpiaDNI(){
		this.campoDNI.setText("");

	}

    public void setActionListener(ActionListener controlador) {
        btnRegistrar.addActionListener(controlador);
    }

}
