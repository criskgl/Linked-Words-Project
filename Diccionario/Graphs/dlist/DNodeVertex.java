package dlist;

public class DNodeVertex {

	public Integer vertex;
	public String palabra;
	
	public DNodeVertex prev;
	public DNodeVertex next;
	
	public DNodeVertex(Integer v, String palabra) {
		vertex = v;
		this.palabra = palabra;
	}

}

