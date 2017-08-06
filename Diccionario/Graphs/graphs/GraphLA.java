package graphs;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

import cpQueue.SQueue;
import cpDList.DList;
import cpDList.DNode;
import dlist.DListVertex;
import dlist.DNodeVertex;

public class GraphLA implements IGraph {
	
	int numVertices;
	int maxVertices;
	
	DListVertex[] vertices;
	boolean directed;
	String palabras[];
	
	
	public GraphLA(int n, int max, boolean d, DList lista) {
		if (max<=0) 
			throw new IllegalArgumentException("Negative maximum number of vertices!!!");
		if (n<=0) 
			throw new IllegalArgumentException("Negative number of vertices!!!.");
		if (n>max) 
			throw new IllegalArgumentException("number of vertices can never " + "be greater than the maximum.");
		
		maxVertices=max;
		vertices=new DListVertex[maxVertices];
		numVertices=n;
		palabras = new String[maxVertices];
		//creates each list
		DNode aux = lista.header.next;
		
		for (int i=0; i<numVertices;i++) {
			vertices[i]=new DListVertex();
			palabras[i] = aux.elem; 
			aux = aux.next;
		}
		
		directed=d;
	}
	
	public void addVertex() {
		if (numVertices==maxVertices) {
			System.out.println("Cannot add new vertices!!!");
			return;
		}
		numVertices++;
		vertices[numVertices-1]=new DListVertex(); 
	}
		
	@Override
	public int sizeVertices() {
		return numVertices;
	}

	@Override
	public int sizeEdges() {
		int numEdges=0;
		for (int i=0;i<numVertices;i++) {
			if (vertices[i]!=null) numEdges=+vertices[i].getSize();
		}
		if (!directed) numEdges=numEdges/2;
		return numEdges;
	}

	
	public int getOutDegree(int i) {
		if (!checkVertex(i)) 
			throw new IllegalArgumentException("Nonexistent vertex  " + i);
		
		int outdegree=0;
		outdegree=vertices[i].getSize();
		return outdegree;
	}
	
	public int getInDegree(int i) {
		if (!checkVertex(i)) 
			throw new IllegalArgumentException("Nonexistent vertex  " + i);
		int indegree=0;
		for (int j=0; j<numVertices;j++) {
			if (vertices[j].contains(i)) indegree++;
		}
		return indegree;
	}

	
	
	
	
	@Override
	public int getDegree(int i) {
		int degree=0;
		if (directed) {
			degree=getOutDegree(i)+getInDegree(i);
		} else degree=vertices[i].getSize();
		return degree;
	}
	
	@Override
	public void addEdge(int i, int j) {
		//by default, we add an edge with value null;
		addEdge(i,j,null);
	}
	//check if i is a right vertex
	private boolean checkVertex(int i) {
		if (i>=0 && i<numVertices) return true;
		else return false;
	}
		
	public void addEdge(int i, int j, String palabra) {
		if (!checkVertex(i)) 
			throw new IllegalArgumentException("Nonexistent vertex  " + i);
		if (!checkVertex(j)) 
			throw new IllegalArgumentException("Nonexistent vertex  " + j);
		
		vertices[i].addLast(j,palabra);
		//if it is a non-directed graph
		if (!directed) vertices[j].addLast(i,palabra);
	}

	
	
	public void removeEdge(int i, int j) {
		if (!checkVertex(i)) 
			throw new IllegalArgumentException("Nonexistent vertex  " + i);
		if (!checkVertex(j)) 
			throw new IllegalArgumentException("Nonexistent vertex  " + j);
		
		int index=vertices[i].getIndexOf(j);
		vertices[i].removeAt(index);
		
		if (!directed) {
				index=vertices[j].getIndexOf(i);
				vertices[j].removeAt(index);
		}
	}

	@Override
	public boolean isEdge(int i, int j) {
		if (!checkVertex(i)) 
			throw new IllegalArgumentException("Nonexistent vertex  " + i);
		if (!checkVertex(j)) 
			throw new IllegalArgumentException("Nonexistent vertex  " + j);
		
		boolean result=vertices[i].contains(j);
		return result;
	}

	@Override
	public String getStringEdge(int i, int j) {
		if (!checkVertex(i)) throw new IllegalArgumentException("Nonexistent vertex  " + i);
		if (!checkVertex(j)) throw new IllegalArgumentException("Nonexistent vertex  " + j);
		String result=null;
		int index=vertices[i].getIndexOf(i);
		if (index!=-1) {
			result=vertices[i].getStringAt(index);
		}
		return result;
	}

	@Override
	public void show() {

		for (int i=0; i<numVertices; i++) {
			if (vertices[i]!=null) {
				System.out.println("adjacentes vertices for vertex  " + palabras[i] +"[" +i+"]"+ ": " + vertices[i].toString());
			}
		}
		
	}

	@Override
	public int[] getAdjacents(int i) {
		// TODO Auto-generated method stub
		int adjacents[] = new int[vertices[i].getSize()]; 
		
		DNodeVertex aux = vertices[i].header.next;
		int actualListSize = vertices[i].getSize();
		for(int j = 0; j < actualListSize; j++){
			adjacents[j] = aux.vertex;
			aux = aux.next;
		}
		
		return adjacents;
	}
	public static String[] readFile(String file) throws IOException{
		
		String result = "";
	    
	    FileReader f = new FileReader(file);
	    BufferedReader b = new BufferedReader(f);
	    
	    System.out.println("Loading file...");	
	    
	    result += b.readLine();
	    while(b.readLine() != null){
	    	result += b.readLine();
	    }
	    b.close();
	    
	    result = result.replaceAll("á","a");
	    result = result.replaceAll("é","e");
	    result = result.replaceAll("í","i");
	    result = result.replaceAll("ó","o");
	    result = result.replaceAll("ú","u");
	    result = result.replaceAll("ñ","n");
	    result = result.replaceAll("[^a-zA-Z ]", "");
	    result = result.toLowerCase();
	   
	    System.out.println(result);

	    //String[] resultNoSpaces = result.split("");
	    //System.out.println(resultNoSpaces[0]);
	    String [] finalListWords = result.split(" ");
	    return finalListWords;
	}
	
	/*FASE 3 METHODS*/
	public static DList getWords(int n, String file) throws FileNotFoundException, IOException{
		
		SQueue text = new SQueue();
		text.readFile(file);
		DList listaTexto = new DList();
		listaTexto.add(text);
		
		DNode actual = listaTexto.header.next;
		int initialSize = listaTexto.size;
		if(initialSize < n){
			return null;
		}
		//comprobar palabras con long menor de la deseada
		for(int i = 0; i < initialSize; i++){
			int larguito = actual.elem.length();
			if(larguito < 2){//DECISION DISEÑO
				//si el nodo alberga una palabra de longitud menor que dos se BORRA para no tener en cuenta palabras de una sola letra.
				actual.prev.next = actual.next;
				actual.next.prev = actual.prev;
				actual.prev = null;
				listaTexto.size--;
			}
			//step
			actual = actual.next;
		}
		//Borrar palabras al azar hasta quedarnos con una lista de n elementos 
		//a partir de la lista en la que se eliminaron las palabras más cortas
		int initialSize2 = listaTexto.size;
		int high = initialSize2;
		int result;
		for(int i=0; i<initialSize2-n;i++){
			Random r = new Random();
			result = r.nextInt(high);
			high--;
			listaTexto.removeAt(result);
		}
		return listaTexto;
	}
//		DList myList = new DList();
//		
//		String[] myArray = readFile(file);
//		if(myArray.length < n){
//			System.out.println("[getWords] no se admiten más paabras de las que puede ofrecer el archivo["+myArray.length+"]");
//			return null;
//		}
//		String palabra;
//		int result;
//		boolean encontrado = false;
//		
//		for(int i = 0; i < n; i++){
//				while(encontrado == false){
//					Random r = new Random();
//					result = r.nextInt(myArray.length);
//					palabra = myArray[result];
//					if(myList.contains(palabra) == false && palabra.length() > 2){
//						myList.addFirst(palabra);
//						encontrado = true;
//					}
//				}
//				encontrado = false;	
//			}
//		return myList;
//		}

	
	public static String getStringTail(String palabra){
		
		char veryLast = palabra.charAt(palabra.length()-1);
		char Last = palabra.charAt(palabra.length()-2);
		String palabraResult = "" + Last + veryLast;
		
		return palabraResult;
	}
	
	public static String getStringHead(String palabra){
		
		char first = palabra.charAt(0);
		char second = palabra.charAt(1);
		String palabraResult = "" + first + second;
		
		return palabraResult;
	}
	
	public void clearRelations(){
		for(int i=0; i<this.sizeVertices();i++){//Establecer las relaciones para cada vertice
			this.vertices[i].header.next = this.vertices[i].trailer;
			this.vertices[i].trailer.prev = this.vertices[i].header;
		}
	}
	
	public void relateVertices(){
		this.clearRelations();
		for(int i=0; i<this.sizeVertices();i++){//Establecer las relaciones para cada vertice
			this.relateVertex(i);
		}
	}
	
	public void relateVertex(int index){
		for(int i = 0; i<palabras.length; i++){
			if(getStringTail(palabras[index]).compareTo(getStringHead(palabras[i])) == 0  && i != index){
				addEdge(index,i);
			}
		}
	}
	
	public boolean repetida(String palabra){
		boolean repetida = false;
		for(int i = 0; i < palabras.length; i++){
			if(palabras[i].compareTo(palabra) == 0){
				repetida = true;
				break;
			}
		}
		return repetida;
	}
	
	public void replaceSumideros(String file) throws IOException{
		
		//Take list with words from file
		SQueue cola = new SQueue();
		
		DList lista = new DList();
		
		
		String palabraNueva = "";
		int randomIndex;
		boolean allOk = true;
		boolean sumidero = true;
		System.out.println("[replaceSumideros]fixing sumideros...");
		//Start fixing sumideros
		/*Comprobar si el grafo originalmente no tiene sumideros[I]*/
		for(int i = 0 ; i <this.numVertices; i++){
			if(this.getOutDegree(i) == 0){//sumidero...
				allOk = false;
				break;
			}
		}
		if(allOk){/*Comprobar si el grafo originalmente no tiene sumideros[II]*/
			System.out.println("[replaceSumideros]El grafo introducido no tiene sumideros.");
			return;
		}
		/*Si se llega aqui es que hay sumideros en el grafo y se intenta arreglar*/
		for(int i = 0; i < this.numVertices; i++){
					cola.readFile(file);//Rebuild list from all possible text words 
					lista.add(cola);
			if(this.vertices[i].isEmpty()){//¿Sumidero?
				palabraNueva = "";
				sumidero = true;
				while(sumidero == true){
					while(palabraNueva.length() < 2 || repetida(palabraNueva) == true){
						if(lista.size == 0){//si NO hay elementos en la LISTA...
							System.out.println("[replaceSumideros] No se pudo crear un grafo en el que no existan sumideros porque se agotaron las palabras con las que probar");
							return;
						}else if(lista.size == 1){//SI hay UN elemento en la LISTA
							palabraNueva = lista.getAt(0);//coger palabra de la lista
							lista.removeAt(0);//quitar palabra de la lista
						}
						else{//SI aun hay elementos en la LISTA
							Random r = new Random();
							/*escoger palabra al azar...*/
							randomIndex = r.nextInt(lista.size-1);//get random index
							palabraNueva = lista.getAt(randomIndex);//coger palabra de la lista
							lista.removeAt(randomIndex);//quitar palabra de la lista
						}
						/*probar con la palabra escogida al azar...*/
					}			
					this.palabras[i] = palabraNueva;//Meter la palabra en nuestro array de palabras
					this.relateVertices();//volver a establecer relaciones
					if(vertices[i].isEmpty() == false){//si palabra esocogida no Sumidero...
						sumidero = false;
						
					}
					
				}
				System.out.println("[replaceSumideros][Grafo resultante en el STEP ["+i+"]");
				this.show();
				System.out.println();
			}
		}
		System.out.println("[replaceSumideros][FINISHED tries of replace sumideros]");
	}
	//0: adfsa  fdsa 
	//1: fd 
	//2: fd  ds  fdsa
	
	
	
	/*
	public void depth(){
		System.out.println("depth traverse of the graph: ");
		boolean visited[] = new boolean[numVertices];
		for(int i = 0; i < numVertices; i++){
			if(!visited[i]) depth(i, visited);
		}
	}
	*/
	
	public DList cadenaMasLarga(){
		//Array donde guardaremos las longitudes de cada cadena dependiendo del vertice de inicio
		//int[] largos = new int [this.numVertices];
 		DList [] cadenas = new DList[this.numVertices];
		int indexMax = -1;
		int mayorLongitud = 0;
 		
		for(int i = 0; i < this.numVertices-1; i++){//calcular la cadena más larga desde cada vertice
			cadenas[i] = this.cadenaLarga(this.palabras[i]);//y guardarlo en el array cadenas (IMPORTANTE: cadenaLarga devuelve listas con un size >= 1)
			//largos[i] = cadenas[i].size;
			if(cadenas[i].size != 1 && i < this.numVertices){//Si hay una cadena...
				if(cadenas[i].size > mayorLongitud){
					indexMax = i;
					mayorLongitud = cadenas[i].size;
				}
			}
		}
		if(indexMax == -1){
				System.out.println("cadenaMasLarga() no pudo funcionar con un grafo no conexo");
				return null;
		}
		//int indexOfGreatest = arrayGetIndexOfMax(largos);
		return cadenas[indexMax];
	}
	
	public DList cadenaLarga(String palabra){
		
		boolean encontrado = false;
		int index = 0;
		
		for(int i = 0; i < palabras.length; i++){
			if(this.palabras[i].compareTo(palabra) == 0){
				index = i;
				encontrado = true;
				break;
			}
		}
		if(encontrado == false){
			System.out.println("La palabra: ["+palabra+"]"+" especificada no se encuentra en el grafo");
			return null;
		}else{
			
			boolean visited[] = new boolean[numVertices];
			
			DList listaDef = new DList();
			
			return cadenaLarga(index, visited, listaDef);
		}
		
		
	}
	public DList cadenaLarga(int i, boolean[]visited, DList listaDef){
		visited[i] = true;
		DList[] posiblesCadenas = new DList[this.numVertices-1];//como muxho los caminos posibles a patir de un vértice iran a los demas nodos
		int index = - 1;
		int maxSize = 0;
		if(this.vertices[i].getSize() == 0){//CASO BASE-->SUMIDERO O VISITADO
			DList listaAux = new DList();
			listaAux.addLast(palabras[i]);
			posiblesCadenas[0] = listaAux;
			return posiblesCadenas[0];
		}else{
		
			int[] adjacents = this.getAdjacents(i);//Ver nodos adyacentes al nodo actual
			int contador = 0;
			for(int adjV: adjacents){
				
					if(!visited[adjV] && contador < adjacents.length){
						//only visit  those adjacent vertices that
						//have not been visited yet.
						
							posiblesCadenas[contador] = cadenaLarga(adjV, visited, listaDef);
						
					}else if(visited[adjV]){//Si ya se ha visitado se devuelve a si mismo
						DList listaAux = new DList();
						listaAux.addLast(palabras[i]);
						posiblesCadenas[0] = listaAux;
						return posiblesCadenas[0];
					}
					contador ++;
			}
			//una vez el nodo recibe todas las cadenas escoge la mas larga
			for(int k = 0; k < adjacents.length; k++){//Comprobar dentro de nuestra lista de cadenas el indice de la mas larga
				if(posiblesCadenas[k].size > maxSize){
					maxSize = posiblesCadenas[k].size;
					index = k;
				}
			}
			visited[i] = true;
			posiblesCadenas[index].addFirst(palabras[i]);//se añade a si misma a a cadena mas larga que llega
			
			
			return posiblesCadenas[index];//pasar al nodo anterior la cadena más larga habiendose incluido la palabra del nodo actual en ella
		}
	}
}
