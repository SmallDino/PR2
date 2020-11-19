package pr2.uebung02;

public class InsertionSort implements SortInterface{	
	/**
	 * Sortieralgorithmus nach dem InsertionSort-Verfahren ohne Binäre-Suche.
	 * 
	 * @param A  übergebenes Array, welches durchlaufen wird.
	 * @param so Statistik-Objekt der Klasse StatObj, welches benötigt wird, um
	 *           verschiedene Statistiken der Methode zu sammeln.
	 */
	public void sort(int A[], StatObj so) {
		for (int i = 1; i < A.length; i++) {
			int index = i, marker = A[i];
			while (index > 0 && A[index - 1] > marker) {
				so.inccc();
				// Der Wert, der vor dem Index ist (1 Feld links vom Index), wird dem aktuellen
				// Index zugewiesen
				A[index] = A[index - 1];
				so.incsc();
				// Array mit den makierten Feldern ausgeben
				printArrayAndSwaps(A, A[index]);
				index--;
			}
			// Dem Index wird der markierte Wert zugewiesen
			A[index] = marker;
			printArrayAndSwaps(A, A[index], marker);
			so.incsc();
			so.incrc();
			// Zum Schluss des Durchlaufs das Array ausgeben
			printArray(A, so);
		}
		// Nach Abschluss die Statistik-Daten ausgeben
		System.out.println(so.toString());
	}

	/**
	 * Sortieralgorithmus nach dem InsertionSort-Verfahren mit Binärer-Suche.
	 * 
	 * @param A  übergebenes Array, welches durchlaufen wird.
	 * @param so Statistik-Objekt der Klasse StatObj, welches benötigt wird, um
	 *           verschiedene Statistiken der Methode zu sammeln.
	 */
	public void sortBin(int A[], StatObj so) {
		for (int i = 1; i < A.length; i++) {
			// Einfügeposition ermitteln
			int insertPos = binarySearch(A, i, so);
			// Elemente mit dem normalen InsertionSort verschieben und dann dann das Element
			// an der Einfügeposition einfügen
			shiftElements(A, insertPos, i, so);
			so.incrc();
		}
		// Nach Abschluss die Statistik-Daten ausgeben
		System.out.println(so.toString());
	}

	/**
	 * Binäre Suche (Iterativ) - Der gewünschte Key wird im übergebenen Array binär
	 * gesucht und die Position (Index) wird zurückgegeben.
	 * 
	 * @param array     übergebenes Array welches durchsucht wird.
	 * @param lastIndex gibt das Ende des Arrays an
	 * @param so        Statistik-Objekt der Klasse StatObj, welches benötigt wird,
	 *                  um verschiedene Statistiken der Methode zu sammeln.
	 * 
	 * @return Gibt die Position des gesuchten Keys zurück, falls gefunden.
	 */
	private static int binarySearch(int[] array, int lastIndex, StatObj so) {
		int left = 0, right = lastIndex - 1, middle = 0, key = array[lastIndex];

		// InsertionSort
		while (left <= right) {
			so.inccc();
			middle = (left + right) / 2;

			if (left == right && key < array[middle]) {
				so.inccc();
				return middle;
			} else if (left == right && key > array[middle]) {
				so.cc += 2;
				return middle + 1;
			}

			if (array[middle] == key) {				
				return middle;
			} else if (key < array[middle]) {
				right = middle - 1;
			} else {
				left = middle + 1;
			}
		}
		so.inccc();
		return middle;
	}

	/**
	 * 
	 * @param A          übergebenes Array welches durchsucht wird.
	 * @param index      welcher die einzufügende Position repräsentiert.
	 * @param upperLimit repräsentiert das Ende des Arrays
	 * @param so         Statistik-Objekt der Klasse StatObj, welches benötigt wird,
	 *                   um verschiedene Statistiken der Methode zu sammeln.
	 */
	private static void shiftElements(int A[], int index, int upperLimit, StatObj so) {
		int key = A[upperLimit];
		for (int i = upperLimit; index < i; i--) {
			A[i] = A[i - 1];
			so.incsc();
			printArrayAndSwaps(A, i);
		}
		A[index] = key;
		printArrayAndSwaps(A, A[index], key);
		so.incsc();
	}

	/**
	 * Methode zum Ausgeben eines Arrays
	 * 
	 * @param array   übergebenes Array, welches ausgegeben wird
	 * @param statObj Statistik-Objekt der Klasse StatObj, welches benötigt wird, um
	 *                den aktuellen Durchlauf zu bestimmen
	 */
	public static void printArray(int[] array, StatObj statObj) {
		System.err.println("Array nach dem " + statObj.rc + ". Durchlauf:");
		for (int i = 0; i < array.length; i++) {
			System.err.println(array[i]);
		}
		System.out.println("\n--------------------------------\n");
	}

	/**
	 * Methode zum Ausgeben eines Arrays, wobei zusätzlich die stattgefundenen
	 * Vertauschungen hervorgehoben werden
	 * 
	 * @param array übergebenes Array, welches ausgegeben wird
	 * @param swapA erstes Element, welches vertauscht wurde
	 * @param swapB zweites Element, welches vertauscht wurde
	 */
	public static void printArrayAndSwaps(int[] array, int swapA, int swapB) {
		System.out.println("Array nach dem Vertauschen:");
		for (int i = 0; i < array.length; i++) {
			if (((array[i] == swapA) || (array[i] == swapB))) {
				System.out.println(array[i] + " <- wurde vertauscht");
			} else {
				System.out.println(array[i]);
			}
		}
		System.out.println("\n--------------------------------\n");
	}

	/**
	 * Methode zum Ausgeben eines Arrays, wobei zusätzlich die stattgefundenen
	 * Vertauschungen hervorgehoben werden
	 * 
	 * @param array übergebenes Array, welches ausgegeben wird
	 * @param swapA erstes Element, welches vertauscht wurde
	 * @param swapB zweites Element, welches vertauscht wurde
	 */
	public static void printArrayAndSwaps(int[] array, int swapOnce) {
		boolean isMarked = false;
		System.out.println("Array nach dem Vertauschen:");
		for (int i = 0; i < array.length; i++) {
			if ((array[i] == swapOnce) && (!isMarked)) {
				System.out.println(array[i] + " <- wurde vertauscht");
				isMarked = true;
			} else {
				System.out.println(array[i]);
			}
		}
		System.out.println("\n--------------------------------\n");
	}
}