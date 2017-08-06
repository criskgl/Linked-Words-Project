package cpDList;
public class DNode {
	
	public int frec;
	public String elem;
	//para palabras
	public DNode prev;
	public DNode next;
	
	public DNode(String elem) {
		this.elem = elem;
		this.frec = 1;
	}

}
