package uo.cpm.calculadora.model;

/**
 * Lógica de una calculadora muy simple: hace una única operación por ejecución.
 * Orden: primer operando (entero positivo), operador, segundo operando y "=".
 * Una vez mostrado el resultado ya no admite nada más.
 *
 * No usa nada de Swing: la ventana pregunta aquí qué se puede pulsar en cada momento.
 */
public class Calculadora {

	/** Fase de la operación en la que está la calculadora. */
	public enum Estado {
		PRIMER_OPERANDO, SEGUNDO_OPERANDO, RESULTADO
	}

	/** Cifras máximas de cada operando (así el resultado siempre cabe en un long). */
	public static final int MAX_CIFRAS = 9;

	private Estado estado;
	private String operando1;
	private String operando2;
	private Operador operador;
	private long resultado;

	public Calculadora() {
		estado = Estado.PRIMER_OPERANDO;
		operando1 = "";
		operando2 = "";
	}

	/**
	 * Añade una cifra al operando que se está escribiendo.
	 * @return false si en este momento no se admiten cifras
	 */
	public boolean pulsarDigito(int digito) {
		if (digito < 0 || digito > 9 || !isDigitoPermitido())
			return false;
		if (estado == Estado.PRIMER_OPERANDO)
			operando1 = anadirCifra(operando1, digito);
		else
			operando2 = anadirCifra(operando2, digito);
		return true;
	}

	private String anadirCifra(String operando, int digito) {
		if (operando.equals("0")) // sin ceros a la izquierda
			return String.valueOf(digito);
		return operando + digito;
	}

	/**
	 * Elige la operación. Solo se puede una vez y tras escribir el primer operando.
	 * @return false si en este momento no se admite un operador
	 */
	public boolean elegirOperador(Operador operador) {
		if (operador == null || !isOperadorPermitido())
			return false;
		this.operador = operador;
		estado = Estado.SEGUNDO_OPERANDO;
		return true;
	}

	/**
	 * Calcula el resultado (botón "="). A partir de aquí la calculadora queda bloqueada.
	 * @return false si todavía falta algún elemento de la operación
	 */
	public boolean calcular() {
		if (!isIgualPermitido())
			return false;
		resultado = operador.aplicar(Long.parseLong(operando1), Long.parseLong(operando2));
		estado = Estado.RESULTADO;
		return true;
	}

	// ---- Qué se puede pulsar en cada momento ----

	public boolean isDigitoPermitido() {
		switch (estado) {
		case PRIMER_OPERANDO:
			return cabeOtraCifra(operando1);
		case SEGUNDO_OPERANDO:
			return cabeOtraCifra(operando2);
		default:
			return false;
		}
	}

	private boolean cabeOtraCifra(String operando) {
		return operando.length() < MAX_CIFRAS || operando.equals("0");
	}

	public boolean isOperadorPermitido() {
		return estado == Estado.PRIMER_OPERANDO && !operando1.isEmpty();
	}

	public boolean isIgualPermitido() {
		return estado == Estado.SEGUNDO_OPERANDO && !operando2.isEmpty();
	}

	public boolean isTerminada() {
		return estado == Estado.RESULTADO;
	}

	// ---- Lo que se muestra ----

	/** Número grande de la pantalla: el operando que se escribe o el resultado. */
	public String getPantalla() {
		switch (estado) {
		case PRIMER_OPERANDO:
			return operando1.isEmpty() ? "0" : operando1;
		case SEGUNDO_OPERANDO:
			return operando2.isEmpty() ? operando1 : operando2;
		default:
			return String.valueOf(resultado);
		}
	}

	/** Línea pequeña sobre la pantalla con la operación ("12 +", "12 + 3 ="). */
	public String getOperacion() {
		switch (estado) {
		case PRIMER_OPERANDO:
			return "";
		case SEGUNDO_OPERANDO:
			return operando1 + " " + operador.getSimbolo();
		default:
			return operando1 + " " + operador.getSimbolo() + " " + operando2 + " =";
		}
	}

	public Estado getEstado() {
		return estado;
	}

	public Operador getOperador() {
		return operador;
	}

	public long getResultado() {
		return resultado;
	}
}
