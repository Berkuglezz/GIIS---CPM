package uo.cpm.p8.iu;

import java.awt.Component;
import java.io.File;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

/**
 * Pinta cada canción de una JList mostrando solo su nombre, sin la ruta ni la
 * extensión (".../Music/Waves.mp3" se ve como "Waves"). El modelo sigue
 * guardando el File completo.
 */
public class SongRenderer extends DefaultListCellRenderer {

	private static final long serialVersionUID = 1L;

	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
		Object texto = value;
		if (value instanceof File)
			texto = nombreCancion((File) value);
		return super.getListCellRendererComponent(list, texto, index, isSelected, cellHasFocus);
	}

	public static String nombreCancion(File f) {
		String nombre = f.getName();
		int punto = nombre.lastIndexOf('.');
		return punto > 0 ? nombre.substring(0, punto) : nombre;
	}
}
