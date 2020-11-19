package pr2.uebung02;

public class QuickSort implements SortInterface {
	/**
	 * Sortieralgorithmus nach dem QuickSort-Verfahren (Variante 1).
	 * 
	 * @param A          übergebenes Array, welches durchlaufen wird.
	 * @param so         Statistik-Objekt der Klasse StatObj, welches benötigt wird,
	 *                   um verschiedene Statistiken der Methode zu sammeln.
	 */
	public void sort(int A[], StatObj so) {
		sort(A, so, 0, A.length - 1);
		System.out.println(so.toString());
	}

	/**
	 * Sortieralgorithmus nach dem QuickSort-Verfahren (Variante 1).
	 * 
	 * @param A          übergebenes Array, welches durchlaufen wird.
	 * @param so         Statistik-Objekt der Klasse StatObj, welches benötigt wird,
	 *                   um verschiedene Statistiken der Methode zu sammeln.
	 * @param lowerLimit Untere Grenze des Arrays.
	 * @param upperLimit Obere Grenze des Arrays.
	 */
	private static void sort(int[] A, StatObj so, int lowerLimit, int upperLimit) {
		if (lowerLimit < upperLimit) {
			// Gibt den Index des neuen Pivotelemts an
			int index = partition(A, so, lowerLimit, upperLimit);
			// Rekursiver Aufruf der sort-Methode
			sort(A, so, lowerLimit, index);
			sort(A, so, index + 1, upperLimit);
		}
		so.incrc();
	}

	/**
	 * Ermittelt das mittlere Element im Array als Pivot. Das Pivotelement wird an
	 * seine korrekte Position getauscht und alle kleineren Elemente stehen davor
	 * bzw. alle größeren Elemente stehen hinter dem Pivot.
	 * 
	 * @param A          übergebenes Array, welches durchlaufen wird.
	 * @param so         Statistik-Objekt der Klasse StatObj, welches benötigt wird,
	 *                   um verschiedene Statistiken der Methode zu sammeln.
	 * @param lowerLimit Untere Grenze des Arrays.
	 * @param upperLimit Obere Grenze des Arrays.
	 * @return gibt das am weitesten rechts stehende Element zurück.
	 */
	private static int partition(int[] A, StatObj so, int lowerLimit, int upperLimit) {
		int pivotIndex = (lowerLimit + upperLimit) / 2, pivot = A[pivotIndex];

		while (lowerLimit <= upperLimit) {
			int left = lowerLimit, right = upperLimit;
			
			// Durchsucht von links ein Element das größer als das Pivot ist
			while (A[left] < pivot) {
				left++;
				so.inccc();
			}
			
			// Durchsucht von rechts ein Element das kleiner als das Pivot ist
			while (A[right] > pivot) {
				right--;
				so.inccc();
			}

			// Werte im Array vergleichen
			if (left < right) {
				so.inccc();
				// Werte im Array werden vertauscht
				swap(A, left, right);
				so.incsc();
				printArrayAndSwaps(A, A[left], A[right]);

				// Ober- und Untergrenze werden jeweils um 1 nach innen geschoben
				lowerLimit = left + 1;
				upperLimit = right - 1;

				printArray(A, so);
			} else {
				so.inccc();
				return right;
			}
		}
		return upperLimit;
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
		int tmp = array[indexMarker];
		array[indexMarker] = array[indexMax];
		array[indexMax] = tmp;
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
}