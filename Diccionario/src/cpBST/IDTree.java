package cpBST;


public interface IDTree{
	
	public DictionaryTreeNode getRoot();
	
	public int getSize();

	public int getHeight();

	public void getPreorder();
	
	public void getInorder();
	
	public void getPostorder();	
	
	public int find(String elem);

	public void insert(int frec, String elem);

	public void remove(Integer key);
	
	//FASE 2
	public void add(cpQueue.SQueue cola);
	
	public void add(String palabra);
}
