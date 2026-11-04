package uo.cpm.p6.model;

import uo.cpm.p6.model.Casilla.TipoCasilla;

public class Tablero {

	private int dim;
	Casilla[] casillas;
	private int nInvasores;
	private int nMeteoritos;

	public Tablero(int numCasillas, int numInvasores, int numMeteoritos) {
		this.dim = numCasillas;
		this.nInvasores = numInvasores;
		this.nMeteoritos = numMeteoritos;
		
		casillas = new Casilla[dim];
		for (int i = 0; i < dim; i++)
			casillas[i] = new Casilla("Espacio", TipoCasilla.ESPACIO, -200, "/img/space.jpg");
		colocaInvasores();
		colocaMeteoritos();
	}

	private void colocaInvasores() {
		int posicionInvasor;
		for (int i = 0; i < nInvasores; i++) {
			do
				posicionInvasor = (int) (Math.random() * dim);
			while (!casillas[posicionInvasor].getTipoCasilla().equals(TipoCasilla.ESPACIO));
			casillas[posicionInvasor] = new Casilla("Invasor", TipoCasilla.INVASOR, 3000, "/img/invader.jpg");
			System.out.println("Posicion invasor = " + posicionInvasor);
		}
	}

	private void colocaMeteoritos() {
		int posicionMeteorito;
		for (int i = 0; i < nMeteoritos; i++) {
			do
				posicionMeteorito = (int) (Math.random() * dim);
			while (!casillas[posicionMeteorito].getTipoCasilla().equals(TipoCasilla.ESPACIO));
			casillas[posicionMeteorito] = new Casilla("Meteorito", TipoCasilla.METEORITO, 0, "/img/meteorite.jpg");
			System.out.println("Posicion meteorito = " + posicionMeteorito);
		}
	}

	public Casilla[] getCasillas() {
		return casillas;
	}

	public String obtenerImagen(int i) {
		return casillas[i].getImagen();
	}

	public int getDimension() {
		// TODO Auto-generated method stub
		return dim;
	}
	
	
}
