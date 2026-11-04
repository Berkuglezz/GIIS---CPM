package uo.cpm.p6.model;

import uo.cpm.p6.model.Casilla.TipoCasilla;

public class Tablero {

	public static final int DIM = 8;
	Casilla[] casillas;
	private int posicionInvasor;

	public Tablero() {
		casillas = new Casilla[DIM];
		for (int i = 0; i < DIM; i++)
			casillas[i] = new Casilla("Espacio", TipoCasilla.ESPACIO, -200, "/img/space.jpg");
		colocaInvasor();
		colocaMeteorito();
	}

	private void colocaInvasor() {
		posicionInvasor = (int) (Math.random() * DIM);
		casillas[posicionInvasor] = new Casilla("Invasor", TipoCasilla.INVASOR, 3000, "/img/invader.jpg");
		System.out.println("Posicion invasor = " + posicionInvasor);
	}

	private void colocaMeteorito() {
		int posicionMeteorito;
		do {
			posicionMeteorito = (int) (Math.random() * DIM);
		} while (posicionMeteorito == posicionInvasor);
		casillas[posicionMeteorito] = new Casilla("Meteorito", TipoCasilla.METEORITO, 0, "/img/meteorite.jpg");
		System.out.println("Posicion meteorito = " + posicionMeteorito);
	}

	public Casilla[] getCasillas() {
		return casillas;
	}

	public String obtenerImagen(int i) {
		return casillas[i].getImagen();
	}
}
