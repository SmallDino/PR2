package pr2.uebung03;

//import graphvisualizer.*;

public class MyBinaryTree implements BinaryTree {
	// Variablen deklarieren
	private TreeNode root = null;
	private int counter = 0;

	public boolean insert(Element val) {
		// Abfrage ob Empty
		if (this.isEmpty()) {
			TreeNode tmp = new TreeNode(val);
			this.root = tmp;
			root.setElement(tmp.getElement());
		}
		// Variablen deklarieren
		TreeNode parent = null;
		TreeNode child = this.root;
		// Schleife
		while (child != null) {
			parent = child;
			// Werte der Elemente miteinander vergleichen - 0 = vorhanden, 1 = Wert ist
			// größer, -1 = Wert ist kleiner
			if (val.compareTo(child.getElement()) == 0) {
				return false;
			} else if (val.compareTo(child.getElement()) < 0) {
				child = child.getLeft();
			} else {
				child = child.getRight();
			}
		}
		// Neue Elemente dem Binären-Baum hinzufügen
		if (val.compareTo(parent.getElement()) < 0) {
			parent.setLeft(new TreeNode(val));
		} else {
			parent.setRight(new TreeNode(val));
		}
		return true;
	}

	// DOES ONLY WORK FOR INTEGER VALUES INSIDE TEXT-FILE
	public boolean insert(String filename) {
		// Variablen deklarieren
		int[] intArray = readIntegerArray(filename);
		Element[] elArray = new Element[intArray.length];
		boolean success = false;

		for (int i = 0; i < elArray.length; i++) {
			elArray[i] = new IntElement(intArray[i]);
		}

		for (int i = 0; i < elArray.length; i++) {
			success = insert(elArray[i]);
		}

		return success;
	} 

	public boolean saveToFile(String filename) {
		// Variablen deklarieren
		String[] elArray = new String[size()];
		// Array befüllen
		elArray = savePreorder(root, elArray);
		// Array abspeichern
		//saveStringArray(elArray, filename);
		return true;
	}

	private String[] savePreorder(TreeNode root, String[] elArray) {
		if (root != null) {
			elArray[counter] = root.getElement().toString();
			counter += 1;
			savePreorder(root.getLeft(), elArray);
			savePreorder(root.getRight(), elArray);
		}
		return elArray;
	}

	public boolean contains(Element val) {
		// Variablen deklarieren
		TreeNode node = this.root;

		// Abfrage ob überhaupt ein Baum vorhanden ist
		if (node == null) {
			return false;
		}
		// Knoten durchlaufen und vergleichen, ob das Element vorhanden ist
		while (node != null) {
			if (val.compareTo(node.getElement()) == 0) { // Knoten wurde gefunden
				return true;
			} else if (val.compareTo(node.getElement()) < 0) { // Wert ist kleiner als jetziger Knoten
				node = node.getLeft();
			} else { // Wert ist größer als jetziger Knoten
				node = node.getRight();
			}
		}
		return false;
	}

	public int size() {
		return (size(this.root));
	}

	// Hilfsmethode für size, um Rekursion zu ermöglichen
	private int size(TreeNode node) {
		if (node == null) {
			return 0;
		} else {
			return (size(node.getLeft()) + 1 + size(node.getRight()));
		}
	}

	public int height() {
		return height(this.root);
	}

	// Hilfsmethode für height, um Rekursion zu ermöglichen
	private int height(TreeNode node) {
		if (node == null) {
			return 0;
		} else {
			int leftHeight = height(node.getLeft());
			int rightHeight = height(node.getRight());

			return leftHeight > rightHeight ? (leftHeight + 1) : (rightHeight + 1);
		}
	}

	public Element getMax() {
		if (this.root == null) {
			return null;
		}

		// Variablen deklarieren
		Element maxElement = this.root.getElement();
		TreeNode currentNode = this.root;

		// Durchläuft immer nur rechte Knoten, bis keiner mehr vorhanden ist.
		while (currentNode != null) {
			maxElement = currentNode.getElement();
			currentNode = currentNode.getRight();
		}

		return maxElement;
	}

	public Element getMin() {
		if (this.root == null) {
			return null;
		}

		// Variablen deklarieren
		Element minElement = this.root.getElement();
		TreeNode currentNode = this.root;

		// Durchläuft immer nur linke Knoten, bis keiner mehr vorhanden ist.
		while (currentNode != null) {
			minElement = currentNode.getElement();
			currentNode = currentNode.getLeft();
		}

		return minElement;
	}

	public boolean remove(Element val) {
		// Variablen deklarieren
		TreeNode node = search(val);
		TreeNode root = this.root;
		TreeNode child = null;
		TreeNode parent = null;

		// Wenn null, dann sind keine Knoten im Baum vorhanden
		if (node == null) {
			return false;
		}
		// Wurzel löschen - Sonderfall
		if (node == root) {
			if((node.getLeft() == null) && (node.getRight() == null)) {
				this.root = null;
				return true;
			} else if (node.getLeft() == null) {			
				/*
				 * null, wenn kein linker Teilbaum ab der Wurzel existiert. Somit verschiebt
				 * sich die Wurzel auf den 1. Knoten des rechten Teilbaums.
				 */
				root = node.getRight();
			} else {
				/*
				 * null, wenn kein rechter Teilbaum ab der Wurzel existiert. Somit verschiebt
				 * sich die Wurzel auf den 1. Knoten des linken Teilbaums.
				 */
				if (node.getRight() == null) {
					root = node.getLeft();
				} else {
					// Knoten mit dem Maximum im linken Teilbaum ermitteln
					child = getMaxInLeftTree(node.getLeft());
					// Eltern-Knoten des Kindes ermitteln
					parent = getParentFromMaxNode(node.getLeft());
					// Löschvorgang des Knotens, da dieser jetzt die Wurzel bildet
					if (child.getLeft() != null) { // Setze den linken Teilbaum des Kinder-Knotens, als rechten Teilbaum
													// des Eltern-Knoten
						parent.setRight(child.getLeft());
					} else {
						parent.setRight(null); // Lösche den Kinder-Knoten (Maximum
					}
					// Wurzel setzen
					node.setElement(child.getElement());
					return true;
				}				
			}
		} else { // Normaler Knoten löschen
			// Elternknoten ermitteln
			parent = getParent(node.getElement());

			if (node.getLeft() == null) { // Beim gesuchten Knoten ist der linke Knoten null
				if (node == parent.getLeft()) { // Gesuchter Knoten ist linkes Kind von Eltern-Knoten
					parent.setLeft(node.getRight());
				} else if (node == parent.getRight()) { // Gesuchter Knoten ist rechtes Kind von Eltern-Knoten
					parent.setRight(node.getRight());
				}
				return true;
			} else if (node.getRight() == null) { // Beim gesuchten Knoten ist der rechte Knoten null
				if (node == parent.getLeft()) { // Gesuchter Knoten ist linkes Kind von Eltern-Knoten
					parent.setLeft(node.getLeft());
				} else if (node == parent.getRight()) { // Gesuchter Knoten ist rechtes Kind von Eltern-Knoten
					parent.setRight(node.getLeft());
				}
				return true;
			} else { // Beim gesuchten Knoten sind beide Knoten belegt
				// Größte Element im linken Teilbaum des zu löschenden Knotens wird ermittelt
				child = getMaxInLeftTree(node.getLeft());
				// Elternknoten des größten Element im linken Teilbaum wird ermittelt
				parent = getParent(child.getElement());
				// Ersetzte den zu löschenden Knoten mit dem größten Element im linken Teilbaum
				node.setElement(child.getElement());
				// Gibt es einen linken Teilbaum ab dem größten Element im linken Teilbaum
				if (child.getLeft() != null) {
					// Linker Teilbaum des zu ersetzten Knotens wird gesetzt
					node.setLeft(child.getLeft());
					// Ermittelt das kleinste Element im linken Teilbaum des Größten Elements im
					// linken Teilbaum
					while (child.getLeft() != null) {
						child = child.getLeft();
					}
					// Setze am kleinsten Element im linken Teilbaum des größten Elements den
					// Eltern-Knoten des größten Elements an
					child.setLeft(parent);
				}
				// Lösche den größten Knoten des linken Teilbaums, da dieser den neuen Knoten
				// bildet
				parent.setRight(null);

				return true;
			}
		}
		return false;
	}

	// Suchalgorithmus für die remove-Methode
	private TreeNode search(Element val) {
		// Variablen deklarieren
		TreeNode node = this.root;

		// Abfrage ob überhaupt ein Baum vorhanden ist
		if (node == null) {
			return node;
		}
		// Baum durchlaufen und vergleichen, ob das Element vorhanden ist
		while (node != null) {
			// Passender Knoten wurde gefunden
			if (val.compareTo(node.getElement()) == 0) {
				return node;
			} else if (val.compareTo(node.getElement()) < 0) { // Knoten ist kleiner als das übergebene Element
				node = node.getLeft();
			} else {
				node = node.getRight(); // Knoten ist größer als das übergebene Element
			}
		}
		// Gibt null zurück, da kein passender Knoten gefunden wurde
		return null;
	}

	// Suchalgorithmus für die remove-Methode
	private TreeNode getMaxInLeftTree(TreeNode node) {
		// Rechte Knoten durchlaufen, da diese immer das Maximum enthalten
		while (node != null) {
			// Abfrage ob es noch einen weiteren rechten Knoten gibt, wenn nicht, dann
			// Maximum gefunden
			if (node.getRight() == null) {
				// Knoten mit dem Maximum zurückgeben
				return node;
			} else if (node.getRight() != null) { // wenn != null, dann sind noch weitere rechte Knoten vorhanden
				// Gehe zum weiteren rechten Knoten
				node = node.getRight();
			}
		}
		return node;
	}

	private TreeNode getParentFromMaxNode(TreeNode node) {
		TreeNode parent = node;
		// Rechte Knoten durchlaufen, da diese immer das Maximum enthalten
		while (node != null) {
			// Abfrage ob es noch einen weiteren rechten Knoten gibt, wenn nicht, dann
			// Maximum gefunden
			if (node.getRight() == null) {
				return parent;
			} else if (node.getRight() != null) { // wenn != null, dann sind noch weitere rechte Knoten vorhanden
				parent = node;
				// Gehe zum weiteren rechten Knoten
				node = node.getRight();
			}
		}
		return parent;
	}

	private TreeNode getParent(Element val) {
		// Variablen deklarieren

		TreeNode node = this.root;
		TreeNode parent = node;

		// Abfrage ob überhaupt ein Baum vorhanden ist
		if (node == null) {
			return node;
		}
		// Baum durchlaufen und vergleichen, ob das Element vorhanden ist
		while (node != null) {
			// Passender Knoten wurde gefunden
			if (val.compareTo(node.getElement()) == 0) {
				return parent;
			} else if (val.compareTo(node.getElement()) < 0) { // Knoten ist kleiner als das übergebene Element
				parent = node;
				node = node.getLeft();
			} else {
				parent = node;
				node = node.getRight(); // Knoten ist größer als das übergebene Element
			}
		}
		// Gibt null zurück, da kein passender Knoten gefunden wurde
		return null;
	}

	public boolean isEmpty() {
		return this.root == null;
	}

	public void clear() {
		this.root = null;
	}

	public BinaryTree addAll(BinaryTree otherTree) {
		if ((otherTree == null) && (this.root == null)) {
			return this;
		}
		addAll(this.root, (MyBinaryTree) otherTree); // type cast kann man sicherer machen
		return this;
	}

	// Hilfsmethode für addAll
	private void addAll(TreeNode node, MyBinaryTree otherTree) {
		// b1 contains 3 with root val 3 , b2 contians nothing with root=null
		if (node != null) {
			otherTree.insert(node.getElement().clone());// element.clone -> returns new object of value Element not a
														// ref.
			addAll(node.getLeft(), otherTree);
			addAll(node.getRight(), otherTree);
		}
	}

	public void printInorder() {
		printInorder(this.root);
	}

	// Hilfsmethode für printInorder, um Rekursion zu ermöglichen
	private void printInorder(TreeNode node) {
		if (node == null) {
			return;
		}
		printInorder(node.getLeft());
		System.out.print(node.getElement());
		printInorder(node.getRight());
	}

	public void printPostorder() {
		printPostorder(this.root);
	}

	private void printPostorder(TreeNode node) {
		if (node == null) {
			return;
		}
		printPostorder(node.getLeft());
		printPostorder(node.getRight());
		System.out.print(node.getElement());
	}

	public void printPreorder() {
		printPreorder(this.root);
	}

	private void printPreorder(TreeNode node) {
		if (node == null) {
			return;
		}
		System.out.print(node.getElement());
		printPreorder(node.getLeft());
		printPreorder(node.getRight());
	}

	public void printLevelorder() {
		// Variablen deklarieren
		int height = height();
		// Binären Baum durchlaufen
		for (int i = 0; i <= height; i++) {
			printLevelorder(this.root, i);
		}

	}

	// Hilfsmethode für printLevelorder, um Rekursion zu ermöglichen
	private void printLevelorder(TreeNode node, int level) {
		// Abbruchbedingung
		if (node == null) {
			return;
		}
		// Aktuelle Elemente im Level ausgeben
		if (level == 1) {
			System.out.print(node.getElement());
		} else if (level > 1) {
			printLevelorder(node.getLeft(), level - 1);
			printLevelorder(node.getRight(), level - 1);
		}
	}

	@Override
	public BinaryTree clone() {
		// Clone-Baum anlegen
		MyBinaryTree newTree = new MyBinaryTree();
		// Clone-Baum mit vorhandenen Knoten befüllen
		newTree.cloneTree(this.root, newTree);
		return newTree;
	}

	private void cloneTree(TreeNode root, BinaryTree newTree) {
		// Variablen deklarieren
		TreeNode current;
		// Abbruchbedingung
		if (root == null) {
			return;
		}
		
		newTree.insert(root.getElement());
		
		if (root.getLeft() != null) {
			current = root.getLeft();
			newTree.insert(current.getElement());
			cloneTree(current, newTree);
		}

		if (root.getRight() != null) {
			current = root.getRight();
			newTree.insert(current.getElement());
			cloneTree(current, newTree);
		}
	}

	@Override
	public boolean equals(Object otherTree) {
		// Übergebenes Objekt zu Binär-Baum casten
		otherTree = (MyBinaryTree) otherTree;
		return equals(this.root, ((MyBinaryTree) otherTree).root);
	}

	// Hilfsmethode für equals, um Rekursion zu ermöglichen
	private boolean equals(TreeNode first, TreeNode second) {
		if (null == first && null == second) {
			return true;
		}
			
		if (null == first || null == second) {
			return false;
		}			

		return equals(first.getRight(), second.getRight()) && equals(first.getLeft(), second.getLeft());
	}

	public boolean equal(BinaryTree otherTree) {
		if (otherTree.isEmpty() && isEmpty()) {
			return true;
		} else if (isEmpty() && !otherTree.isEmpty()) {
			return false;
		} else if (size() != otherTree.size()) {
			return false;
		}
		return equal(this.root, otherTree);

	}

	// Hilfsmethode für equal, um Rekursion zu ermöglichen
	private boolean equal(TreeNode node, BinaryTree otherTree) {
		if (node != null) {
			if (!otherTree.contains(node.getElement())) {
				return false;
			}
			if (!equal(node.getLeft(), otherTree)) {
				return false;
			}
			if (!equal(node.getRight(), otherTree)) {
				return false;
			}
		}
		return true;
	}

//	public void visualize() {
//		TreeVisualizer tv = new TreeVisualizer();
//		tv.draw((VisualizableNode) this.root);
//	}
}