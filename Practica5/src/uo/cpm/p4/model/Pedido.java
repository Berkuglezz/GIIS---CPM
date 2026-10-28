package uo.cpm.p4.model;

import java.util.*;

import uo.cpm.p4.util.FileUtil;

public class Pedido {

	private List<Articulo> listaPedido;
	private String codigo;
	private float importe;
	private boolean local;
	private Cliente cliente;
	

	public Pedido() {
		listaPedido = new ArrayList<Articulo>();
		inicializar();
	}

	public void inicializar() {
		listaPedido.clear();
		generarCodigo();
		setImporte(0);
		setLocal(true);
	    setCliente(null);
	}

	public void add(Articulo articuloDelCatalogo, int unidades) {
		Articulo articuloEnPedido = buscar(articuloDelCatalogo);

		if (articuloEnPedido != null) {
			articuloEnPedido.setUnidades(articuloEnPedido.getUnidades() + unidades);
		} else {
			articuloEnPedido = new Articulo(articuloDelCatalogo);
			articuloEnPedido.setUnidades(unidades);
			listaPedido.add(articuloEnPedido);
		}
		calcularImporte();
	}

	// Quita unidades de un artículo; si se quitan todas, el artículo sale del pedido
	public void eliminar(Articulo articuloDelCatalogo, int unidades) {
		Articulo articuloEnPedido = buscar(articuloDelCatalogo);
		if (articuloEnPedido == null) {
			return;
		}

		int restantes = articuloEnPedido.getUnidades() - unidades;
		if (restantes <= 0) {
			listaPedido.remove(articuloEnPedido);
		} else {
			articuloEnPedido.setUnidades(restantes);
		}
		calcularImporte();
	}

	// Se pueden eliminar entre 1 y las unidades que hay de ese artículo en el pedido
	public boolean sePuedeEliminar(Articulo articulo, int unidades) {
		return unidades > 0 && unidades <= getUnidades(articulo);
	}

	public boolean contiene(Articulo articulo) {
		return buscar(articulo) != null;
	}

	public boolean isVacio() {
		return listaPedido.isEmpty();
	}

	public boolean hayPedido() {
		return !isVacio();
	}

	private Articulo buscar(Articulo articulo) {
		if (articulo != null) {
			for (Articulo a : listaPedido) {
				if (a.getCodigo().equals(articulo.getCodigo())) {
					return a;
				}
			}
		}
		return null;
	}

	public void grabarPedido() {
		FileUtil.saveToFile(this);
	}

	public List<Articulo> getListaPedido() {
		return listaPedido;
	}

	public void setListaPedido(List<Articulo> listaPedido) {
		this.listaPedido = listaPedido;
	}

	public String getCodigo() {
		return codigo;
	}

	public float getImporte() {
		return importe;
	}

	public void setImporte(float importe) {
		this.importe = importe;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void setLocal (boolean tipo) {
		local = tipo;
		calcularImporte();	// la bolsa depende de si es para llevar
	}
	
	public boolean isLocal() {
		return local;
	}
	
	public void generarCodigo() {
		codigo = "";
		String base = "0123456789abcdefghijklmnopqrstuvwxyz";
		int longitudCodigo = 8;
		for (int i = 0; i < longitudCodigo; i++)
			codigo += base.charAt((int) (Math.random() * (base.length())));
	}

	public int getUnidades(Articulo articulo) {
	    Articulo articuloEnPedido = buscar(articulo);
	    return articuloEnPedido != null ? articuloEnPedido.getUnidades() : 0;
	}

	private void calcularImporte() {
	    importe = 0;

	    for (Articulo a : listaPedido) {
	        importe += a.getPrecio() * a.getUnidades();
	    }

	    if (!local && !listaPedido.isEmpty()) {
	        importe += 0.15;
	    }
	}

	//TOstring de la profe
	@Override
	public String toString() {
		String strPedido = "";
		
		if (cliente == null) {
			// Para mostrar el pedido cuando todavia no tiene asociado un cliente
			if (hayPedido())
				strPedido = generarDetallePedido(strPedido);
		} else {
			String strCabecera = ("Cliente: " + this.getCliente().getNombreYApellidos()) + ("\n");
			strCabecera += "----------------------------------" + ("\n");
			strPedido = generarDetallePedido(strCabecera);
			strPedido += ("Pedido para ") + ((isLocal() ? "consumir en local" : "llevar"));
		}
		return strPedido;
	}
	
	private String generarDetallePedido(String strPedido) {
		for (Articulo a : listaPedido) {
			strPedido += a.getDenominacion() + " - " + a.getUnidades() + (" uds.") + ("\n");
		}
		strPedido += ("Total: " + (String.format("%.2f", getImporte()))) + ("\n");
		return strPedido;
	}
}
