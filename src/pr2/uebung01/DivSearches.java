package pr2.uebung01;

public class DivSearches {
	// Konstanten
	public static final int NO_KEY = -1;

	// Aufgabe 1a
	/**
	 * Der gewünschte Key wird im übergebenen Array sequenziell gesucht und die
	 * letzte Position (Index) wird zurückgegeben, falls gefunden.
	 * 
	 * @param array übergebenes Array welches durchsucht wird.
	 * @param key   übergebener Key, welcher benötigt wird um den Index im Array zu
	 *              bestimmen.
	 * @return Gibt die letzte Position des gesuchten Keys zurück, falls gefunden.
	 *         Falls nicht gefunden wird -1 zurückgegeben.
	 */
	public static int sequentialSearchL(int[] array, int key) {
		// Variablen deklarieren
		int index = 0;
		boolean isIndexFound = false;
		// Sequenzielle Suche im Array
		for (int i = 0; i < array.length; i++) {
			if (array[i] == key) {
				index = i;
				isIndexFound = true;
			}
		}
		// Gibt die Position des gesuchten Keys zurück. Falls nicht gefunden, wird -1
		// zurückgegeben
		if (isIndexFound) {
			return index;
		} else {
			return NO_KEY;
		}
	}

	// Aufgabe 1b
	/**
	 * Der gewünschte Key wird im übergebenen Array sequenziell gesucht und die
	 * erste Position (Index) wird zurückgegeben, falls gefunden.
	 * 
	 * @param array übergebenes Array welches durchsucht wird.
	 * @param key   übergebener Key, welcher benötigt wird um den Index im Array zu
	 *              bestimmen.
	 * @return Gibt die erste Position des gesuchten Keys zurück, falls gefunden.
	 *         Falls nicht gefunden wird -1 zurückgegeben.
	 */
	public static int sequentialSearchF(int[] array, int key) {
		// Sequenzielle Suche im Array
		for (int i = 0; i < array.length; i++) {
			if (array[i] == key) {
				return i;
			}
		}
		return NO_KEY;
	}

	// Aufgabe 1c
	/**
	 * Binäre Suche (Rekursiv) - Der gewünschte Key wird im übergebenen Array binär
	 * gesucht und die Position (Index) wird zurückgegeben, falls gefunden.
	 * 
	 * @param array übergebenes Array, welches durchsucht wird.
	 * @param key   übergebener Key, welcher benötigt wird, um den Index im Array zu
	 *              bestimmen.
	 * @return Gibt die Position des gesuchten Keys zurück, falls gefunden. Falls
	 *         nicht gefunden wird -1 zurückgegeben.
	 */
	public static int binarySearchRec(int[] array, int key, int firstIndex, int lastIndex) {
		// Mitte des Arrays wird erfasst
		int middle = (firstIndex + lastIndex) / 2;
		// Array sortieren
		selectionSort(array);
		// Binäre Suche
		if (firstIndex > lastIndex) {
			return NO_KEY;
		} else if (array[middle] > key) {
			// Rekursiver Aufruf
			return binarySearchRec(array, key, firstIndex, middle - 1);
		} else if (array[middle] < key) {
			// Rekursiver Aufruf
			return binarySearchRec(array, key, middle + 1, lastIndex);
		}
		// Gibt den Index zurück
		return middle;
	}

	// Überladene Methode für JUnit-Tests
	public static int binarySearchRec(int[] array, int key) {
		return binarySearchRec(array, key, 0, array.length - 1);
	}

	// Aufgabe 1d
	/**
	 * Binäre Suche (Iterativ) - Der gewünschte Key wird im übergebenen Array binär
	 * gesucht und die Position (Index) wird zurückgegeben, falls gefunden.
	 * 
	 * @param array übergebenes Array welches durchsucht wird.
	 * @param key   übergebener Key, welcher benötigt wird, um den Index im Array zu
	 *              bestimmen.
	 * @return Gibt die Position des gesuchten Keys zurück, falls gefunden. Falls
	 *         nicht gefunden wird -1 zurückgegeben.
	 */
	public static int binarySearch(int[] array, int key) {
		// Variablen deklarieren
		int start = 0, end = array.length - 1, middle;
		// Das übergebene Array sortieren (nach der SelectionSort-Methode) - Zwingend
		// notwendig sonst funktioniert die Binäre Suche nicht
		selectionSort(array);
		// Binäre Suche, Falls ein Wert gefunden wird, wird die Position zurückgegeben,
		// falls nicht wird -1 zurückgegeben
		while (start <= end) {
			middle = (start + end) / 2;
			if (array[middle] == key) {
				return middle;
			} else if (key < array[middle]) {
				end = middle - 1;
			} else {
				start = middle + 1;
			}
		}
		return NO_KEY;
	}

	/**
	 * Sort Methode zum sortieren eines Arrays, welches die SelectionSort-Variante
	 * benutzt. Kleinster Wert am kleinsten Index, größter Wert am größten Index.
	 * 
	 * @param array übergebenes Array, welches sortiert werden soll.
	 */
	public static void selectionSort(int[] array) {
		// Variablen deklarieren
		int marker = array.length - 1;
		while (marker > 0) {
			int max = 0;
			for (int i = 1; i <= marker; i++) {
				if (array[i] > array[max]) {
					max = i;
				}
			}
			// Werte vertauschen
			swap(array, marker, max);
			marker--;
		}
	}

	/**
	 * Sawp Methode zum vertauschen einzelner Werte in einem Array
	 * 
	 * @param array       übergebenes Array, bei dem 2 Werte 2er Indizes vertauscht
	 *                    werden
	 * @param indexMarker markiert den aktuellen Index, welcher getauscht werden
	 *                    soll
	 * @param indexMax    markiert den gewünschten Index, welcher getauscht werden
	 *                    soll
	 */
	public static void swap(int[] array, int indexMarker, int indexMax) {
		int index = array[indexMarker];
		array[indexMarker] = array[indexMax];
		array[indexMax] = index;
	}
}