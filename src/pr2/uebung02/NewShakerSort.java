package pr2.uebung02;

public class NewShakerSort {

	public static void main(String[] args) {
		int[] F1 = new int[] { 10, 4, 33, 44, 17, 20, 3, 2, 9, 82, 38, 67, 55, 11, 32, 23, 19, 7, 6, 14, 29 };

		StatObj soObj = new StatObj();

		sort(F1, soObj);

		for (int i = 0; i < F1.length; i++) {
			System.out.println(F1[i]);
		}
	}

	public static void sort(int A[], StatObj so) {
		// Variablen deklarieren
		boolean swapped;
		boolean isSwapped = false;
		int lastUnsorted = A.length - 1;
		int firstUnsorted = 1;

		do {
			if (!isSwapped) {
				System.out.println("Bubble");
				// Array von unten nach oben (links nach rechts) durchsuchen, vergleichen und
				// vertauschen
				swapped = false;
				// Array durchlaufen
				for (int i = 0; i < lastUnsorted; i++) {
					so.inccc();
					if (A[i] > A[i + 1]) {
						so.incsc();
						// Werte im Array vertauschen
						swap(A, i, i + 1);
						// Array ausgeben und vertauschte Felder hervorheben
						// printArrayAndSwaps(A, A[i], A[i + 1]);
						swapped = true;
					}
				}
				lastUnsorted--;
				so.incrc();
				isSwapped = true;
			} else {
				System.out.println("Shaker");
				// Array von oben nach unten (rechts nach links) durchsuchen, vergleichen und
				// vertauschen
				swapped = false;
				// Array durchlaufen
				for (int i = A.length - 2; i >= firstUnsorted; i--) {
					so.inccc();
					if (A[i] > A[i + 1]) {
						so.incsc();
						// Werte im Array vertauschen
						swap(A, i, i + 1);
						// Array ausgeben und vertauschte Felder hervorheben
						// printArrayAndSwaps(A, A[i], A[i + 1]);
						swapped = true;
					}
				}
				firstUnsorted++;
				so.incrc();
				isSwapped = false;
			}
			// Array nach jedem Durchlauf ausgeben
			// printArray(A, so);
		} while (swapped);
		// Nach Abschluss die Statistik-Daten ausgeben
		System.out.println(so.toString());
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
