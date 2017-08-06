package cpBST;

public interface IDTNode {

public int getSize(); //number of nodes in its subtree

public int getHeight(); //length of the longest way to some of its subtree's leaves plus one

public int getDepth(); 	//length of the way from to the root

public IDTNode getRoot();

public void getPreorder();

public void getInorder();

public void getPostorder();
}
