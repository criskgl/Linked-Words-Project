import java.io.IOException;

import cpBST.DictionaryTree;
import cpBST.DictionaryTreeFrec;
import cpDList.DList;
import cpQueue.SQueue;
import graphs.GraphLA;

public class principal{
	public static void main(String[] args) throws IOException{
		String file="src/file.txt";	
		
//		//Crear diccionario Lista
//		DList diccionario = new DList();
//		SQueue cola = new SQueue();
//		//Añadir palabras a la cola y filtrar signos de puntuación	
//		try { 
//			cola.readFile(file);                       
//	    } catch (IOException ioex) { 
//	    	System.out.println("File  " +file + " not found in "+System.getProperty("user.dir"));
//	        throw ioex;
//	    } 

//FASE 1
//		System.out.println("");
//		long tiempoInicio = System.currentTimeMillis();
//		diccionario.add(cola);
//		long tiempoTotal = System.currentTimeMillis() - tiempoInicio;
//		System.out.println("La cola se ha añadido a la lista doblemente enlazada en: "+tiempoTotal+" milisegundos");
//		System.out.println("****COLA AÑADIDA A LA LISTA*****");
//		System.out.println(diccionario.toString());
//	
//		System.out.println("****GET-TOP 10*****");
//		tiempoInicio = System.currentTimeMillis();
//		DList listaTop = diccionario.getTop(10);
//		tiempoTotal = System.currentTimeMillis() - tiempoInicio;
//		System.out.println("Se ha conseguido el top 10 en : "+tiempoTotal+" milisegundos");
//		System.out.println(listaTop.toString());
//	
//		System.out.println("****GET-LOW 10*****");
//		tiempoInicio = System.currentTimeMillis();
//		DList listaLow = diccionario.getLow(10);
//		tiempoTotal = System.currentTimeMillis() - tiempoInicio;
//		System.out.println("Se ha conseguido el low 10 en : "+tiempoTotal+" milisegundos");
//		System.out.println(listaLow.toString());
//
//		System.out.println("****Search(frec)*****");
//		String palabraPrueba = "de";
//		System.out.println("La frecuencia de la palabra ["+palabraPrueba+"] es: "+diccionario.search(palabraPrueba)); 
//		
//		System.out.println("****SHOW A*****");
//		diccionario.show('A');
//		
//		System.out.println("****SHOW OTHER*****");
//		diccionario.show('K');
		
//FIN FASE 1
		
//FASE 2
		
//		DictionaryTree arbol = new DictionaryTree();//creamos el arbol que albergará el diccionario
//		System.out.println("*********add´s have been done*********");
//		arbol.add(cola);
//
//		System.out.println("*********toString2(extra, solo para ver arbol)*********");
//		System.out.println(arbol.toString2());
//	
//		System.out.println("ROOT: "+arbol.root.elem);
//		System.out.println("*********inOrder*********");
//		arbol.getInorder();
//		System.out.println("*********inOrderReverse*********");
//		arbol.getInorderReverse();
//		System.out.println("*********Search*********");
//		System.out.println("La palabra <de> ha sido encontrada y tiene frecuencia: "+arbol.search("de"));
//		DList max,low;
//		System.out.println("*********getTop 7*********");
//		max = arbol.getTop(7);
//		System.out.println(max.toString());
//		System.out.println("*********getLow 7*********");
//		low = arbol.getLow(7);
//		System.out.println(low.toString());
///////////*********DICCIONARIO DE FRECUENCIAS ASOCIADO A LISTAS DE PALABRAS
//		System.out.println("*********DICCIONARIO DE FRECUENCIAS ASOCIADO A LISTAS DE PALABRAS*********");
//		DictionaryTreeFrec arbolFrec = new DictionaryTreeFrec();
//		arbolFrec.save(diccionario);
//		System.out.println("done");
//FIN FASE 2
		
//FASE 3
//		DList lista = new DList();
//		System.out.println("**********getWords********");
//		lista = GraphLA.getWords(500,file);
//		if(lista == null){
//			System.out.println("[getWords-->null]no se pueden usar más palabras que las que has proporcionado en el fichero de entrada");
//		}else{
//			System.out.println("****getWords done succesfully****");
//			/*Create our brand new graph*/
//			GraphLA myGraph = new GraphLA(lista.size, lista.size, true, lista);
//			myGraph.relateVertices();
//REPLACE SUMIDEROS
//			System.out.println("**********show********");
//			myGraph.show();
//			System.out.println("**********replaceSumideros********");
//			myGraph.replaceSumideros(file);
//			System.out.println("**********show********");
//			myGraph.show();
//CADENA LARGA		
//			System.out.println("**********cadenaLarga********");
//		if(myGraph.cadenaLarga("toda") == null){
//			System.out.println("cadenaLarga() ha devuelto null porque no se encuentra recorrido para la palabra");
//		}else{
//			DList palabrasEncadenadas = myGraph.cadenaLarga("toda");
//			
//			System.out.println(palabrasEncadenadas.toStringNoFrec());
//		}			 
//CADENA MAS LARGA		
//			System.out.println("**********cadenaMasLarga********");
//			DList cadenaMasLarga = myGraph.cadenaMasLarga();
//			if(cadenaMasLarga != null){
//				System.out.println(cadenaMasLarga.toStringNoFrec());
//			}
			

//		}
	}
}