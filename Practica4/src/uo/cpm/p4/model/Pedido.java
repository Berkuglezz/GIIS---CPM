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
		Articulo articuloEnPedido = null;

		for (Articulo a : listaPedido) {
			if (a.getCodigo().equals(articuloDelCatalogo.getCodigo())) {
				articuloEnPedido = a;
				articuloEnPedido.setUnidades(articuloEnPedido.getUnidades() + unidades);
				break;
			}
		}

		if (articuloEnPedido == null) {
			articuloEnPedido = new Articulo(articuloDelCatalogo);
			articuloEnPedido.setUnidades(unidades);
			listaPedido.add(articuloEnPedido);
		}
		calcularImporte();
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
	    if (articulo != null) {
	        for (Articulo a : listaPedido) {
	            if (a.getCodigo().equals(articulo.getCodigo())) {
	                return a.getUnidades();
	            }
	        }
	    }
	    return 0;
	}
	
	private void calcularImporte() {
	    importe = 0;

	    for (Articulo a : listaPedido) {
	        importe += a.getPrecio() * a.getUnidades();
	    }

	    if (!local) {
	        importe += 0.15;
	    }
	}
}
