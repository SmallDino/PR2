package pr2.uebung01;

public class Komplexitätsexperiment {
	// Konstanten
	public static final int NO_KEY = -1;
	public static int BIN_COUNTER = 0;

	public static void main(String[] args) {
		// Variablen deklarieren
		int[] array1024 = new int[1024], array2048 = new int[2048], array4096 = new int[4096];
		int key1024, key2048, key4096;

		// Arrays befüllen
		for (int i = 0; i < array1024.length; i++) {
			array1024[i] = i;
		}

		for (int i = 0; i < array2048.length; i++) {
			array2048[i] = i;
		}

		for (int i = 0; i < array4096.length; i++) {
			array4096[i] = i;
		}

		// Aufgabe 2a
		for (int i = 0; i < 100; i++) {
			key1024 = (int) (Math.random() * 1023);
			System.out.println("Key: " + key1024);
			System.out.println(
					"Durchlauf: " + (i + 1) + " - Anzahl der Vergleiche: " + seqSearchLastIndex(array1024, key1024));
		}

		System.err.println("\n------------------------------\n");

		// Aufgabe 2b
		for (int i = 0; i < 100; i++) {
			key1024 = (int) (Math.random() * 1023);
			System.out.println("Key: " + key1024);
			System.out.println(
					"Durchlauf: " + (i + 1) + " - Anzahl der Vergleiche: " + seqSearchFirstIndex(array1024, key1024));
		}

		System.err.println("\n------------------------------\n");

		// Aufgabe 2c
		for (int i = 0; i < 100; i++) {
			key1024 = (int) (Math.random() * 1023);
			BIN_COUNTER = 0;
			System.out.println("Key: " + key1024);
			System.out.println(
					"Durchlauf: " + (i + 1) + " - Anzahl der Vergleiche: " + binSearchRekursiv(array1024, key1024));
		}

		System.err.println("\n------------------------------\n");

		// Aufgabe 2d
		for (int i = 0; i < 100; i++) {
			key1024 = (int) (Math.random() * 1023);
			System.out.println("Key: " + key1024);
			System.out.println(
					"Durchlauf: " + (i + 1) + " - Anzahl der Vergleiche: " + binSearchIterativ(array1024, key1024));
		}
	}

	// Aufgabe 1a
	/**
	 * Gibt die Anzahl der Schlüssel-Vergleiche zurück. Es wird immer das ganze
	 * Array verglichen.
	 * 
	 * @param array übergebenes Array welches durchsucht wird.
	 * @param key   übergebener Key, welcher benötigt wird um die Anzahl der
	 *              Vergleiche zu ermitteln.
	 * @return Gibt die Anzahl der Schlüssel-Vergleiche zurück.
	 */
	public static int seqSearchLastIndex(int[] array, int key) {
		if (array.length == 0) {
			return 0;
		} else {
			return array.length;
		}
	}

	// Aufgabe 1b
	/**
	 * Gibt die Anzahl der Schlüssel-Vergleiche zurück. Sobald der Schlüssel
	 * gefunden wurde, wird die Anzahl sofort zurückgegeben.
	 * 
	 * @param array übergebenes Array welches durchsucht wird.
	 * @param key   übergebener Key, welcher benötigt wird um die Anzahl der
	 *              Vergleiche zu ermitteln.
	 * @return Gibt die Anzahl der Schlüssel-Vergleiche zurück.
	 */
	public static int seqSearchFirstIndex(int[] array, int key) {
		// Sequenzielle Suche im Array
		for (int i = 0; i < array.length; i++) {
			if (array[i] == key) {
				return i + 1;
			}
		}

		if (array.length == 0) {
			return 0;
		} else {
			return array.length;
		}
	}

	// Aufgabe 1c
	/**
	 * Gibt die Anzahl der Schlüssel-Vergleiche zurück. Sobald der Schlüssel
	 * gefunden wurde, wird die Anzahl sofort zurückgegeben.
	 * 
	 * @param array übergebenes Array welches durchsucht wird.
	 * @param key   übergebener Key, welcher benötigt wird um die Anzahl der
	 *              Vergleiche zu ermitteln.
	 * @param firstIndex ist der Anfang des Arrays.
	 * @param lastIndex ist das Ende des Arrays.
	 * @return Gibt die Anzahl der Schlüssel-Vergleiche zurück.
	 */
	public static int binSearchRekursiv(int[] array, int key, int firstIndex, int lastIndex) {
		// Mitte des Arrays wird erfasst
		int middle = (firstIndex + lastIndex) / 2;
		// Array sortieren
		selectionSort(array);
		// Binäre Suche
		BIN_COUNTER++;
		if (firstIndex > lastIndex) {
			return BIN_COUNTER;
		} else if (array[middle] > key) {
			BIN_COUNTER++;
			// Rekursiver Aufruf
			return binSearchRekursiv(array, key, firstIndex, middle - 1);
		} else if (array[middle] < key) {
			BIN_COUNTER++;
			// Rekursiver Aufruf
			return binSearchRekursiv(array, key, middle + 1, lastIndex);
		}
		// Gibt den Index zurück
		return BIN_COUNTER;
	}

	// Überladene Methode für JUnit-Tests
	public static int binSearchRekursiv(int[] array, int key) {
		return binSearchRekursiv(array, key, 0, array.length - 1);
	}

	// Aufgabe 1d
	/**
	 * Gibt die Anzahl der Schlüssel-Vergleiche zurück. Sobald der Schlüssel
	 * gefunden wurde, wird die Anzahl sofort zurückgegeben.
	 * 
	 * @param array übergebenes Array welches durchsucht wird.
	 * @param key   übergebener Key, welcher benötigt wird um die Anzahl der
	 *              Vergleiche zu ermitteln.
	 * @return Gibt die Anzahl der Schlüssel-Vergleiche zurück.
	 */
	public static int binSearchIterativ(int[] array, int key) {
		// Variablen deklarieren
		int start = 0, end = array.length - 1, middle, counter = 0;
		// Das übergebene Array sortieren (nach der SelectionSort-Methode) - Zwingend
		// notwendig sonst funktioniert die Binäre Suche nicht
		selectionSort(array);
		// Binäre Suche, Falls ein Wert gefunden wird, wird die Position zurückgegeben,
		// falls nicht wird -1 zurückgegeben
		while (start <= end) {
			middle = (start + end) / 2;
			counter++;
			if (array[middle] == key) {
				return counter;
			} else if (array[middle] > key) {
				counter++;
				end = middle - 1;
			} else if (array[middle] < key) {
				counter++;
				start = middle + 1;
			}
		}
		return counter;
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