package pr2.uebung03;

public interface BinaryTree {

	/**
	 * Fügt ein Element im Binären-Baum ein.
	 * 
	 * @param val Wert, der dem Binären-Baum hinzugefügt werden soll.
	 * @return true wenn ein Element dem Binären-Baum hinzugefügt wurde, false wenn
	 *         nicht.
	 */
	public boolean insert(Element val);

	/**
	 * Liest eine Datei ein und fügt die Werte dem Binär-Baum hinzu.
	 * 
	 * @param filename Pfad der Datei.
	 * @return Gibt true zurück, wenn die Elemente dem Binären-Baum hinzugefügt
	 *         wurden, false wenn nicht.
	 */
	boolean insert(String filename);

	/**
	 * Speichert den Binär-Baum in Pre-Order in die übergebene Datei hinein.
	 * 
	 * @param filename Pfad der Datei.
	 * @return Gibt true zurück, wenn alle Elemente des Binär-Baums in der Datei
	 *         erfolgreich gespeichert wurden, sonst false.
	 */
	boolean saveToFile(String filename);

	/**
	 * Überprüft den Binären-Baum ob das übergebene Element im Baum vorhanden ist.
	 * 
	 * @param val Element, welches im Binären-Baum gesucht werden soll.
	 * @return Gibt true zurück, wenn das Element im Binären-Baum gefunden wurde,
	 *         sonst false.
	 */
	boolean contains(Element val);

	/**
	 * Ermittelt die Größe des Binär-Baums.
	 * 
	 * @return Gibt die Größe des Binär-Baums als Integer zurück.
	 */
	int size();

	/**
	 * Ermittelt die Höhe des Binär-Baums.
	 * 
	 * @return Gibt die Höhe des Binär-Baums als Integer zurück.
	 */
	int height();

	/**
	 * Ermittelt das größte Element im Binär-Baum
	 * 
	 * @return Gibt das Element mit dem größten Wert zurück.
	 */
	Element getMax();

	/**
	 * Ermittelt das kleinste Element im Binär-Baum.
	 * 
	 * @return Gibt das Element mit dem kleinsten Wert zurück.
	 */
	Element getMin();

	/**
	 * Löscht den übergebenen Knoten.
	 * 
	 * @param val Wert des übergebenen Knotens.
	 * @return Gibt true zurück, wenn der Knoten erfolgreich gelöscht ist, sonst
	 *         false.
	 */
	boolean remove(Element val);

	/**
	 * Überprüft den Binär-Baum ob dieser leer ist.
	 * 
	 * @return Gibt true zurück, wenn der Baum leer ist, ansonsten false.
	 */
	boolean isEmpty(); // checks if tree is empty

	/**
	 * Löscht den kompletten Binär-Baum.
	 */
	void clear(); // removes all elements from tree

	/**
	 * Fügt alle Elemente des übergebenen Baums dem aktuellen Baum hinzu.
	 * 
	 * @param otherTree Übergebener Binär-Baum, von dem die Elemente hinzugefügt
	 *                  werden sollen.
	 * @return Gibt den neuen Binär-Baum zurück, welchem die Werte hinzugefügt
	 *         wurden.
	 */
	BinaryTree addAll(BinaryTree otherTree);

	/**
	 * Gibt den Binär-Baum in Inorder-Reihenfolge aus.
	 */
	void printInorder();

	/**
	 * Gibt den Binär-Baum in PostOrder-Reihenfolge aus.
	 */
	void printPostorder();

	/**
	 * Gibt den Binär-Baum in PreOrder-Reihenfolge aus.
	 */
	void printPreorder();

	/**
	 * Gibt den Binär-Baum in LevelOrder-Reihenfolge aus.
	 */
	void printLevelorder();

	/**
	 * Exakte Hart-Kopie des aktuellen Baums.
	 * 
	 * @return Gibt eine exakte Kopie des aktuellen Baums zurück.
	 */
	BinaryTree clone();

	/**
	 * Überprüft ob der aktuelle Baum mit dem übergebenen Baum, inhaltlich sowie
	 * strukturell gleich sind.
	 * 
	 * @param other Übergebenes Objekt, welches auf Gleichheit überprüft werden
	 *              soll.
	 * @return Gibt true zurück, wenn beide Bäume inhaltlich sowie strukturell
	 *         gleich sind.
	 */
	boolean equals(Object other);

	/**
	 * Überprüft ob der aktuelle Baum mit dem übergebenen Baum inhaltlich gleich
	 * sind.
	 * 
	 * @param otherTree Übergebener Baum, welcher auf Gleichheit überprüft werden
	 *                  soll.
	 * @return Gibt true zurück, wenn beide Bäume inhaltlich gleich sind.
	 */
	boolean equal(BinaryTree otherTree);
}