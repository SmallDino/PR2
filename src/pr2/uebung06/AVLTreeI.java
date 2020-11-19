package pr2.uebung06;

//import graphvisualizer.TreeVisualizer;

import pr2.uebung04.IntElement;
import pr2.uebung04.StringElement;
import pr2.uebung05.SongImplementation;

public class AVLTreeI {

	private AVLTreeNode root;

	/**
	 * Fügt neue Comparable Objekte dem AVL-Baum hinzu.
	 * 
	 * @param elem Übergebenes Comparable Objekt, welches im AVL-Baum als Knoten
	 *             gespeichert wird.
	 */
	public void insert(Comparable key) {
		// AVL-Baum von unten nach oben aufbauen
		this.root = insert(this.root, key);
		// Balance-Werte nach dem Einfügen neu berechnen
		this.setTreeBalanceAfterInsert(this.root);
	}

	// Hilfsmethode für insert, um Rekursion zu ermöglichen
	private AVLTreeNode insert(AVLTreeNode node, Comparable key) {
		AVLTreeNode nodeToInsert;
		// Neues Blatt wird angelegt, wenn es null (leer) ist - Durch rekursion wird
		// diese Methode bei jedem insert aufgerufen
		if (node == null) {
			node = new AVLTreeNode(key);
		}

		// Abfrage, ob das übergebene Element kleiner oder größer ist, als der aktuelle
		// Knoten. Entspricht das Element dem Knoten, so wird der Vergleich
		// übersprungen.
		if (key.compareTo(node.getValue()) < 0) {
			nodeToInsert = insert(node.getLeft(), key);
			node.setLeft(nodeToInsert);
		} else if (key.compareTo(node.getValue()) > 0) {
			nodeToInsert = insert(node.getRight(), key);
			node.setRight(nodeToInsert);
		}

		// Balance des Knotens setzen
		node.setBalance(getNodeBalance(node));

		// Überprüfung, ob der aktuelle Knoten in Balance ist (In Balance = -1, 0, 1)
		if (node.getBalance() > 1 && key.compareTo(node.getLeft().getValue()) < 0) { // Rechts-Rotation
			/*
			 * Balance ist größer als 1, somit ist der Knoten links-belastet. Zusätzlicher
			 * Vergleich, ob der einzufügende Schlüssel kleiner ist als der linke Wert des
			 * Knotens. Rechts-Rotation des aktuellen Knotens erforderlich.
			 */
			return rotateRight(node);
		} else if (node.getBalance() > 1 && key.compareTo(node.getLeft().getValue()) > 0) { // Links-Rechts-Rotation
			/*
			 * Balance ist größer als 1, somit ist der Knoten links-belastet. Zusätzlicher
			 * Vergleich, ob der einzufügende Schlüssel größer ist als der linke Wert des
			 * Knotens. Links-Rechts-Rotation des aktuellen Knotens erforderlich.
			 */
			node.setLeft(rotateLeft(node.getLeft()));
			return rotateRight(node);
		} else if (node.getBalance() < -1 && key.compareTo(node.getRight().getValue()) > 0) { // Links-Rotation
			/*
			 * Balance ist kleiner als -1, somit ist der Knoten rechts-belastet.
			 * Zusätzlicher Vergleich, ob der einzufügende Schlüssel größer ist als der
			 * rechte Wert des Knotens. Links-Rotation des aktuellen Knotens erforderlich.
			 */
			return rotateLeft(node);
		} else if (node.getBalance() < -1 && key.compareTo(node.getRight().getValue()) < 0) { // Rechts-Links-Rotation
			/*
			 * Balance ist kleiner als -1, somit ist der Knoten rechts-belastet.
			 * Zusätzlicher Vergleich, ob der einzufügende Schlüssel kleiner ist als der
			 * rechte Wert des Knotens. Rechts-Links-Rotation des aktuellen Knotens
			 * erforderlich.
			 */
			node.setRight(rotateRight(node.getRight()));
			return rotateLeft(node);
		}
		return node;
	}

	/**
	 * Ermittelt das Maximum zweier übergebener Comparable Objekte.
	 * 
	 * @param a Comparable Objekt a.
	 * @param b Comparable Objekt b.
	 * @return Gibt das größere Comparable Objekt zurück.
	 */
	private Comparable getMax(Comparable a, Comparable b) {
		return (a.compareTo(b) > 0) ? a : b;
	}

	/**
	 * Ermittelt die Balance eines Knotens im AVL-Baum.
	 * 
	 * @param node Übergebener Knoten, bei der die Balance ermittelt werden soll.
	 * @return Gibt die Balance des Knotens als Integer zurück.
	 */
	private int getNodeBalance(AVLTreeNode node) {
		if (node == null) {
			return 0;
		}
		return this.height(node.getLeft()) - this.height(node.getRight());
	}

	/**
	 * Geht den kompletten AVL-Baum durch. Ermittelt und setzt die Balance aller
	 * Knoten neu.
	 * 
	 * @param node Knoten bei der die Balance ermittelt und gesetzt wird.
	 */
	private void setTreeBalanceAfterInsert(AVLTreeNode node) {
		if (node != null) {
			setTreeBalanceAfterInsert(node.getLeft());
			node.setBalance(getNodeBalance(node));
			setTreeBalanceAfterInsert(node.getRight());
			node.setBalance(getNodeBalance(node));
		}
	}

	/**
	 * Fügt neue Comparable Objekte dem AVL-Baum hinzu, indem es die Comparable
	 * Objekte aus einer Datei einliest.
	 * 
	 * @param filename   Dateipfad der Datei.
	 * @param elementTyp Gibt an, welche DateiTypen die übergebene Datei enthält.
	 */
	public void insert(String filename, String elementTyp) {
		if (elementTyp.equals("IntElement")) {
			int[] intArray = readIntegerArray(filename);
			IntElement[] elArray = new IntElement[intArray.length];

			for (int i = 0; i < elArray.length; i++) {
				elArray[i] = new IntElement(intArray[i]);
			}

			for (int i = 0; i < elArray.length; i++) {
				insert(elArray[i]);
			}
		} else if (elementTyp.equals("String")) {
			String[] stringArray = readStringArray(filename);
			StringElement[] elArray = new StringElement[stringArray.length];

			for (int i = 0; i < elArray.length; i++) {
				elArray[i] = new StringElement(stringArray[i]);
			}

			for (int i = 0; i < elArray.length; i++) {
				insert(elArray[i]);
			}
		} else if (elementTyp.equals("Song")) {
			String[] songArray = readStringArray(filename);
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
				insert(new SongImplementation(title, artists, album));
			}
		}
	}

	/**
	 * Löscht das übergebene Comparable Objekt aus dem AVL-Baum.
	 * 
	 * @param elem Comparable Objekt, welches gelöscht werden soll.
	 */
	public void remove(Comparable key) {
		this.root = remove(this.root, key);

		setTreeBalanceAfterInsert(this.root);
	}

	// Hilfsmethode für remove, um Rekursion zu ermöglichen
	private AVLTreeNode remove(AVLTreeNode node, Comparable key) {
		AVLTreeNode nodeToDelete;

		// Leere Node (null) wird abgefangen
		if (node == null) {
			return node;
		}

		// Abfrage, ob das übergebene Element kleiner oder größer ist, als der aktuelle
		// Knoten. Entspricht das Element dem Knoten, so wird der Vergleich
		// übersprungen.
		if (key.compareTo(node.getValue()) < 0) {
			nodeToDelete = remove(node.getLeft(), key);
			node.setLeft(nodeToDelete);
		} else if (key.compareTo(node.getValue()) > 0) {
			nodeToDelete = remove(node.getRight(), key);
			node.setRight(nodeToDelete);
		} else {
			// >>> Treffer - Der übergebene Key entspricht der Node
			// Abfrage ob der aktuelle Knoten mindestens einen (links oder rechts) leeren
			// Kind-Knoten hat
			if ((node.getLeft() == null) || (node.getRight() == null)) {
				AVLTreeNode tempNode = null;
				// Linker Kind-Knoten ist leer. Weise dem Übergangs-Knoten den Wert des rechten
				// Kind-Knoten zu.
				if (tempNode == node.getLeft()) {
					tempNode = node.getRight();
				} else {
					// Rechter Kind-Knoten ist leer. Weise dem Übergangs-Knoten den Wert des linken
					// Kind-Knoten zu.
					tempNode = node.getLeft();
				}
				// Zugewiesener Inhalt des Kind-Knoten kann leer sein -> Aktueller Knoten hat
				// keine Kind-Knoten.
				if (tempNode == null) {
					// Übergangs-Knoten dem aktuellen Knoten zuweisen und aktuellen Knoten löschen
					// (null setzen)
					tempNode = node;
					node = null;
				} else {
					// Zugewiesener Inhalt des Kind-Knoten war nicht leer (nicht null). Aktuellen
					// Knoten mit dem Inhalt des Übergangs-Knoten überschreiben. Dadurch wird der
					// aktuelle Knoten gelöscht, ohne eine Lücke im Baum zu hinterlassen.
					node = tempNode;
				}
			} else {
				// >>> Aktueller Knoten hat 2 Kind-Knoten (linkes und rechtes Kind)
				// Übergangs-Knoten den kleinsten Knoten (kleinster Wert) im rechten Teilbaum
				// des aktuellen Knotens zuweisen.
				AVLTreeNode tempNode = getMinNode(node.getRight());
				// Aktueller Knoten mit dem Wert des Übergangs-Knoten überschreiben
				node.setValue(tempNode.getValue());
				// Neuer zu löschender Knoten ermitteln, wobei der Knoten rechts vom aktuellen
				// (vorher überschriebenem) Knoten ermittelt wird. Als Key dient der aktuelle
				// Wert des aktuellen (überschriebenem) Knoten.
				nodeToDelete = remove(node.getRight(), node.getValue());
				// Den ermittelten Knoten an die rechte Stelle des aktuellen Knoten setzen
				node.setRight(nodeToDelete);
			}
		}

		// Leere Node (null) wird nach dem Vergleich abgefangen. Notwendig, da rekursive
		// Methode!
		if (node == null) {
			return node;
		}

		// Balance des Knotens setzen
		node.setBalance(getNodeBalance(node));

		// Überprüfung, ob der aktuelle Knoten und seine Kind-Knoten in Balance sind (In Balance = -1, 0, 1)
		if (node.getBalance() > 1 && getNodeBalance(node.getLeft()) >= 0) {
			/*
			 * Balance ist größer als 1, somit ist der Knoten links-belastet.
			 * Rechts-Rotation des aktuellen Knotens erforderlich.
			 */
			return rotateRight(node);
		} else if (node.getBalance() > 1 && getNodeBalance(node.getLeft()) < 0) {
			/*
			 * Balance ist größer als 1, somit ist der Knoten links-belastet.
			 * Links-Rechts-Rotation des aktuellen Knotens erforderlich.
			 */
			node.setLeft(rotateLeft(node.getLeft()));
			return rotateRight(node);
		} else if (node.getBalance() < -1 && getNodeBalance(node.getRight()) <= 0) {
			/*
			 * Balance ist kleiner als -1, somit ist der Knoten rechts-belastet.
			 * Links-Rotation des aktuellen Knotens erforderlich.
			 */
			return rotateLeft(node);
		} else if (node.getBalance() < -1 && getNodeBalance(node.getRight()) > 0) {
			/*
			 * Balance ist kleiner als -1, somit ist der Knoten rechts-belastet.
			 * Rechts-Links-Rotation des aktuellen Knotens erforderlich.
			 */
			node.setRight(rotateRight(node.getRight()));
			return rotateLeft(node);
		}

		return node;
	}

	/**
	 * Ermittelt das kleinste Element im angegebenen Zweig des AVL-Baums.
	 * 
	 * @param node Angegebener Knoten, ab welchem das kleinste Element gesucht
	 *             werden soll.
	 * @return Gibt den Knoten mit dem kleinsten Wert zurück.
	 */
	private AVLTreeNode getMinNode(AVLTreeNode node) {
		AVLTreeNode tempNode = node;

		while (tempNode.getLeft() != null) {
			tempNode = tempNode.getLeft();
		}

		return tempNode;
	}

	/**
	 * Überprüft den AVL-Baum ob das übergebene Element im Baum vorhanden ist.
	 * 
	 * @param val Element, welches im AVL-Baum gesucht werden soll.
	 * @return Gibt true zurück, wenn das Element im AVL-Baum gefunden wurde, sonst
	 *         false.
	 */
	public boolean contains(Comparable val) {
		// Variablen deklarieren
		AVLTreeNode node = this.root;

		// Abfrage ob überhaupt ein Baum vorhanden ist
		if (node == null) {
			return false;
		}
		// Knoten durchlaufen und vergleichen, ob das Element vorhanden ist
		while (node != null) {
			if (val.compareTo(node.getValue()) == 0) { // Knoten wurde gefunden
				return true;
			} else if (val.compareTo(node.getValue()) < 0) { // Wert ist kleiner als jetziger Knoten
				node = node.getLeft();
			} else { // Wert ist größer als jetziger Knoten
				node = node.getRight();
			}
		}
		return false;
	}

	/**
	 * Gibt den AVL-Baum in InOrder-Reihenfolge aus.
	 */
	public void printInorder() {
		printInorder(this.root);
	}

	// Hilfsmethode für printInorder, um Rekursion zu ermöglichen
	private void printInorder(AVLTreeNode node) {
		if (node == null) {
			return;
		}
		printInorder(node.getLeft());
		System.out.print(node.toString());
		printInorder(node.getRight());
	}

	/**
	 * Gibt den AVL-Baum in PreOrder-Reihenfolge aus.
	 */
	public void printPreorder() {
		printPreorder(this.root);
	}

	// Hilfsmethode für printPreorder, um Rekursion zu ermöglichen
	private void printPreorder(AVLTreeNode node) {
		if (node == null) {
			return;
		}
		System.out.print(node.toString());
		printPreorder(node.getLeft());
		printPreorder(node.getRight());
	}

	/**
	 * Gibt den AVL-Baum in PostOrder-Reihenfolge aus.
	 */
	public void printPostorder() {
		printPostorder(this.root);
	}

	// Hilfsmethode für printPostorder, um Rekursion zu ermöglichen
	private void printPostorder(AVLTreeNode node) {
		if (node == null) {
			return;
		}
		printPostorder(node.getLeft());
		printPostorder(node.getRight());
		System.out.print(node.toString());
	}

	/**
	 * Gibt den AVL-Baum in LevelOrder-Reihenfolge aus.
	 */
	private void printLevelorder() {
		// Realisierung erst mit der erweiterten Aufgabe
	}

	/**
	 * Ermittelt die Größe des AVL-Baums.
	 * 
	 * @return Gibt die Größe des AVL-Baums als Integer zurück.
	 */
	public int size() {
		return (size(this.root));
	}

	// Hilfsmethode für size, um Rekursion zu ermöglichen
	private int size(AVLTreeNode node) {
		if (node == null) {
			return 0;
		} else {
			return (size(node.getLeft()) + 1 + size(node.getRight()));
		}
	}

	/**
	 * Ermittelt die Höhe des AVL-Baums.
	 * 
	 * @return Gibt die Höhe des AVL-Baums als Integer zurück.
	 */
	public int height() {
		return height(this.root);
	}

	// Hilfsmethode für height, um Rekursion zu ermöglichen
	private int height(AVLTreeNode node) {
		if (node == null) {
			return 0;
		} else {
			int leftHeight = height(node.getLeft());
			int rightHeight = height(node.getRight());

			return leftHeight > rightHeight ? (leftHeight + 1) : (rightHeight + 1);
		}
	}

	/**
	 * Ermittelt das größte Element im AVL-Baum.
	 * 
	 * @return Gibt den Knoten mit dem größten Wert zurück.
	 */
	public Comparable getMax() {
		if (this.root == null) {
			return null;
		}

		// Variablen deklarieren
		Comparable maxElement = this.root.getValue();
		AVLTreeNode currentNode = this.root;

		// Durchläuft immer nur rechte Knoten, bis keiner mehr vorhanden ist.
		while (currentNode != null) {
			maxElement = currentNode.getValue();
			currentNode = currentNode.getRight();
		}

		return maxElement;
	}

	/**
	 * Ermittelt das kleinste Element im AVL-Baum.
	 * 
	 * @return Gibt den Knoten mit dem kleinsten Wert zurück.
	 */
	public Comparable getMin() {
		if (this.root == null) {
			return null;
		}

		// Variablen deklarieren
		Comparable minElement = this.root.getValue();
		AVLTreeNode currentNode = this.root;

		// Durchläuft immer nur linke Knoten, bis keiner mehr vorhanden ist.
		while (currentNode != null) {
			minElement = currentNode.getValue();
			currentNode = currentNode.getLeft();
		}

		return minElement;
	}

	/**
	 * Überprüft, ob der AVL-Baum leer ist.
	 * 
	 * @return Gibt true zurück, wenn der AVL-Baum leer ist, sonst false.
	 */
	public boolean isEmpty() {
		return this.root == null ? true : false;
	}

	/**
	 * Löscht den kompletten AVL-Baum.
	 */
	public void clear() {
		this.root = null;

	}

	/**
	 * Speichert den aktuellen AVL-Baum in ">> INSERT ORDER <<" in eine Datei.
	 * 
	 * @param filename Dateipfad, unter welcher die Datei abgespeichert werden soll.
	 */
	private void saveToFile(String filename) {
		// Realisierung erst mit der erweiterten Aufgabe
	}

	/**
	 * Rotiert inerhalb des AVL-Baums den übergebenen Knoten nach rechts.
	 * 
	 * @param node Übergebener Knoten, von dem aus nach rechts rotiert werden soll.
	 * @return Gibt den rotierten Knoten zurück.
	 */
	private AVLTreeNode rotateRight(AVLTreeNode node) {
		AVLTreeNode tempNode = node.getLeft();
		node.setLeft(node.getLeft().getRight());
		tempNode.setRight(node);
		return tempNode;
	}

	/**
	 * Rotiert inerhalb des AVL-Baums den übergebenen Knoten nach links.
	 * 
	 * @param node Übergebener Knoten, von dem aus nach rechts rotiert werden soll.
	 * @return Gibt den rotierten Knoten zurück.
	 */
	private AVLTreeNode rotateLeft(AVLTreeNode node) {
		AVLTreeNode tempNode = node.getRight();
		node.setRight(node.getRight().getLeft());
		tempNode.setLeft(node);
		return tempNode;
	}

//	/**
//	 * Visualisiert den aktuellen Baum.
//	 */
//	public void visualize() {
//		TreeVisualizer tv = new TreeVisualizer();
//		tv.draw(root);
//	}
}