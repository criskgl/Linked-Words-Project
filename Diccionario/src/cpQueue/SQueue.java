package cpQueue;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class SQueue implements IQueue{
	private SNodeString first;
	private SNodeString last;
	private int size;

	public boolean isEmpty() {
		return first == null;
	}
	
	public void enqueue(String elem) {
		SNodeString node = new SNodeString(elem);
		size++;
		if (isEmpty()) {
			first = node;
		} else {
			last.next = node;
		}
		last = node;
	}

	public String dequeue() {
		if (isEmpty()) {
			//System.out.println("Queue is empty!");
			return null;
		} else {
			String firstElem = first.word;
			first = first.next;
			if (first == null) {
				last = null;
			}
			size--;
			return firstElem;
		}
	}

	public String front() {
		if (isEmpty()) {
			//System.out.println("Queue is empty!");
			return null;
		}
		return first.word;
	}

	@Override
	public String toString() {
		String result = "";
		for (SNodeString nodeIt = first; nodeIt != null; nodeIt = nodeIt.next) {
			if (result == null) {
				result = "[" + nodeIt.word.toString() + "]";
			} else {
				result += " " + nodeIt.word.toString();
			}
		}
		return result == null ? "empty" : result;
	}

	public int getSize() {
		return size;
	}

	@Override
	public void readFile(String file) throws FileNotFoundException, IOException{
		// carga el fichero en la cola  
	    String result = "";
	    
	    FileReader f = new FileReader(file);
	    BufferedReader b = new BufferedReader(f);
	    
	    //System.out.println("Loading file...");	<<<<DESCOMENTAR
	    
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
	   
	   // System.out.println(result); <<<<DESCOMENTAR

	    //String[] resultNoSpaces = result.split("");
	    //System.out.println(resultNoSpaces[0]); 
	    String [] finalListWords = result.split(" ");
	    
	    for(int i = 0; i < finalListWords.length; i++){
	    	this.enqueue(finalListWords[i]);
	    }
	    
	    //System.out.println("File loaded"); <<<<DESCOMENTAR
	}
	

	
}

