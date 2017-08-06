package cpDList;

public interface IList {
	public void addFirst(String elem);//O(1)
	public void add(cpQueue.SQueue cola);//O(n)
	public void add(String elem);//O(n)
	public void addLast(String elem);//O(1)
	public void removeFirst();//O(1)
	public void removeLast();//O(1)
	public void insertAt(int index, String elem);//O(n)
	public boolean isEmpty();//O(1)
	public boolean contains(String elem);//O(n)
	public int getIndexOf(String elem);//O(n)
	public void removeAll(String elem);//O(n)
	public void removeAt(int index);//O(n)
	public int getSize();//O(1)
	public String getFirst();//O(1)
	public String getLast();//O(1)
	public String getAt(int index);//O(n)
	public void swapNodes(DNode first, DNode second);//O(1)
	public String toString();//O(n)
	public String toStringInverted();//O(n)
	public void sort(int option);//O(n^2)
	public void show(char c);//O(n)
	public int search(String palabra);//O(n)
	public DList getTop(int n);//O(n^3)
	public DList getLow(int n);//O(n^3)
}


