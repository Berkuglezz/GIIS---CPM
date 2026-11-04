package uo.cpm.p6.model;

public class Dado {
	
	public static int lanzar(int maxDisparos)
	{ 
		return ((int) (Math.random() * maxDisparos) + 1);
	}
}
