package cpBST;

public class DictionaryTreeNode implements IDTNode {

		public Integer frec;
		public String elem;

		DictionaryTreeNode parent;
		DictionaryTreeNode left;
		DictionaryTreeNode right;

		
		public DictionaryTreeNode(Integer k, String e) {
			frec = k;
			elem = e;
		}

		public boolean isInternal() {
			return (left != null || right != null);
		}
		
		public boolean isExternal() {
			return (left == null && right == null) ;
		}
		
		public boolean isRoot() {
			return (parent == null);
		}

		public int getSize() {
			return getSize(this);
		}

		private static int getSize(DictionaryTreeNode node) {
			if (node == null)
				return 0;
			else
				return 1 + getSize(node.left) + getSize(node.right);
		}
	
		public int getHeight() {
			return getHeight(this);
		}

		private static int getHeight(DictionaryTreeNode node) {
			if (node == null)
				return -1;
			else
				return 1 + Math.max(getHeight(node.left),
						getHeight(node.right));
		}

		public int getDepth() {
			return getDepth(this);
		}

		private static  int getDepth(DictionaryTreeNode node) {
			if (node == null)
				return -1;
			else
				return 1 + getDepth(node.parent);
		}
		
		public DictionaryTreeNode getRoot() {
			if (parent == null) {
				return this;
			} else {
				return parent.getRoot();
			}
		}
		
		public DictionaryTreeNode getRootIterativo() {
			DictionaryTreeNode root=this;
			if (root!=null) {				
				while (root.parent != null) 
					root=root.parent;				
			}
			return root;
		}		
		
		public void getPreorder() {		
			getPreorder(this);
		}
		
		public void getInorder() {
			getInorder(this);
		}
		
		public void getPostorder() {
			getPostorder(this);
		}
		
		private static void getPreorder(DictionaryTreeNode node) {
			if (node == null) return;
			
			System.out.println("("+node.frec+","+node.elem+")");
			getPreorder(node.left);
			getPreorder(node.right);
		}
		
		private static  void getInorder(DictionaryTreeNode node) {
			if (node == null) return;
			
			getInorder(node.left);
			System.out.println("("+node.frec+","+node.elem+")");
			getInorder(node.right);
		}
		
		private static  void getPostorder(DictionaryTreeNode node) {
			if (node == null) return;
			getPostorder(node.left);
			getPostorder(node.right);
			System.out.println("("+node.frec+","+node.elem+")");
		}
	
		
		//con este m�todo podremos m�s adelante imprimir los �rboles con una estructura intuitiva
		//que nos proporcione evidencias extra de que estamos costruyendo el �rbol de manera correcta
		//tambi�n podemos utilizar VisualEDA
		public StringBuilder toString(StringBuilder prefix, boolean isTail, StringBuilder sb) {
		    if(right!=null) {
		        right.toString(new StringBuilder().append(prefix).append(isTail ? "|   " : "    "), false, sb);
		    }
		    sb.append(prefix).append(isTail ? "\\---" : "/---").append(elem.toString()).append("\n");
		    if(left!=null) {
		        left.toString(new StringBuilder().append(prefix).append(isTail ? "    " : "|   "), true, sb);
		    }
		    return sb;
		}

		@Override
		public String toString() {			
			System.out.println("\nImprimimos el arbol:\n\n");
			return this.toString(new StringBuilder(), true, new StringBuilder()).toString();			
		}
		
		public void show(char c){
			if(c == 'A'){
				this.getInorder();
			}else{
				this.getInorderReverse();
			}
		}
		
		public static  void getInorderReverse(DictionaryTreeNode node) {
			if (node == null) return;
			
			getInorderReverse(node.right);
			System.out.println("("+node.frec+","+node.elem+")");
			getInorderReverse(node.left);
		}
		public void getInorderReverse() {
			getInorderReverse(this);
		}
		
}
