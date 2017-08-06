package stack;

public class SStack implements IStack {
	SNodeVertex peak = null;

	
	public boolean isEmpty() {
		return peak == null;
	}

	public void push(int newElem) {
		SNodeVertex newNode = new SNodeVertex(newElem);
		newNode.next = peak;
		peak = newNode;
	}

	public int pop() {
		if (isEmpty()) {
			System.out.println("The stack is empty.");
			return -1;
		}
		int elem = peak.elem;
		peak = peak.next;
		return elem;
	}

	
	public int top() {
		if (isEmpty()) {
			System.out.println("The stack is empty.");
			return -1;
		}
		return peak.elem;
	}

	public String toString() {
		String result = null;
		for (SNodeVertex nodeIt = peak; nodeIt != null; nodeIt = nodeIt.next) {
			if (result == null) {
				result = "[" + nodeIt.elem + "]";
			} else {
				result += "," + nodeIt.elem;
			}
		}
		return result == null ? "empty" : result;
	}

	

	public int getSize() {
		int size = 0;
		SNodeVertex node = peak;
		while ( node != null) {
			size++;
			node = node.next;
		}
		return size;
	}


	
}
