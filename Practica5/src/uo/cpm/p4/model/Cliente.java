package uo.cpm.p4.model;

public class Cliente {
	public static final int EDAD_MIN = 18;
	public static final int EDAD_MAX = 100;

	private String nombreYApellidos;
	private int anioNacimiento;
	private String password;

	public Cliente(String nombreYApellidos, int anioNacimiento, String password) {
		this.nombreYApellidos = nombreYApellidos;
		this.anioNacimiento = anioNacimiento;
		this.password = password;
	}

	public String getNombreYApellidos() {
		return nombreYApellidos;
	}

	public void setNombreYApellidos(String nombreYApellidos) {
		this.nombreYApellidos = nombreYApellidos;
	}

	public int getAnioNacimiento() {
		return anioNacimiento;
	}

	public void setAnioNacimiento(int anioNacimiento) {
		this.anioNacimiento = anioNacimiento;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return this.nombreYApellidos;			
	}
}
