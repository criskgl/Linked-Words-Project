package cpBST;

import cpDList.DList;
import cpDList.DNode;

public class DictionaryTreeFrec implements IDCTFrec{
	public DictionaryTreeFrecNode root;
	
	@Override
	public void save(DList lista) {
		// TODO Auto-generated method stub
		while(lista.isEmpty() == false){
			int frecActual = lista.header.next.frec;//Guardar frecuencia nodo cabeza
			String palabraActual = lista.header.next.elem;//Guardar palabra nodo cabeza
			lista.removeFirst();//borrar el nodo cabeza para poder acceder a los demás //DECISIÓN IMPLEMENTACION
			this.add(palabraActual, frecActual);
		}
	}

	@Override
	public void add(String palabra, int frec) {
		// TODO Auto-generated method stub
		DList listaXNodoNoRoot = new DList();
		DictionaryTreeFrecNode newNodeNoRoot=new DictionaryTreeFrecNode(frec,listaXNodoNoRoot);
		newNodeNoRoot.list.addFirst(palabra);
		
		DictionaryTreeFrecNode nodeIt=root;
		if (nodeIt==null){
			//inserto raiz
			//crear nodo con sus valores
			DList listaXNodo = new DList();
			DictionaryTreeFrecNode newNode=new DictionaryTreeFrecNode(frec,listaXNodo);
			newNode.list.addFirst(palabra);
			nodeIt = newNode;
			this.root = nodeIt;
		}
		else
			while (nodeIt != null) {			
				if (frec == nodeIt.frec) {//ya se ha almacenado la frecuencia que se va a insertar
					
					/*añadir la palabra a la lista asociada al nodo de frecuencia frec*/
					nodeIt.list.addFirst(palabra);
					return;
				}
				else 
					if (frec < nodeIt.frec) {
						if (nodeIt.left==null) {/*si ya no hay más hijos a la izda del nodo iterador...*/
							// le inserto
							nodeIt.left=newNodeNoRoot;
							newNodeNoRoot.parent=nodeIt;	
							return;
						}
						else
							nodeIt=nodeIt.left;
					}
					else {
						if (nodeIt.right==null) {
							// le inserto
							nodeIt.right=newNodeNoRoot;
							newNodeNoRoot.parent=nodeIt;
							return;
						}
						else
							nodeIt=nodeIt.right;
					}
			}
	}

}
