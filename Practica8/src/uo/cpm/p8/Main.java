package uo.cpm.p8;

import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.jtattoo.plaf.hifi.HiFiLookAndFeel;

import uo.cpm.p8.iu.VentanaPrincipal;
import uo.cpm.p8.player.MusicPlayer;

public class Main {

	private static final String FICHERO_PROPIEDADES = "/properties/jtattoo.properties";

	public static void main(String[] args) {
		MusicPlayer mP = new MusicPlayer();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					HiFiLookAndFeel.setCurrentTheme(cargarPropiedades());
					UIManager.put("Slider.focus", Color.BLACK); // para que no se vea el foco amarillo en el slider
					UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
					VentanaPrincipal v = new VentanaPrincipal(mP);
					v.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Lee el fichero de propiedades de JTattoo (logotipo del menú y color del
	 * foco). Si no se encuentra, se usan las propiedades por defecto del tema.
	 */
	private static Properties cargarPropiedades() {
		Properties props = new Properties();
		try (InputStream in = Main.class.getResourceAsStream(FICHERO_PROPIEDADES)) {
			if (in != null)
				props.load(in);
			else
				props.put("logoString", "");
		} catch (IOException e) {
			props.put("logoString", "");
		}
		return props;
	}
}
