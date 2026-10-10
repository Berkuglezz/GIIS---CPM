package uo.cpm.p3.util;

import java.io.*;
import java.util.*;

import uo.cpm.p3.model.Articulo;
import uo.cpm.p3.model.Pedido;

public abstract class FileUtil {

	public static void loadFile(String nombreFicheroEntrada, List<Articulo> listaCatalogo) {

		String linea;
		String[] datosArticulo = null;

		try {
			BufferedReader fichero = new BufferedReader(new FileReader(nombreFicheroEntrada));
			while (fichero.ready()) {
				linea = fichero.readLine();
				datosArticulo = linea.split("@");
				listaCatalogo.add(new Articulo(datosArticulo[0], datosArticulo[1], datosArticulo[2],
						Float.parseFloat(datosArticulo[3]), 0));
			}
			fichero.close();
		} catch (FileNotFoundException fnfe) {
			System.out.println("El archivo no se ha encontrado.");
		} catch (IOException ioe) {
			new RuntimeException("Error de entrada/salida.");
		}
	}

	public static void saveToFile(Pedido pedido) { 
		try {
			BufferedWriter fichero = new BufferedWriter(new FileWriter("files/" + pedido.getCodigo() + ".dat"));
			fichero.write(pedido.getListaPedido().toString());
			fichero.close();
		}

		catch (FileNotFoundException fnfe) {
			System.out.println("El archivo no se ha podido guardar");
		} catch (IOException ioe) {
			new RuntimeException("Error de entrada/salida");
		}
	}
}
