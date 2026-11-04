package uo.cpm.p6.model;

public class Casilla {
	
	public enum TipoCasilla {INVASOR,ESPACIO,METEORITO};
	private String nombre;
	private TipoCasilla tipoCasilla;
	private int puntos;
	private String imagen;
	
	public Casilla(String nombre, TipoCasilla tipoCasilla, int puntos, String imagen) {
		this.nombre = nombre;
		this.tipoCasilla = tipoCasilla;
		this.puntos = puntos;
		this.imagen = imagen;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public int getPuntos() {
		return puntos;
	}
	
	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
	
	public TipoCasilla getTipoCasilla() {
		return tipoCasilla;
	}

	public void setTipoCasilla(TipoCasilla tipoCasilla) {
		this.tipoCasilla = tipoCasilla;
	}
}
