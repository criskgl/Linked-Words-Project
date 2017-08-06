package cpQueue;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface IQueue {
	
	public boolean isEmpty();

	public void enqueue(String word);

	public String dequeue();

	public String front();
	
	public String toString();

	public void readFile(String file) throws FileNotFoundException, IOException;
	
	

}
