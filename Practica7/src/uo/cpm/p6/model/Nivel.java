package uo.cpm.p6.model;

public enum Nivel {
	FACIL(10, 2, 0, 5), INTERMEDIO(8, 1, 1, 4), DIFICIL(6, 1, 2, 3);

	private int numCasillas, numInvasores, numMeteoritos, numDisparos;

	Nivel (int numCasillas, int numInvasores, int numMeteoritos, int numDisparos) {
		this.numCasillas = numCasillas;
		this.numInvasores = numInvasores;
		this.numMeteoritos = numMeteoritos;
		this.numDisparos = numDisparos;
	}

	public int getNumCasillas() {
		return numCasillas;
	}

	public int getNumInvasores() {
		return numInvasores;
	}

	public int getNumMeteoritos() {
		return numMeteoritos;
	}

	public int getNumDisparos() {
		return numDisparos;
	}
}

