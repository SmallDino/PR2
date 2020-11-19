package pr2.hausarbeit;

//import graphvisualizer.*;
import java.util.*;

public class BTreeNode<E extends Comparable<E>> { //implements VisualizableOneKeyNode {
	// Node-Comparator
	private Comparator<BTreeNode<E>> nodeComparator = new Comparator<BTreeNode<E>>() {
		@Override
		public int compare(BTreeNode<E> nodeL, BTreeNode<E> nodeR) {
			return nodeL.getElement(0).compareTo(nodeR.getElement(0));
		}
	};
	// Variablen deklarieren
	private E[] elements; // Repräsentiert die Elemente im B-Baum
	private int elementLevel; // Anzahl der vorhandenen Elemente im Knoten
	private BTreeNode<E>[] childNodes; // Repräsentiert die Kind-Knoten im B-Baum
	private int childLevel; // Anzahl der vorhandenen Kind-Knoten des aktuellen Knoten
	private BTreeNode<E> parent; // Repräsentiert den Eltern-Knoten des aktuellen Knoten

	/**
	 * Konstruktor von BTreeNode.
	 * 
	 * @param parent      Eltern-Knoten des aktuellen Knoten.
	 * @param maxElements Anzahl der maximalen Elemente, die in einem Knoten
	 *                    gespeichert werden dürfen.
	 * @param maxChilds   Anzahl der maximalen Kind-Knoten, die in einem Knoten
	 *                    gespeichert werden dürfen.
	 */
	@SuppressWarnings("unchecked")
	public BTreeNode(BTreeNode<E> parent, int maxElements, int maxChilds) {
		this.parent = parent;
		this.elements = (E[]) new Comparable[maxElements + 1];
		this.elementLevel = 0;
		this.childNodes = new BTreeNode[maxChilds + 1];
		this.childLevel = 0;
	}

	/**
	 * Fügt ein Element dem aktuellen Knoten hinzu.
	 * 
	 * @param key Element, welches dem Knoten hinzugefügt werden soll.
	 */
	public void addElement(E key) {
		// Füge das Element im nächsten freien Platz im Element-Array ein
		this.elements[this.elementLevel] = key;
		// Erhöhe die Anzahl der aktuellen Elemente
		this.elementLevel++;
		// Sortiere die Elemente (Wo - Von - Bis)
		Arrays.sort(this.elements, 0, this.elementLevel);
	}

	/**
	 * Durchsucht den aktuellen Knoten nach dem Element an der übergebenen Position
	 * und gibt dieses Element zurück.
	 * 
	 * @param elemPosition Position an der das Element im Knoten vorhanden ist.
	 * @return Gibt das Element zurück, der unter der angegebenen Position
	 *         gespeichert ist.
	 */
	public E getElement(int elemPosition) {
		return this.elements[elemPosition];
	}

	/**
	 * Ermittelt die aktuelle Anzahl vorhandener Elemente im Knoten.
	 * 
	 * @return Gibt die aktuelle Anzahl vorhandener Elemente im Knoten zurück.
	 */
	public int getElementLevel() {
		return this.elementLevel;
	}

	/**
	 * Fügt einen Kind-Knoten dem aktuellen Knoten hinzu.
	 * 
	 * @param childNode Kind-Knoten, welcher dem aktuellen Knoten hinzugefügt wird.
	 * @return Gibt true zurück, nachdem der Kind-Knoten hinzugefügt wurde.
	 */
	public boolean addChildNode(BTreeNode<E> childNode) {
		// Aktueller Knoten ist automatisch Eltern-Knoten des Kind-Knotens
		childNode.parent = this;
		// Füge den Kind-Knoten im nächsten freien Platz im Kind-Array ein
		this.childNodes[this.childLevel] = childNode;
		// Erhöhe die Anzahl der Kind-Knoten
		this.childLevel++;
		// Sortiere die Kind-Knoten (Wo - Von - Bis - Comparator)
		Arrays.sort(this.childNodes, 0, this.childLevel, nodeComparator);
		return true;
	}

	/**
	 * Durchsucht den aktuellen Knoten nach dem Kind-Knoten an der übergebenen
	 * Position und gibt diese Kind-Knoten zurück.
	 * 
	 * @param childPosition Position an der der Kind-Knoten gespeichert ist.
	 * @return Gibt den Kind-Knoten zurück, der unter der angegebenen Position
	 *         gespeichert ist.
	 */
	public BTreeNode<E> getChildNode(int childPosition) {
		// Position ist größer als es aktuell vorhandene Kind-Knoten gibt
		if (childPosition >= this.childLevel) {
			return null;
		} else { // Gebe den Kind-Knoten an der Position zurück
			return this.childNodes[childPosition];
		}
	}

	/**
	 * Löscht den übergebenen Kind-Knoten aus dem aktuellen Knoten.
	 * 
	 * @param childNode Kind-Knoten der aus dem aktuellen Knoten gelöscht werden
	 *                  soll.
	 * @return Gibt true zurück, wenn der Kind-Knoten gelöscht wurde, sonst false.
	 */
	public boolean removeChildNode(BTreeNode<E> childNode) {
		// Variablen deklarieren
		boolean childFound = false;
		// Keine Kind-Knoten vorhanden
		if (this.childLevel == 0) {
			return childFound;
		} else { // Kind-Knoten vorhanden
			// Finde den passenden Kind-Knoten
			for (int i = 0; i < childLevel; i++) {
				if (this.childNodes[i].equals(childNode)) {
					childFound = true;
				} else if (childFound) {
					// Kind-Knoten wurde gefunden
					this.childNodes[i - 1] = this.childNodes[i]; // Restliche Kind-Knoten nach links verschieben
				}
			}
			// Kind-Knoten wurde gefunden
			if (childFound) {
				// Verringere die Anzahl an Kind-Knoten
				this.childLevel--;
				// Lösche das letzte Element
				this.childNodes[this.childLevel] = null;
			}
		}
		return childFound;
	}

	/**
	 * Ermittelt die aktuelle Anzahl von Kind-Knoten.
	 * 
	 * @return Gibt die aktuelle Anzahl an Kind-Knoten zurück.
	 */
	public int getChildLevel() {
		return this.childLevel;
	}

	/**
	 * Ermittelt den Eltern-Knoten des aktuellen Knotens.
	 * 
	 * @return Gibt den Eltern-Knoten des aktuellen Knotens zurück.
	 */
	public BTreeNode<E> getParent() {
		return this.parent;
	}

	/**
	 * Setzt den Eltern-Knoten des aktuellen Knotens.
	 * 
	 * @param parentNode Eltern-Knoten, welcher als neuer Eltern-Knoten gesetzt
	 *                   werden soll.
	 */
	public void setParent(BTreeNode<E> parentNode) {
		this.parent = parentNode;
	}

	/**
	 * Durchsucht den aktuellen Knoten nach dem übergebenen Schlüssel-Element.
	 * 
	 * @param key Übergebenes Schlüssel-Element, welches gesucht wird.
	 * @return Gibt true zurück, wenn der Schlüssel vorhanden ist, sonst false.
	 */
	public boolean search(E key) {
		for (int i = 0; i < this.elements.length; i++) {
			if (elements[i] == null) {
				return false;
			} else if (key.compareTo(this.elements[i]) == 0) {
				return true;
			}
		}
		return false;
	}

//	/**
//	 * Visualisiert den B-Baum.
//	 * 
//	 * @return Gibt die Kind-Knoten des B-Baums zurück.
//	 */
//	public VisualizableNode[] getChildren() {
//		// TODO Funktioniert leider nicht seit dem neuen Update...
//		return new VisualizableNode[] { (VisualizableNode) (this.childNodes[0]) };
//	}

	@Override
	public String toString() {
		String values = "";
		for (int i = 0; i < this.elements.length; i++) {
			if (this.getElement(i) != null) {
				values += this.getElement(i) + " ";
			}
		}
		return values;
	}

	public E getKey() {
		return (E) ("" + elements);
	}
}