package uo.cpm.calculadora;

import java.awt.EventQueue;

import javax.swing.UIManager;

import uo.cpm.calculadora.model.Calculadora;
import uo.cpm.calculadora.ui.VentanaPrincipal;

public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// Metal respeta los colores de fondo de los botones en cualquier sistema
					UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
					VentanaPrincipal frame = new VentanaPrincipal(new Calculadora());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
