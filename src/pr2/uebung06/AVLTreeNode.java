package pr2.uebung06;

//import graphvisualizer.*;
import pr2.uebung03.Element;
import pr2.uebung03.IntElement;

public class AVLTreeNode {//implements VisualizableOneKeyNode {
	// Variablen deklarieren
	private AVLTreeNode left;
	private AVLTreeNode right;

	private int balance = 0;

	private Comparable key;

	/**
	 * Konstruktor der Klasse AVLTreeNode. Setzt als Default-Wert den rechten und
	 * linken Nachfolger-Knoten gleich null.
	 * 
	 * @param elem Key-Wert der als aktuellen Wert in der AVLTreeNode gespeichert
	 *             wird.
	 */
	public AVLTreeNode(Comparable key) {
		this.key = key;
		this.left = this.right = null;
	}

	@Override
	public String toString() {
		return "" + key + "(" + balance + ") ";
	}

	/**
	 * Setzt den rechten Nachfolger-Knoten des aktuellen Knotens.
	 * 
	 * @param right Wert, welcher im rechten Knoten gespeichert wird.
	 */
	public void setRight(AVLTreeNode right) {
		this.right = right;
	}

	/**
	 * Setzt den linken Nachfolger-Knoten des aktuellen Knotens.
	 * 
	 * @param left Wert, welcher im linken Knoten gespeichert wird.
	 */
	public void setLeft(AVLTreeNode left) {
		this.left = left;
	}

	/**
	 * Liefert den Wert, der im rechten Knoten gespeichert ist.
	 * 
	 * @return Gibt den Wert des rechten Nachfolger-Knotens zurück.
	 */
	public AVLTreeNode getRight() {
		return this.right;
	}

	/**
	 * Liefert den Wert, der im linken Knoten gespeichert ist.
	 * 
	 * @return Gibt den Wert des rechten Nachfolger-Knotens zurück.
	 */
	public AVLTreeNode getLeft() {
		return this.left;
	}

	/**
	 * Setzt den Wert des aktuellen Knotens.
	 * 
	 * @param key Wert vom Typ Comparable, der im aktuellen Knoten gespeichert wird.
	 */
	public void setValue(Comparable key) {
		this.key = key;
	}

	/**
	 * Liefert den Wert des aktuellen Knotens.
	 * 
	 * @return Gibt den Wert vom Typ Comparable, der im aktuellen Knoten gespeichert
	 *         ist, zurück.
	 */
	public Comparable getValue() {
		return this.key;
	}

	/**
	 * Setzt die Balance des aktuellen Knotens.
	 * 
	 * @param balance Balance-Wert, welcher in dem Knoten gesetzt wird.
	 */
	public void setBalance(int balance) {
		this.balance = balance;
	}

	/**
	 * Liefert den Balance-Wert des aktuellen Knotens.
	 * 
	 * @return Gibt den Balance-Wert vom Typ Integer des aktuellen Knotens zurück
	 */
	public int getBalance() {
		return this.balance;
	}

	// @Override
	public Object getKey() { // Pro Knoten wird der key + der Balancefaktor beim Aufruf von visualize
								// ausgegeben
		return "" + key + " (" + balance + ")";
	}

//	/**
//	 * @return children of this node
//	 */
//	public VisualizableNode[] getChildren() {
//
//		return new VisualizableNode[] { left, right };
//	}
}