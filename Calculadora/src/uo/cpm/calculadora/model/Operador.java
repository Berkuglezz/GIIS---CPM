package uo.cpm.calculadora.model;

/**
 * Operaciones que admite la calculadora.
 */
public enum Operador {
	SUMA("+"), RESTA("-"), MULTIPLICACION("×");

	private String simbolo;

	Operador(String simbolo) {
		this.simbolo = simbolo;
	}

	public String getSimbolo() {
		return simbolo;
	}

	public long aplicar(long operando1, long operando2) {
		switch (this) {
		case SUMA:
			return operando1 + operando2;
		case RESTA:
			return operando1 - operando2;
		default:
			return operando1 * operando2;
		}
	}
}
