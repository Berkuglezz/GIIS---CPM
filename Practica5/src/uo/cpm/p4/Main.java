package uo.cpm.p4;

import java.awt.EventQueue;

import javax.swing.UIManager;

import uo.cpm.p4.service.McDonalds;
import uo.cpm.p4.ui.VentanaPrincipal;

public class Main {

	public static void main(String[] args) {
		
		McDonalds macdonalds = new McDonalds();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
					VentanaPrincipal frame = new VentanaPrincipal(macdonalds);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});		
	}
}
