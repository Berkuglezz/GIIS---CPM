package uo.cpm.p6.game;

import uo.cpm.p6.model.*;
import uo.cpm.p6.model.Casilla.TipoCasilla;

public class Juego {

	private int puntos;
	private int disparos;
	private Tablero tablero;
	private Casilla casillaDisparada;
	private boolean finJuego;
	private Nivel nivel;
	
	public Juego() {
		inicializarJuego(Nivel.INTERMEDIO);
	}

	public void inicializarJuego(Nivel nivel) {
		puntos = 1000;
		disparos = 0;
		casillaDisparada = null;
		finJuego = false;
		this.nivel = nivel;
		tablero = new Tablero(nivel.getNumCasillas(), nivel.getNumInvasores(), nivel.getNumMeteoritos());

	}

	public Tablero getTablero() {
		return tablero;
	}

	public Casilla getCasillaDisparada() {
		return casillaDisparada;
	}

	public void setCasillaDisparada(Casilla casillaDisparada) {
		this.casillaDisparada = casillaDisparada;
	}

	public boolean isFinJuego() {
		return finJuego;
	}

	public void dispara(int i) {
		disparos--;
		setCasillaDisparada(tablero.getCasillas()[i]); // guardamos la casilla sobre la que se ha disparado
		if (getCasillaDisparada().getTipoCasilla().equals(TipoCasilla.METEORITO))
			puntos = 0;
		else
			puntos += tablero.getCasillas()[i].getPuntos();
		finJuego = isPartidaFinalizada();
	}

	public boolean isPartidaFinalizada() {
		return (getCasillaDisparada().getTipoCasilla().equals(TipoCasilla.INVASOR)
				|| getCasillaDisparada().getTipoCasilla().equals(TipoCasilla.METEORITO) || disparos == 0);
	}

	public String getMotivoFin() {
		TipoCasilla tipo = getCasillaDisparada().getTipoCasilla();
		if (tipo.equals(TipoCasilla.INVASOR))
			return "Has localizado al invasor";
		if (tipo.equals(TipoCasilla.METEORITO))
			return "Has chocado con un meteorito";
		return "Se han agotado los disparos";
	}

	public int getPuntos() {
		return puntos;
	}

	public void lanzar() {
		setDisparos(Dado.lanzar(nivel.getNumDisparos()));
	}

	public int getDisparos() {
		return disparos;
	}

	private void setDisparos(int disparos) {
		this.disparos = disparos;
	}

	public String obtenerImagen(int i) {
		return getTablero().obtenerImagen(i);
	}

	public Nivel getNivel() {
		return nivel;
	}

	public void setNivel(Nivel nivel) {
		this.nivel = nivel;
	}
}
