package uo296497.cpm.p1.ui;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
//Very important to quit warnings
public class VentanaPrincipal extends JFrame{
	private JPanel panelPrincipal;
	private JButton btAcceptar;
	private JButton btCancelar;

	
	private static final long serialVersionUID = 1L;

	public VentanaPrincipal() {
		this.setBounds(100,100,450,300);
		this.setTitle("prueba");
		
		panelPrincipal = new JPanel();
		panelPrincipal.setBackground(Color.BLUE);
		panelPrincipal.setLayout(null);
		this.setContentPane(panelPrincipal);
		
		btAcceptar = new JButton();
		btAcceptar.setBounds(170,220,100,30);
		btAcceptar.setText("Acceptar");
		panelPrincipal.add(btAcceptar);
		btAcceptar.setForeground(Color.BLUE);
		btAcceptar.setBackground(Color.green);
		
		btCancelar = new JButton();
		btCancelar.setBounds(130,180,100,30);
		btCancelar.setText("Cancelar");
		panelPrincipal.add(btCancelar);
		btCancelar.setForeground(Color.RED);
		btCancelar.setBackground(Color.BLACK);
		
	}
	
	public static void main(String[] args) {
		VentanaPrincipal vp = new VentanaPrincipal();
		vp.setVisible(true);// TODO Viene de fabrica en falso
		
	}

}
