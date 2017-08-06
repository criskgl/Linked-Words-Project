package cpDList;

public class DList implements IList {

	public DNode header; //by default is null
	public DNode tailer;//by default is null
	public int size; //by default is 0

	public DList() {
		//we have to create the sentinel nodes
		header = new DNode(null);
		tailer = new DNode(null);
		//they have to point with each other
		header.next = tailer;
		tailer.prev= header;
	}
	public void addFirst(String elem) {
		DNode newNode = new DNode(elem);
		newNode.next = header.next;
		newNode.prev= header;
		header.next.prev= newNode;
		header.next = newNode;
		size++;
	}
	
	public void add(cpQueue.SQueue cola){
		
		while(cola.front() != null){//mientras no se haya vaciado la cola
			
			String palabra_cola = cola.dequeue();
			this.add(palabra_cola);
		}
	}
	
	public void add(String elem) {
		DNode actual = header.next;
		DNode newNode = new DNode(elem);
		boolean found = false;
		
		if(isEmpty()){
			addFirst(elem);
		}else{
			while(actual.next!= null && String.CASE_INSENSITIVE_ORDER.compare(actual.elem, elem) <= 0){
				if(actual.elem.compareTo(elem) == 0){
					actual.frec++;
					found = true;
					break;
				}
				actual = actual.next;
			}
			if(!found && actual != tailer){
				actual.prev.next = newNode;
				newNode.prev = actual.prev;
				actual.prev = newNode;
				newNode.next = actual;
				size++;
			}else if(!found && actual == tailer){
				addLast(elem);
			}
		}
	}

	
	public void addLast(String elem) {
		DNode newNode = new DNode(elem);
		newNode.next = tailer;
		newNode.prev= tailer.prev;
		tailer.prev.next = newNode;
		tailer.prev= newNode;
		size++;

	}

	public void removeFirst() {
		if (isEmpty()) {
			System.out.println("DList: List is empty");
			return;
		}
		header.next = header.next.next;
		header.next.prev= header;
		size--;
	}
	
	public void removeLast() {
		if (isEmpty()) {
			System.out.println("DList: List is empty");
			return;
		}
		tailer.prev= tailer.prev.prev;
		tailer.prev.next = tailer;
		size--;
	}
	
	
	// laboratorio
	public void insertAt(int index, String elem) {
		
		//Actual empieza siendo el "First"
		DNode actual = header.next;
		int contador = 0;
		DNode newNode = new DNode(elem);
		
		if(index == 0) addFirst(elem);
		if(index == this.size) addLast(elem);
		
		else
		{
			while(contador < index - 1)
			{
				actual = actual.next;
				contador++;
			}
			newNode.next = actual.next;
			newNode.prev = actual;
			actual.next.prev = newNode;
			actual.next = newNode;
			
			size++;
		}
		
	}

	
	public boolean isEmpty() {
		if(size == 0){
			return true;
		}else{
			return false;
		}
	}

	
	public boolean contains(String elem) {
		
		boolean found=false;
		for (DNode nodeIt = header.next; nodeIt != tailer && found==false; nodeIt = nodeIt.next) {
			if (nodeIt.elem.compareTo(elem) == 0) {
				found=true;
			}
		}
		return found;
	}

	
	public int getIndexOf(String elem) {
		
		DNode actual = header.next;
		
		for(int i = 0; i < size; i++)
		{
			if(actual.elem == elem){
				return i;
			}
			actual = actual.next;
		}
		
		return -1;
	}

	
	public void removeAll(String elem) {
		
		if(tailer.prev.elem == elem)
		{
			removeLast();
		}
		else if(header.next.elem == elem)
		{
			removeFirst();
		}
		
		DNode actual = header.next;
		
		for(int i = 1;i < (this.size - 1); i++)
		{
			if(actual.elem == elem){
				removeAt(i);
			}
		}
	}

	
	
	public void removeAt(int index) {
		
		if(index > size){
			System.out.println("Indice no valido");
		}else if(index < 0){
			System.out.println("Indice no válido");
		}else if(index == 0){
			//System.out.println("He borrado a "+getFirst()+" en la posicion: "+index);
			removeFirst();
		}else if(index == size-1){
			//System.out.println("He borrado a "+getLast()+" en la posicion: "+index);
			removeLast();
		}else{
			
			DNode actual = header.next;
			
			for(int i = 0; i < index; i++){
				actual = actual.next;
			}
			//System.out.println("He borrado a "+actual.elem+" en la posicion: "+index);
			actual.prev.next = actual.next;
			actual.next.prev = actual.prev;
			actual.next = null;
			actual.prev = null;
			this.size--;
		}	
	}

	
	public int getSize() {
				return size;
	}

	
	public String getFirst() {
		return header.next.elem;
	}

	public String getLast() {		
		return tailer.prev.elem;
	}

	
	public String getAt(int index) {
		
		DNode actual = header.next;
		
		if(index > (this.size -1)){
			System.out.println("El indice:"+index+" no es válido");
			return null;
		}
		else if(index < 0)
		{
			System.out.println("El indice no es válido");
			return null;
		}else if(index == 0){
			return getFirst();
		}
		else	
		{
			for(int i = 0; i < this.size; i++, actual = actual.next){
				
				if(i == index){
		
					//System.out.println("El elemento en encontrado en la posicion: "+i+" es: "+ actual.elem);
					return actual.elem;
					
				}
				
			}
		}
		return null;
		
		
	}
	//Metodo para cambiar dos nodos Adyacentes
	public void swapNodes(DNode nodo1, DNode nodo2){
		
		//Dos nodos que copian(CREAR NODOS NUEVOS) los datos de entrada
		DNode aux1 = new DNode(null);
		//DNode aux2 = new DNode(null);
		
		//copiamos las propiedades del  nodo1 en el auxiliar
		aux1.elem = nodo1.elem;
		aux1.next = nodo1.next;
		aux1.prev = nodo1.prev;
		
		//reasignamos al nodo1
		nodo1.next = nodo2.next;
		nodo1.prev = nodo2;
		nodo2.next.prev = nodo1;
		
		/*asignamos al nodo2 las propiedades del nodo1
	 	(desde el auxiliar porque el nodo1 ya tiene las propiedades del 2)*/
		
		nodo2.next = nodo1;
		nodo2.prev = aux1.prev;
		aux1.prev.next = nodo2;
	}
	

	public String toString() {
		String result = null;
		if(this.isEmpty()){
			return null;
		}
		for (DNode nodeIt = header.next; nodeIt != tailer; nodeIt = nodeIt.next) {
			if (result == null) {
				result = nodeIt.elem + " " + nodeIt.frec + "\n";
			} else {
				result += nodeIt.elem + " " + nodeIt.frec + "\n";
			}
		}
		return result == null ? "empty" : result;
	}
	//Metodo Fase 3
	public String toStringNoFrec() {
		String result = null;
		if(this.isEmpty()){
			return null;
		}
		for (DNode nodeIt = header.next; nodeIt != tailer; nodeIt = nodeIt.next) {
			if (result == null) {
				result = nodeIt.elem + "--";
			} else {
				result += nodeIt.elem + "--";
			}
		}
		return result == null ? "empty" : result;
	}
	//FIN metodo Fase 3
	
	public String toStringInverted() {
		String result = null;
		for (DNode nodeIt = tailer.prev; nodeIt != header; nodeIt = nodeIt.prev) {
			if (result == null) {
				result = nodeIt.elem + " " + nodeIt.frec + "\n";
			} else {
				result += nodeIt.elem + " " + nodeIt.frec + "\n";
			}
		}
		return result == null ? "empty" : result;
	}
	
	//METODOS EXCLUSIVOS DICCIONARIO
	public void sort(int option) {//BUBBLE SORT BY--> 1: Palabra Ascend 2:Frec Ascend 3:Palabra Descend 4:Frec Descend
		
		DNode actual = header.next;
		boolean change = false;

		while(true)
		{
			change = false;
			for(int i = 1; i < this.size; i++)
			{
				actual = header.next;
				for(int j = 0; j < this.size - i; j++)
				{
					if (option == 1){
						if(actual.elem.compareTo(actual.next.elem) > 0)
						{
							swapNodes(actual, actual.next);
							change = true;
						}else{
							actual = actual.next;
						}
					}else if(option == 2){
						if(actual.frec > actual.next.frec)
						{
							swapNodes(actual, actual.next);
							change = true;
						}else{
							actual = actual.next;
						}
					}else if(option == 3){
						if(actual.elem.compareTo(actual.next.elem) < 0)
						{
							swapNodes(actual, actual.next);
							change = true;
						}else{
							actual = actual.next;
						}
					}else if(option == 4){
						if(actual.frec < actual.next.frec)
						{
							swapNodes(actual, actual.next);
							change = true;
						}else{
							actual = actual.next;
						}
					}else{
						System.out.println("Opción no válida");
					}
					
					
				}
			}
			if(change == false) break;
		}
		
	}
	public void show(char c){
		
		if(c == 'A'){
			System.out.println(this.toString());
		}else{
			System.out.println(this.toStringInverted());
		}
	}
	
	public int search(String palabra){
		
		DNode actual = header.next;
		
		for(int i = 0; i < size; i++)
		{
			if(actual.elem.compareTo(palabra) == 0){
				return actual.frec;
			}
			actual = actual.next;
		}
		return -1;
	}
	
	public DList getTop(int n){
		
		DList max = new DList();
		int frecMax = 1;
		String elemMax = "";
		DNode aux = header.next;
		frecMax = aux.frec;

	for(int j = 0; j < n; j++){
		aux = header.next;
		frecMax = 1;
		for(int i = 0; i<this.size; i++){//Find first maximum frec
			if(aux.next != tailer){
				if(aux.frec > frecMax && max.contains(aux.elem) == false){
					frecMax = aux.frec;
					elemMax = aux.elem;
				}
				aux = aux.next;
			}
		}
		max.addLast(elemMax);
		max.tailer.prev.frec = frecMax;
		
	}
		return max;
	}
	
public DList getLow(int n){
		
		DList min = new DList();
		int frecMin = 1;
		String elemMin = "";
		DNode aux = header.next;
		frecMin = aux.frec;

		
	for(int j = 0; j < n; j++){
		aux = header.next;
		frecMin = Integer.MAX_VALUE;
		for(int i = 0; i<this.size; i++){//Find first minimum frec
			if(aux.next != tailer){
				if(aux.frec < frecMin && min.contains(aux.elem) == false){
					frecMin = aux.frec;
					elemMin = aux.elem;
				}
				aux = aux.next;
			}
		}
		min.addLast(elemMin);
		min.tailer.prev.frec = frecMin;
	}
		return min;
	}
	
}
