package pr2.uebung03;

//import graphvisualizer.*;

public class TreeNode {
	// Variablen deklarieren
	private Element elem;
	private TreeNode left;
	private TreeNode right;

	/**
	 * Konstruktor der Klasse TreeNode.
	 * 
	 * @param e Element, welches übergeben wird und als Knoten-Element gesetzt wird.
	 */
	public TreeNode(Element e) {
		this.elem = e;
		this.left = this.right = null;
	}

	/**
	 * Methode zum Ermitteln des linken Knotens (Node).
	 * 
	 * @return gibt den linken Knoten (Node) zurück.
	 */
	public TreeNode getLeft() {
		return this.left;
	}

	/**
	 * Methode zum Ermitteln des rechten Knotens (Node).
	 * 
	 * @return gibt den rechten Knoten (Node) zurück.
	 */
	public TreeNode getRight() {
		return this.right;
	}

	/**
	 * Methode zum Ermitteln des Elements.
	 * 
	 * @return gibt das Element zurück.
	 */
	public Element getElement() {
		return this.elem;
	}

	/**
	 * Methode zum Setzen des linken Knotens (Node).
	 * 
	 * @param n Wert, welcher dem linken Knoten (Node) zugewiesen wird.
	 */
	public void setLeft(TreeNode n) {
		this.left = n;
	}

	/**
	 * Methode zum Setzen des rechten Knotens (Node).
	 * 
	 * @param n Wert, welcher dem rechten Knoten (Node) zugewiesen wird.
	 */
	public void setRight(TreeNode n) {
		this.right = n;
	}

	/**
	 * Methode zum Setzen des Elements.
	 * 
	 * @param e Wert, welcher dem Element zugewiesen wird.
	 */
	public void setElement(Element e) {
		this.elem = e;
	}

//	/**
//	 * Methode zum Visuellen-Ausgeben von Binären-Bäumen
//	 * 
//	 * @return children of this node
//	 */
//	public VisualizableNode[] getChildren() {
//		return new VisualizableNode[] { (VisualizableNode) this.left, (VisualizableNode) this.right };
//	}

	@Override
	public String toString() {
		return this.elem + " ";
	}
	
	public Object getKey(){
		return elem.getKey();
	}
}