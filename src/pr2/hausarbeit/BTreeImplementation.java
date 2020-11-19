package pr2.hausarbeit;

import java.io.*;
import java.util.*;
//import graphvisualizer.TreeVisualizer;

public class BTreeImplementation<E extends Comparable<E>> implements BTree<E>, Iterable<E> {

	// Variablen deklarieren
	private BTreeNode<E> root;
	private int minElements; // Entspricht der Ordnung
	private int maxElements; // Entspricht der Ordnung * 2
	private int maxChildNodes; // Entspricht der Ordnung * 2 + 1

	/**
	 * Konstruktor der Klasse BTreeImplementation.
	 * 
	 * @param degree Gibt die festgelegte Ordnung des gesamten B-Baums an. Diese
	 *               gilt für den gesamten B-Baum.
	 */
	public BTreeImplementation(int degree) {
		this.root = null; // Wurzel
		this.minElements = degree; // Mindestanzahl vorhandener Elemente im Knoten/Baum
		this.maxElements = 2 * degree; // Maximalanzahl vorhandener Elemente im Knoten/Baum
		this.maxChildNodes = (2 * degree) + 1; // Maximalanzahl vorhandener Kind-Knoten im Knoten/Baum
	}

	@Override
	public boolean insert(E key) {
		if (contains(key)) { // Ist Key bereits im Baum?
			return false;
		} else { // Key ist nicht im Baum
			if (this.root == null) { // Keine Wurzel vorhanden (Leerer Baum)
				this.root = new BTreeNode<E>(null, this.maxElements, this.maxChildNodes); // Neue Wurzel anlegen
				this.root.addElement(key); // Key einfügen
				return true;
			} else { // Wurzel ist vorhanden
				insert(this.root, key); // Key in den vorhandenen Baum einfügen
				return true;
			}
		}
	}

	// Hilfsmethode für insert()
	private void insert(BTreeNode<E> node, E key) {
		// Knoten hat keine Kinder
		if (node.getChildLevel() == 0) {
			// Füge Element in den Knoten ein
			node.addElement(key);
			// Überprüfe, ob der Knoten dadurch platzt
			if (node.getElementLevel() <= this.maxElements) {
				return;
			} else { // Knoten platzt - Split des Knoten notwendig
				split(node); // Splitte Knoten
				return;
			}
		} else { // Knoten hat weitere Kinder
			// Suche nach dem passenden Kind-Knoten
			if (key.compareTo(node.getElement(0)) <= 0) { // Vergleiche Key mit dem kleinsten Element im Knoten
				// Wähle passenden Kind-Knoten aus und füge ihn ein
				insert(node.getChildNode(0), key);
				return;
				// Key ist größer als das maximalste Element im aktuellen Knoten
			} else if (key.compareTo(node.getElement(node.getElementLevel() - 1)) > 0) {
				// Wähle passenden Kind-Knoten aus und füge ihn ein
				insert(node.getChildNode(node.getElementLevel()), key);
				return;
			}
			// Suche nächst-passenden Knoten und beginne von neu (Rekursion)
			for (int i = 1; i < node.getElementLevel(); i++) {
				if (key.compareTo(node.getElement(i - 1)) > 0 && key.compareTo(node.getElement(i)) <= 0) {
					insert(node.getChildNode(i), key);
					return;
				}
			}
		}
	}

	// Hilfsmethode für insert()
	private void split(BTreeNode<E> nodeToSplit) {
		// Variablen deklarieren
		int midElementPosition = nodeToSplit.getElementLevel() / 2;
		// Neue Knoten anlegen
		BTreeNode<E> newLeftNode = new BTreeNode<E>(null, this.maxElements, this.maxChildNodes);
		BTreeNode<E> newRightNode = new BTreeNode<E>(null, this.maxElements, this.maxChildNodes);

		// Werte in den neuen linken Knoten kopieren
		for (int i = 0; i < midElementPosition; i++) {
			newLeftNode.addElement(nodeToSplit.getElement(i));
		}
		// Falls der übergebene Knoten Kinder hat - Anhängen an den rechten Knoten
		if (nodeToSplit.getChildLevel() > 0) {
			for (int j = 0; j <= midElementPosition; j++) {
				newLeftNode.addChildNode(nodeToSplit.getChildNode(j));
			}
		}

		// Werte in den neuen rechten Knoten kopieren
		for (int i = midElementPosition + 1; i < nodeToSplit.getElementLevel(); i++) {
			newRightNode.addElement(nodeToSplit.getElement(i));
		}
		// Falls der übergebene Knoten Kinder hat - Anhängen an den rechten Knoten
		if (nodeToSplit.getChildLevel() > 0) {
			for (int j = midElementPosition + 1; j < nodeToSplit.getChildLevel(); j++) {
				newRightNode.addChildNode(nodeToSplit.getChildNode(j));
			}
		}

		// Eltern-Knoten ist bereits vorhanden
		if (nodeToSplit.getParent() != null) {
			// Variablen deklarieren
			BTreeNode<E> parent = nodeToSplit.getParent();
			// Füge das mittlere Element dem Eltern-Knoten hinzu
			parent.addElement(nodeToSplit.getElement(midElementPosition));
			// Lösche den übergebenen Knoten
			parent.removeChildNode(nodeToSplit);
			// Füge die neuen Knoten an den Eltern-Knoten an
			parent.addChildNode(newLeftNode);
			parent.addChildNode(newRightNode);
			// Aktuelle Anzahl der Elemente übersteigt die maximale Anzahl der im Knoten
			if (parent.getElementLevel() > this.maxElements) {
				// Splitte den Eltern-Knoten
				split(parent);
			}
		} else { // Neuer Eltern-Knoten erstellen
			// Variablen deklarieren
			BTreeNode<E> newRoot = new BTreeNode<E>(null, this.maxElements, this.maxChildNodes);
			// Füge das mittlere Element dem Eltern-Knoten hinzu (Neues Wurzel-Element)
			newRoot.addElement(nodeToSplit.getElement(midElementPosition));
			nodeToSplit.setParent(newRoot);
			// Neue Wurzel setzen
			this.root = newRoot;
			// An die neue Wurzel die erstellten Linken- & Rechten-Kind-Knoten setzen
			this.root.addChildNode(newLeftNode);
			this.root.addChildNode(newRightNode);
		}
	}

	/**
	 * @param filename Datei und ihr Pfad werden übergeben
	 * @param elemType Gibt an welcher Typ eingelesen werden soll
	 * @return Gibt zurück ob Wert eingefügt wurde
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean insert(String filename, String elemType) {
		boolean inserted = false;

		if (elemType.equals("IntElement")) {
			E[] elArray = (E[]) readIntElementsFromFile(filename);

			for (int i = 0; i < elArray.length; i++) {
				if (this.insert(elArray[i]) && !inserted) {
					inserted = true;
				}
			}
			return inserted;
		} else if (elemType.equals("StringElement")) {
			E[] stringArray = (E[]) readStringElementsFromFile(filename);

			for (int i = 0; i < stringArray.length; i++) {
				if (this.insert(stringArray[i]) && !inserted) {
					inserted = true;
				}
			}
			return inserted;
		} else if (elemType.equals("Song")) {
			E[] songArray = (E[]) fillArrayWithSongsFromFile(filename);

			// Song besteht aus title, artists und album. Ein Split ist deshalb notwendig.
			for (int i = 0; i < songArray.length; i++) {
				if (this.insert(songArray[i]) && !inserted) {
					inserted = true;
				}
			}
			return inserted;
		} else {
			return false;
		}

	}

	@Override
	public boolean contains(E key) {
		return contains(this.root, key);
	}

	// Hilfsmethode für contains()
	private boolean contains(BTreeNode<E> node, E key) {
		if (node == null) { // Leerer Knoten kann nichts enthalten
			return false;
		} else if (node.search(key)) { // Durchsuche aktuellen Knoten
			return true;
		}
		// Variable deklarieren
		int position = 0;
		// Suche nach bestem Kind-Knoten
		while (position < node.getElementLevel()) {
			// Key ist kleiner als die aktuelle Element-Position
			if (key.compareTo((E) node.getElement(position)) < 0) {
				// Rufe contains() erneut mit dem geeigneten Kind-Knoten auf
				return contains(node.getChildNode(position), key);
			}
			position++;
		}
		// Suche Key im letzten möglichen Kind-Knoten
		return contains(node.getChildNode(position), key);
	}

	@Override
	public boolean remove(E key) {
		if (!contains(key)) { // Der zu löschende Key ist nicht im B-Tree
			return false;
		} else {
			remove(this.root, key);
			return true;
		}
	}

	// Hilfsmethode für remove
	private void remove(BTreeNode<E> node, E key) {
		return;
	}

	@Override
	public int size() {
		return size(this.root, 0);
	}

	// Hilfsmethode für size()
	private int size(BTreeNode<E> node, int size) {
		if (node == null) {
			return 0;
		}
		// Aktuelle Anzahl der Knoten-Elemente werden gespeichert
		size += node.getElementLevel();
		// Hat aktueller Knoten weitere Kind-Knoten
		if (node.getChildLevel() > 0) {
			// Versuche alle Kind-Knoten zu durchlaufen
			for (int i = 0; i < node.getChildLevel(); i++) {
				size = size(node.getChildNode(i), size);
			}
		}
		return size;
	}

	@Override
	public int height() {
		return height(this.root, 1);
	}

	// Hilfsmethode für height()
	private int height(BTreeNode<E> node, int counter) {
		// Übergebener Knoten ist leer
		if (node == null) {
			return 0;
		}
		// Knoten hat weitere Kind-Knoten
		if (node.getChildLevel() > 0) {
			counter++;
			// Erneuter Aufruf von height() mit dem kleinsten Kind-Knoten
			return height(node.getChildNode(0), counter);
		}
		// Knoten hat keine weiteren Kind-Knoten - Gebe Höhe des Baums zurück
		return counter;
	}

	@Override
	public E getMin() {
		// Wurzel ist nicht vorhanden
		if (this.root == null) {
			return null;
		}

		// Variablen deklarieren
		BTreeNode<E> currentNode = this.root;
		E minElement = null;

		// Durchläuft den Baum am linken Rand
		while (currentNode != null) {
			// Speichere das minimale Element im aktuellen Knoten
			minElement = currentNode.getElement(0);
			// Gehe zum nächsten linkesten Kind-Knoten
			currentNode = currentNode.getChildNode(0);
		}
		// Minimalen Wert zurückgeben
		return minElement;
	}

	@Override
	public E getMax() {
		// Wurzel ist nicht vorhanden
		if (this.root == null) {
			return null;
		}

		// Variablen deklarieren
		BTreeNode<E> currentNode = this.root;
		E maxElement = null;

		// Durchläuft den Baum am rechten Rand
		while (currentNode != null) { // && currentNode.getElementLevel() > 0
			// Speichere das maximale Element im aktuellen Knoten
			maxElement = currentNode.getElement(currentNode.getElementLevel() - 1);
			// Gehe zum nächsten rechtesten Kind-Knoten
			if (currentNode.getChildLevel() > 1) { // Aktueller Knoten hat weitere Kind-Knoten
				currentNode = currentNode.getChildNode(currentNode.getChildLevel() - 1);
			} else {
				return maxElement; // Maximalen Wert zurückgeben
			}
		}
		// Maximalen Wert zurückgeben
		return maxElement;
	}

	@Override
	public boolean isEmpty() {
		// Wurzel leer?
		return this.root == null ? true : false;
	}

	@Override
	public void clear() {
		// Wurzel löschen
		this.root = null;
	}

	@Override
	public void addAll(BTree<E> otherTree) {
		// TODO Implementierung
	}

	@Override
	public void printInOrder() {
		printInOrder(this.root);
	}

	// Hilfsmethode für printInOrder()
	private void printInOrder(BTreeNode<E> node) {
		// Übergebener Knoten ist nicht vorhanden
		if (node == null) {
			return;
		}
		// Knoten hat weitere Kind-Knoten
		if (node.getChildLevel() > 0) {
			// Gehe alle Kind-Knoten von links nach rechts durch
			for (int i = 0; i < node.getChildLevel(); i++) {
				// Rekursiver Aufruf printInOrder()
				printInOrder(node.getChildNode(i));
				// Zum Schutz von ArrayIndexOutOfBounds
				if (i < node.getElementLevel()) {
					// "Eltern-Knoten" ist an der aktuellen Stelle nicht leer, gebe Element aus
					if (node.getElement(i) != null) {
						System.out.print(node.getElement(i) + " ");
					}
				}
			}
		} else { // Knoten hat KEINE weiteren Kind-Knoten
			System.out.print(node.toString()); // Gebe Knoten aus
		}
		return;
	}

	@Override
	public void printPreOrder() {
		printPreOrder(this.root);
	}

	// Hilfsmethode für printPreOrder()
	private void printPreOrder(BTreeNode<E> node) {
		// Übergebener Knoten ist nicht vorhanden
		if (node == null) {
			return;
		}
		// Gebe aktuellen Knoten aus
		System.out.print(node.toString());
		// Knoten hat weitere Kind-Knoten
		if (node.getChildLevel() > 0) {
			// Versuche alle Kind-Knoten zu durchlaufen
			for (int i = 0; i < node.getChildLevel(); i++) {
				printPreOrder(node.getChildNode(i));
			}
		}
		return;
	}

	@Override
	public void printPostOrder() {
		printPostOrder(this.root);
	}

	// Hilfsmethode für printPostOrder, um Rekursion zu ermöglichen
	private void printPostOrder(BTreeNode<E> node) {
		// Übergebener Knoten ist nicht vorhanden
		if (node == null) {
			return;
		}
		// Knoten hat weitere Kind-Knoten
		if (node.getChildLevel() > 0) {
			// Versuche alle Kind-Knoten zu durchlaufen
			for (int i = 0; i < node.getChildLevel(); i++) {
				// Rekursiver Aufruf printPostOrder()
				printPostOrder(node.getChildNode(i));
			}
		}
		// Gebe aktuellen Knoten aus
		System.out.print(node.toString());
		return;
	}

	@Override
	public void printLevelOrder() {
		// Baum durchlaufen
		for (int i = 0; i <= this.height(); i++) {
			printLevelOrder(this.root, i);
		}
	}

	// Hilfsmethode für printLevelOrder()
	private void printLevelOrder(BTreeNode<E> node, int level) {
		// Übergebener Knoten ist nicht vorhanden
		if (node == null) {
			return;
		}

		if (level == 1) { // Werte Ausgeben
			System.out.print(node.toString());
		} else if (level > 1) {
			for (int i = 0; i < node.getChildLevel(); i++) {
				printLevelOrder(node.getChildNode(i), level - 1);
			}
		}
		return;
	}

	/**
	 * Speichert den B-Baum in einer Datei
	 * @param Datei in die gespeichert werden soll wird übergeben
	 */
	@Override
	public void saveToFile(String filename) {
		try {
			StringBuilder data = new StringBuilder();
			String thedata = serialize(root, data);
			FileWriter writer;
			writer = new FileWriter(filename);
			writer.write(thedata);
			writer.close();
			System.out.println("\nEinlesen Erfolgreich");
		} catch (IOException e) {
			System.out.println("Fehler");
			e.printStackTrace();
		}
	}

	/**
	 * Fügt die Werte des Baums in PreOrder in StringBuilder ein
	 * @param node Knoten wird übergeben
	 * @param data Elemente des Baums werden in StringBuilder eingefügt
	 * @return Gibt eingefügte Werte als String zurück
	 */
	public String serialize(BTreeNode<E> node, StringBuilder data) {
		if (node == null) {
			data.append(" ").append(" ");
		} else {
			data.append(node).append(" ");			
			if (node.getChildLevel() > 0) {
				for (int i = 0; i < node.getChildLevel(); i++) {
					serialize(node.getChildNode(i), data);
				}
			}
		}
		return data.toString();

	}

	@Override
	public BTree<E> readFromFile(String filename) {
		return null;
	}
	

	@Override
	public BTree<E> clone() {
		// Neuen Baum anlegen
		BTreeImplementation<E> newBTree = new BTreeImplementation<E>(this.minElements);
		// Kopierten Baum zurückgeben
		return clone(this.root, newBTree);
	}

	// Hilfsmethode für clone() - Clone nach PreOrder
	private BTree<E> clone(BTreeNode<E> originalRoot, BTree<E> treeToClone) {
		// Übergebener Knoten ist nicht vorhanden
		if (originalRoot == null) {
			return null;
		}

		// Werte einfügen
		for (int i = 0; i < originalRoot.getElementLevel(); i++) {
			treeToClone.insert(originalRoot.getElement(i));
		}

		// Knoten hat weitere Kind-Knoten
		if (originalRoot.getChildLevel() > 0) {
			// Versuche alle Kind-Knoten zu durchlaufen
			for (int i = 0; i < originalRoot.getChildLevel(); i++) {
				// Rekursiver Aufruf clone()
				clone(originalRoot.getChildNode(i), treeToClone);
			}
		}
		// Geklonten Baum zurückgeben
		return treeToClone;
	}

	@Override
	public Iterator<E> iterator() {
		// Übergibt den B-Baum dem BTreeIterator
		return new BTreeIterator<E>(this);
	}

//	/**
//	 * Visualisiert den aktuellen Baum.
//	 */
//	public void visualize() {
//		TreeVisualizer tv = new TreeVisualizer();
//		tv.draw(this.root);
//	}

	@SuppressWarnings("rawtypes")
	public static Comparable[] readIntElementsFromFile(String filename) {
		try {
			File file = new File(filename);
			Scanner in = new Scanner(file);
			int size = 0;
			while (in.hasNextInt()) {
				size++;
				in.nextInt();
			}
			in.close();
			int[] data = new int[size];
			Comparable[] comdata = new Comparable[size];

			in = new Scanner(file);

			for (int i = 0; i < data.length; i++) {
				data[i] = in.nextInt();
				comdata[i] = new IntElement(data[i]);
			}

			in.close();
			return comdata;
		} catch (Exception e) {
			return null;
		}
	}

	@SuppressWarnings("rawtypes")
	public static Comparable[] readStringElementsFromFile(String filename) {
		try {
			File file = new File(filename);
			Scanner in = new Scanner(file);
			int size = 0;
			while (in.hasNextLine()) {
				size++;
				in.next();
			}
			in.close();
			String[] data = new String[size];
			Comparable[] comdata = new Comparable[size];

			in = new Scanner(file);

			for (int i = 0; i < data.length; i++) {
				data[i] = in.next();
				comdata[i] = new StringElement(data[i]);
			}

			in.close();
			return comdata;
		} catch (Exception e) {
			return null;
		}
	}

	public static String[] readStringArray(String filename){
		try {
			File file = new File(filename);
			Scanner in = new Scanner(file);
			int size = 0;
			while(in.hasNextLine()) {
				size++;
				in.nextLine();
			}
			in.close();
			String[] data = new String[size];
			
			in = new Scanner(file);
			
			for(int i = 0; i < data.length; i++) {
				data[i] = in.nextLine();
			}
			in.close();
			return data;	
		}
		catch(Exception e) {
			return null;
		}
	}
	
	public static Comparable[] fillArrayWithSongsFromFile(String filename) {

		String[] songArray = readStringArray(filename);
		Comparable[] allSongs = new Comparable[songArray.length];
		String title, album;
		String[] artists;
		// Song besteht aus title, artists und album. Ein Split ist deshalb notwendig.
		for (int i = 0; i < songArray.length; i++) {
			String[] song = songArray[i].split(";");
			title = song[0];
			album = song[1];
			artists = new String[song.length - 2];
			for (int j = 0; j < artists.length; j++) {
				artists[j] = song[j + 2];
			}
			allSongs[i] = new SongImplementation(title, artists, album);
		}
		return allSongs;
	}

}