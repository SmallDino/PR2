package pr2.hausarbeit;

public interface BTree<E extends Comparable<E>> {// Iterable<BTreeNode> {extends Iterable {
	
	/**
	 * Fügt dem B-Baum ein neues Element hinzu.
	 * 
	 * @param key Element, welches in den B-Baum eingefügt werden soll.
	 * @return Gibt true zurück, wenn das Element eingefügt würde, sonst false.
	 */
	public boolean insert(E key);

	/**
	 * Fügt dem B-Baum neue Elemente über die angegebene Datei ein.
	 * 
	 * @param filename Pfad der Datei, aus der eingelesen werden soll.
	 * @param elemType Gibt an, welcher Datentyp sich in der Datei befindet.
	 * @return Gibt true zurück, wenn mindestens 1 Element von der Datei in den
	 *         B-Baum eingefügt wurde, sonst false.
	 */
	public boolean insert(String filename, String elemType);

	/**
	 * Ermittelt, ob der gesuchte Wert im B-Baum vorhanden ist.
	 * 
	 * @param key Übergebenes Element, welches im B-Baum gesucht werden soll.
	 * @return Gibt true zurück, wenn das Element im B-Baum gefunden wurde, sonst
	 *         false.
	 */
	public boolean contains(E key);

	/**
	 * Löscht das Element aus dem B-Baum.
	 * 
	 * @param key Übergebenes Element, welches im B-Baum gelöscht werden soll.
	 * @return Gibt true zurück, wenn das Element im B-Baum gelöscht wurde, sonst
	 *         false.
	 */
	public boolean remove(E key);

	/**
	 * Ermittelt die Anzahl der Elemente im B-Baum.
	 * 
	 * @return Gibt die Anzahl der Elemente im B-Baum als int zurück.
	 */
	public int size();

	/**
	 * Ermittelt die Höhe des B-Baums.
	 * 
	 * @return Gibt die Höhe des B-Baums als int zurück.
	 */
	public int height();

	/**
	 * Ermittelt das größte Element im B-Baum.
	 * 
	 * @return Gibt das größte Element im B-Baum zurück.
	 */
	public E getMax();

	/**
	 * Ermittelt das kleinste Element im B-Baum.
	 * 
	 * @return Gibt das kleinste Element im B-Baum zurück.
	 */
	public E getMin();

	/**
	 * Ermittelt, ob der Baum Elemente enthält, oder leer ist.
	 * 
	 * @return Gibt true zurück, wenn der B-Baum leer ist, sonst false.
	 */
	public boolean isEmpty();

	/**
	 * Löscht den B-Baum.
	 */
	public void clear();

	/**
	 * Fügt alle Elemente des übergebenen Baums ein. Duplikate werden dabei
	 * ignoriert.
	 * 
	 * @param otherTree B-Baum welcher in den aktuellen B-Baum eingefügt werden
	 *                  soll.
	 */
	public void addAll(BTree<E> otherTree);

	/**
	 * Gibt den B-Baum in InOrder-Reihenfolge auf der Konsole aus.
	 */
	public void printInOrder();

	/**
	 * Gibt den B-Baum in PostOrder-Reihenfolge auf der Konsole aus.
	 */
	public void printPostOrder();

	/**
	 * Gibt den B-Baum in PreOrder-Reihenfolge auf der Konsole aus.
	 */
	public void printPreOrder();

	/**
	 * Gibt den B-Baum in LevelOrder-Reihenfolge auf der Konsole aus.
	 */
	public void printLevelOrder();

	/**
	 * Speichert den B-Baum in einer Datei.
	 * 
	 * @param filename Pfad der Datei, in die geschrieben werden soll.
	 */
	public void saveToFile(String filename);

	/**
	 * Liest den B-Baum aus einer Datei
	 * 
	 * @param filename Pfad der Datei, aus der gelesen werden soll.
	 * @return Gibt den B-Baum zurück.
	 */
	public BTree<E> readFromFile(String filename);

	/**
	 * Erstellt eine tiefe Kopie des B-Baums.
	 * 
	 * @return Gibt die erstellte Kopie zurück.
	 */
	public BTree<E> clone();
}