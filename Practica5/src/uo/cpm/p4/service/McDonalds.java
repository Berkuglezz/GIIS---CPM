package uo.cpm.p4.service;

import java.time.LocalDate;

import uo.cpm.p4.model.*;

public class McDonalds {
	Carta carta = new Carta();
	Pedido pedido = new Pedido(); 
		
	public McDonalds() {
		inicializarPedido();
	}

	public Articulo[] getArticulosCarta() {
		return carta.getArticulos();
	}

	public Articulo[] getArticulosCarta(String tipo) {
		return carta.getArticulosFiltrados(tipo);
	}

	public void inicializarPedido() {
		pedido.inicializar();
	}
	
	public String getCodigoPedido() {
		return pedido.getCodigo();
	}

	public void aniadirAPedido(Articulo articulo, int unidades) {
		pedido.add(articulo, unidades);
	}

	public float getImportePedido() {
		return pedido.getImporte();
	}
	
	public void guardarCliente(String nombre, int anio, String password) {
		pedido.setCliente(new Cliente(nombre, anio, password));
	}
	
	public void actualizarTipoPedido(boolean llevar) {
	    pedido.setLocal(!llevar);
	}
	
	public void grabarPedido() {
		pedido.grabarPedido();
	}
	
	public String[] getAnos() {
	    int total = Cliente.EDAD_MAX - Cliente.EDAD_MIN + 1;
	    String[] anios = new String[total];
	    int anioBase = LocalDate.now().getYear() - Cliente.EDAD_MAX;

	    for (int i = 0; i < anios.length; i++) {
	        anios[i] = String.valueOf(anioBase + i);
	    }
	    
	    return anios;
	}
	
	public int getUnidadesArticulo(Articulo articulo) {
	    return pedido.getUnidades(articulo);
	}

	public void eliminarDePedido(Articulo articulo, int unidades) {
		pedido.eliminar(articulo, unidades);
	}

	public boolean sePuedeEliminar(Articulo articulo, int unidades) {
		return pedido.sePuedeEliminar(articulo, unidades);
	}

	public boolean isArticuloEnPedido(Articulo articulo) {
		return pedido.contiene(articulo);
	}

	public boolean isPedidoVacio() {
		return pedido.isVacio();
	}

	public String getTextoPedido() {
		return pedido.toString();
	}

}
