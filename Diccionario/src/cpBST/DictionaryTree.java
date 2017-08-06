package cpBST;
import cpDList.DList;
import cpDList.DNode;

public class DictionaryTree implements IDTree {
	
	public DictionaryTreeNode root;

	
	public DictionaryTreeNode getRoot(){
		return root;
	}
	
	@Override
	public int getSize() {
		//utilizamos el metodo del nodo usando el root
		if (root!=null)
			return root.getSize();
		else return 0;
	}

	@Override
	public int getHeight() {
		//utilizamos el metodo del nodo usando el root
		if (root!=null)
			return root.getHeight();
		else return 0;
	}

	@Override
	public void getPreorder() {
		//utilizamos el metodo del nodo usando el root
		if (root!=null) 
			root.getPreorder();
	}
	
	@Override
	public void getInorder() {
		//utilizamos el metodo del nodo usando el root
		if (root!=null)
			 root.getInorder();
	}
	public void getInorderReverse() {
		//utilizamos el metodo del nodo usando el root
		if (root!=null)
			 root.getInorderReverse();
	}
	
	@Override
	public void getPostorder() {
		//utilizamos el metodo del nodo usando el root
		if (root!=null)
			 root.getPostorder();
	}
	
	@Override
	public int find(String elem) {
		//llamamos al metodo auxiliar con nodo para poder realizar la recursion
		return find(root, elem);		
	}
	
	private int find(DictionaryTreeNode node, String elem) {
		if (node == null) {
			return -1;
		} else {
			if (elem.equals(node.elem)) //encontrado
				return node.frec;
			if (elem.compareTo(node.elem) < 0) //al izquierdo
				return find(node.left, elem);
			else                        //al derecho
				return find(node.right, elem);
		}
	}

	public String findIterativo(Integer frec) {
		DictionaryTreeNode nodeIt=root;
		while (nodeIt != null) {			
			if (frec.equals(nodeIt.frec)) //encontrado
				return nodeIt.elem;
			else 
				if (frec.compareTo(nodeIt.frec) < 0) //al izquierdo
					nodeIt=nodeIt.left;
				else              //al derecho
					nodeIt=nodeIt.right;
		}
		//si llego aqu� no lo he encontrado
		System.out.println(frec + " no ha sido encontrado");
		return null;
	}
	
	@Override
	public void insert(int frec, String elem) {
		DictionaryTreeNode newNode=new DictionaryTreeNode(frec,elem);
		if (root==null)  //no necesitamos el constructor con nodo
			root=newNode;  
		else 
			insert(newNode, root); //llamada al metodo recursivo auxiliar con nodo
	}
	
	private void insert(DictionaryTreeNode newNode, DictionaryTreeNode node) {
		
		if (newNode.elem.compareTo(node.elem)==0) { //coincide, no podemos insertar
			System.out.println(newNode.frec + " already exists. Duplicates are not allowed!!!.");
			return;
		}
		if (newNode.elem.compareTo(node.elem)<0) { //a la izquierda
			if (node.left==null) {  //si no tiene hijo izquierdo, lugar encontrado
				node.left=newNode;
				newNode.parent=node;
			} else 
				insert(newNode,node.left); //llamada recursiva con hijo izquierdo
		} else {                   // a la derecha
			if (node.right==null) {  //si no tiene hijo derecho, lugar encontrado
				node.right=newNode;
				newNode.parent=node;
			} else 
				insert(newNode,node.right);  //llamada recursiva con hijo derecho
		}
	}
	
	 public void insertIterativo(Integer frec, String element) {
		 
	    	DictionaryTreeNode newNode=new DictionaryTreeNode(frec,element);
			DictionaryTreeNode nodeIt=root;
			if (nodeIt==null) //inserto raiz
				root=newNode;
			else
				while (nodeIt != null) {			
					if (frec.compareTo(nodeIt.frec)==0) {//ya existe
						System.out.println("La clave " + frec + " ya existe.");
						return;
					}
					else 
						if (frec.compareTo(nodeIt.frec)<0) {
							if (nodeIt.left==null) {
								// le inserto
								nodeIt.left=newNode;
								newNode.parent=nodeIt;	
								return;
							}
							else
								nodeIt=nodeIt.left;
						}
						else {
							if (nodeIt.right==null) {
								// le inserto
								nodeIt.right=newNode;
								newNode.parent=nodeIt;
								return;
							}
							else
								nodeIt=nodeIt.right;
						}
				}				
		}
	
	
	public void remove(Integer frec) {
		if (root == null) {
			System.out.println("Cannot remove: The tree is empty");
			return;
		}
		//si el elemento que tenemos que borrar es la raiz, es un caso especial
		if (frec.equals(root.frec)) {
			if (root.left==null && root.right==null) //root es hoja, no tiene hijos 
				root=null;    //el arbol se queda vacio
			else 
				if (root.left==null ||root.right==null)  { //si tengo un hijo vacio
					if (root.left==null) 
						root=root.right; //si el hijo izquierdo esta vacio, el derecho pasa a ser root
					if (root.right==null) 
						root=root.left; //si el hijo deerecho esta vacio, el izquierdo pasa a ser root
					root.parent=null;  //actualizo el parent porque antes apuntaba al viejo root
				} else 
					remove(frec,root); //metodo auxiliar recursivo					
		}
		else 
			remove(frec,root); //metodo auxiliar recursivo		
	}

	
	private boolean remove(Integer frec, DictionaryTreeNode node) {
		if (node == null) {
			System.out.println("Cannot remove: The frec doesn't exist");
			return false;
		}
		
		if (frec.compareTo(node.frec)<0)  //sigo buscando por la izquierda 
			return remove(frec,node.left);
		
		if (frec.compareTo(node.frec)>0) //sigo buscando por la derecha
			return remove(frec,node.right);
		
		//Ya lo he encontrado, estoy en el nodo qe tengo que borrar		
		//caso1; Es hoja =>  no tiene hijos, poner a null el hijo del parent 
		// pero �qu� hijo es dcho o izqdo?
		if (node.left==null && node.right==null) { 			
			if (node.parent.left==node) //si es hijo izquierdo 
				node.parent.left=null;  //lo pongo a null
			else              //si es hijo derecho
				node.parent.right=null; //lo pongo a null			
			return true;
		}

		//caso2: solo un hijo => colocamos el hijo que existe en su lugar 
		if (node.left==null ||node.right==null){
			DictionaryTreeNode grandChild=null;  //el hijo que existe
			if (node.left==null)  //ser� el derecho
				grandChild=node.right;
			else         //ser� el izquierdo
				grandChild=node.left;
			
			//ahora tengo q saber si el node era hijo izquierdo o dercho de su padre para saber que lugar ocupa el nuevo
			if (node.parent.left==node) //
				node.parent.left=grandChild;
			else 
				node.parent.right=grandChild;
			
			grandChild.parent=node.parent;
			return true;
		}
		
		//caso3: tiene dos hijos => reemplazamos su valor por el m�ximo valor de su hijo izquierdo
		// o por el m�nimo valor de su hijo derecho
		//necesitaremos un metodo auxiliar que devuelva el nodo por el que vamos a realizar el cambiazo
		DictionaryTreeNode sucesorNode = findMin(node.right);
		//llamamos al metodo del minimo sobre el subarbol derecho
		//cambiamos los valores		
		node.elem=sucesorNode.elem;
		node.frec=sucesorNode.frec;
		//borramos el minimo
		remove(sucesorNode.frec,node.right);
		
		return true;
	}


	private DictionaryTreeNode findMin(DictionaryTreeNode node) {
		//devuelve el nodo que est� m�s a la izquierda, es decir el menor
		if (node==null) return null;
		DictionaryTreeNode minNode=node;
		while (minNode.left!=null) {
			minNode=minNode.left;
		}
		return minNode;
	}
	//FASE 2**************************************************************************************************

	@Override
	public void add(cpQueue.SQueue cola){
		
		while(cola.front() != null){//mientras no se haya vaciado la cola
			
			String palabra_cola = cola.dequeue();
			this.add(palabra_cola);
		}
	}


	public void add(String palabra) {
		DictionaryTreeNode nodeIt=root;
		boolean encontrado = false; 

		while (nodeIt != null && !encontrado) {			
			if (palabra.compareTo(nodeIt.elem) == 0){//si lo encontramos aumentamos su frecuencia 
				nodeIt.frec++;
				encontrado = true;
			}
			else{
				if (palabra.compareTo(nodeIt.elem) < 0) //al izquierdo
					nodeIt=nodeIt.left;
				else//al derecho
					nodeIt=nodeIt.right;
				
			}
		}
		if(encontrado == false){
			insert(1, palabra);//si no lo encontramos añadimos palabra con frecuencia 1
		}
	}
	
	public String toString(DictionaryTreeNode root)
	{
	    StringBuilder builder = new StringBuilder();
	    if (root == null)
	        return "";
	    builder.append(toString(root.left)+" \n izdo ");
	    builder.append(toString(root.right)+" \n dcho ");
	    return builder.append(root.elem.toString()).toString();
	}
	
	public String toString2() {
		return toString2("", root);
	}

   private static String toString2(String prefix, DictionaryTreeNode node) {
      if (node == null) return "";
      String string = prefix + node.elem.toString()+"("+node.frec+")";
      if (node.right != null)
         string = toString2("    " + prefix, node.right) + "\n" + string;
      if (node.left != null)
         string = string + "\n" + toString2("    " + prefix, node.left);
      return string;
   }
   
	public int search(DictionaryTreeNode node, String elem) {
		if (node == null) {
			return -1;
		} else {
			if (elem.equals(node.elem)) //encontrado
				return node.frec;
			if (elem.compareTo(node.elem) < 0) //al izquierdo
				return find(node.left, elem);
			else                        //al derecho
				return find(node.right, elem);
		}
	}
	
	public int search(String elem) {
		//llamamos al metodo auxiliar con nodo para poder realizar la recursion
		return find(root, elem);		
	}
	
	
	public DList getTop(int n){
		DList top = new DList();
		getTop(root, top, n);
		return top;
	}
   
	private void getTop(DictionaryTreeNode node, DList top, int n){
		if(node == null) return;
		
		getTop(node.left, top, n);
		if(node.parent != null){
			if(top.contains(node.elem) == false && top.tailer.prev.frec <= node.frec){
				top.addFirst(node.elem);
				top.header.next.frec = node.frec;
				DNode aux = top.header.next;
			
				while(aux.next != null && aux.frec <= aux.next.frec ){
					top.swapNodes(aux,aux.next);
				}
				if(top.size>n){
					top.removeLast();
				}
			}
		}
		getTop(node.right, top, n);
	}
	
	public DList getLow(int n){
		DList low = new DList();
		getLow(root, low, n);
		return low;
	}
   
	private void getLow(DictionaryTreeNode node, DList low, int n){
		if(node == null) return;
		
		getLow(node.right, low, n);
		if(node.parent != null){
			if(low.contains(node.elem) == false && low.tailer.prev.frec >= node.frec){
				low.addFirst(node.elem);
				low.header.next.frec = node.frec;
				DNode aux = low.header.next;
				while(aux.next != null && aux.frec > aux.next.frec ){
					low.swapNodes(aux,aux.next);
				}
				if(low.size>n){
					low.removeLast();
				}
			}
		}
		getLow(node.left, low, n);
	}
	
}