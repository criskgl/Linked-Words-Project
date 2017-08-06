package cpBST;
import cpDList.DList;

public class DictionaryTreeFrecNode {
	public Integer frec;
	public DList list;

	DictionaryTreeFrecNode parent;
	DictionaryTreeFrecNode left;
	DictionaryTreeFrecNode right;

	
	public DictionaryTreeFrecNode(Integer k, DList list) {
		frec = k;
		this.list = list;
	}

}
