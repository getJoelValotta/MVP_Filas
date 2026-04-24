package vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class PuestoGUI extends JPanel {
	private JPanel panelAuxBtn;
	private JPanel panelAuxLblLista;
	private JLabel lblCantClientesEspera;
	private JPanel panelAuxLlamar;
	private JPanel panelAuxReLlamar;
	private JButton btnLlamar;
	private JButton btnRenotificar;


	public PuestoGUI() {
		setLayout(new BorderLayout(0, 0));
		
		this.panelAuxBtn = new JPanel();
		add(this.panelAuxBtn, BorderLayout.CENTER);
		this.panelAuxBtn.setLayout(new GridLayout(0, 2, 0, 0));
		
		this.panelAuxLlamar = new JPanel();
		this.panelAuxBtn.add(this.panelAuxLlamar);
		this.panelAuxLlamar.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		this.btnLlamar = new JButton("Llamar siguiente");
		this.panelAuxLlamar.add(this.btnLlamar);
		
		this.panelAuxReLlamar = new JPanel();
		this.panelAuxBtn.add(this.panelAuxReLlamar);
		
		this.btnRenotificar = new JButton("Re-notificar");
		this.panelAuxReLlamar.add(this.btnRenotificar);
		
		this.panelAuxLblLista = new JPanel();
		add(this.panelAuxLblLista, BorderLayout.SOUTH);
		
		this.lblCantClientesEspera = new JLabel("");
		this.lblCantClientesEspera.setFont(new Font("Segoe UI Variable", Font.PLAIN, 21));
		this.panelAuxLblLista.add(this.lblCantClientesEspera);
	}
	
	public void setCantClientes(int clientes) {
		this.lblCantClientesEspera.setText("Hay "+ clientes +" en cola");
		this.lblCantClientesEspera.setForeground(Color.BLACK);
	}
	
	public void inhabilitarBtn() { //para cuando no haya clientes en cola
		this.btnLlamar.setEnabled(false);
		this.btnRenotificar.setEnabled(false);
	}

}
