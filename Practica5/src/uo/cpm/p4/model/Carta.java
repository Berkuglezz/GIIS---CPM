package uo.cpm.p4.model;

import java.util.*;

import uo.cpm.p4.util.FileUtil;

public class Carta {

	private static final String FICHERO_ARTICULOS = "files/articulos.dat";
	private List<Articulo> listaArticulos = null;

	public Carta() {
		listaArticulos = new ArrayList<Articulo>();
		cargarArticulos();
	}

	private void cargarArticulos() {
		FileUtil.loadFile(FICHERO_ARTICULOS, listaArticulos);
	}

	public Articulo[] getArticulos() {
		Articulo[] articulos = listaArticulos.toArray(new Articulo[listaArticulos.size()]);
		return articulos;
	}

	public Articulo[] getArticulosFiltrados(String tipo) {
		List<Articulo> filtrados = new ArrayList<Articulo>();
		for (Articulo articulo : listaArticulos) {
			if (articulo.getTipo().equals(tipo)) {
				filtrados.add(articulo);
			}
		}
		return filtrados.toArray(new Articulo[filtrados.size()]);
	}
}
