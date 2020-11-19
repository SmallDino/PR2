package pr2.uebung04;

import java.util.*;

public class ShakerSort implements SortMethod {
	/**
	 * Sortieralgorithmus nach dem ShakerSort-Verfahren.
	 * 
	 * @param A übergebenes Array vom Typ Comparable, welches durchlaufen wird.
	 */
	public void sort(Comparable A[]) {
		// Variablen deklarieren
		boolean swapped, isBubbleSort = true;
		
		do {
			if (isBubbleSort) {
				// Array von unten nach oben (links nach rechts) durchsuchen, vergleichen und
				// vertauschen
				swapped = false;
				// Array durchlaufen
				for (int i = 0; i < A.length - 1; i++) {
					// Werte im Array vergleichen
					if ((A[i].compareTo(A[i + 1])) > 0) {
						// Werte im Array vertauschen
						swap(A, i, i + 1);
						swapped = true;
					}
				}
				isBubbleSort = false;
			} else {
				// Array von oben nach unten (rechts nach links) durchsuchen, vergleichen und
				// vertauschen
				swapped = false;
				// Array durchlaufen
				for (int i = A.length - 2; i >= 0; i--) {
					// Werte im Array vergleichen
					if ((A[i].compareTo(A[i + 1])) > 0) {
						// Werte im Array vertauschen
						swap(A, i, i + 1);
						swapped = true;
					}
				}
				isBubbleSort = true;
			}
		} while (swapped);
	}

	/**
	 * Sawp Methode zum vertauschen einzelner Werte in einem Array
	 * 
	 * @param array       übergebenes Array vom Typ Comparable, bei dem 2 Werte 2er
	 *                    Indizes vertauscht werden
	 * @param indexMarker markiert den aktuellen Index, welcher getauscht werden
	 *                    soll
	 * @param indexMax    markiert den gewünschten Index, welcher getauscht werden
	 *                    soll
	 */
	public static void swap(Comparable[] array, int indexMarker, int indexMax) {
		Comparable tmp = array[indexMarker];
		array[indexMarker] = array[indexMax];
		array[indexMax] = tmp;
	}
}