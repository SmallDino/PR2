package pr2.hausarbeit;

import java.util.*;

public class BTreeIterator<E extends Comparable<E>> implements Iterator<E> {
	// Variablen deklarieren
	BTree<E> bTree;

	public BTreeIterator(BTree<E> bTree) {
		this.bTree = bTree;
	}

	@Override
	public boolean hasNext() {
		// TODO Implementierung
		return false;
	}

	@Override
	public E next() {
		// TODO Implementierung
		return null;
	}

	@Override
	public void remove() {
		// TODO Implementierung
	}
}