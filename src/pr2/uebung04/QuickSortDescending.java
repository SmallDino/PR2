package pr2.uebung04;

import java.util.Scanner;

public class QuickSortDescending implements SortMethod {
	/**
	 * Sortieralgorithmus nach dem QuickSort-Verfahren (Variante 1).
	 * 
	 * @param A übergebenes Array vom Typ Comparable, welches durchlaufen wird.
	 */
	public void sort(Comparable A[]) {
		// Sortieraufruf
		sort(A, 0, A.length - 1);
		
		// Sortieren nach absteigender Reihenfolge
		for (int i = 0, j = A.length - 1; i < j; i++, j--) {
			swap(A, i, j);
		}
	}

	/**
	 * Sortieralgorithmus nach dem QuickSort-Verfahren (Variante 1).
	 * 
	 * @param A          übergebenes Array vom Typ Comparable, welches durchlaufen
	 *                   wird.
	 * @param lowerLimit Untere Grenze des Arrays.
	 * @param upperLimit Obere Grenze des Arrays.
	 */
	private static void sort(Comparable[] A, int lowerLimit, int upperLimit) {
		if (lowerLimit < upperLimit) {
			// Gibt den Index des neuen Pivotelemts an
			int index = partition(A, lowerLimit, upperLimit);
			// Rekursiver Aufruf der sort-Methode
			sort(A, lowerLimit, index);
			sort(A, index + 1, upperLimit);
		}
	}

	/**
	 * Ermittelt das mittlere Element im Array als Pivot. Das Pivotelement wird an
	 * seine korrekte Position getauscht und alle kleineren Elemente stehen davor
	 * bzw. alle größeren Elemente stehen hinter dem Pivot.
	 * 
	 * @param A          übergebenes Array vom Typ Comparable, welches durchlaufen
	 *                   wird.
	 * @param lowerLimit Untere Grenze des Arrays.
	 * @param upperLimit Obere Grenze des Arrays.
	 * @return gibt das am weitesten rechts stehende Element zurück.
	 */
	private static int partition(Comparable[] A, int lowerLimit, int upperLimit) {
		int pivotIndex = (lowerLimit + upperLimit) / 2;
		Comparable pivot = A[pivotIndex];

		while (lowerLimit <= upperLimit) {
			int left = lowerLimit, right = upperLimit;

			// Durchsucht von links ein Element das größer als das Pivot ist
			while (A[left].compareTo(pivot) < 0) {
				left++;
			}

			// Durchsucht von rechts ein Element das kleiner als das Pivot ist
			while (A[right].compareTo(pivot) > 0) {
				right--;
			}

			// Werte im Array vergleichen
			if (left < right) {
				// Werte im Array werden vertauscht
				swap(A, left, right);

				// Ober- und Untergrenze werden jeweils um 1 nach innen geschoben
				lowerLimit = left + 1;
				upperLimit = right - 1;
			} else {
				return right;
			}
		}
		return upperLimit;
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