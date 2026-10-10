package uo.cpm.p3.console;

import java.util.Scanner;

import uo.cpm.p3.model.Articulo;
import uo.cpm.p3.service.McDonalds;

public class Main {
	private static McDonalds mc;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		mc = new McDonalds();

		System.out.println("=====Haga su pedido=====");
		mc.inicializarPedido();
		System.out.println();

		Articulo[] articulos = mc.getArticulosCarta();
		for (int i = 0; i < articulos.length; i++) {
			System.out.println(i + " " + articulos[i].getDenominacion() + " " + articulos[i].getPrecio() + " $");
		}
		System.out.println();
		System.out.println("Escoja los articulos de su pedido y la cantidad");
		System.out.println("(introduzca -1 para terminar)");

		int indice = scanner.nextInt();
		while (indice != -1) {
			if (indice < 0 || indice >= articulos.length) {
				System.out.println("Articulo no valido");
			} else {
				System.out.print("Cantidad: ");
				int cantidad = scanner.nextInt();
				if (cantidad > 0) {
					mc.aniadirAPedido(articulos[indice], cantidad);
				} else {
					System.out.println("Cantidad no valida");
				}
			}
			System.out.print("Siguiente articulo (-1 para terminar): ");
			indice = scanner.nextInt();
		}

		scanner.close();
		
		System.out.println("tus articulos son: ");
		
	}
}