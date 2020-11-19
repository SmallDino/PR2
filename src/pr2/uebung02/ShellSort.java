    package pr2.uebung02;

    public class ShellSort implements SortInterface {
    	/**
    	 * Sortieralgorithmus nach dem Shellsort-Verfahren.
    	 * 
    	 * @param A  übergebenes Array, welches durchlaufen wird.
    	 * @param so Statistik-Objekt der Klasse StatObj, welches benötigt wird, um
    	 *           verschiedene Statistiken der Methode zu sammeln.
    	 */
    	public void sort(int[] A, StatObj so) {
    		int index, hSortLevel, marker;
    		int[] partition = { 9, 7, 4, 1 };

    		for (int i = 0; i < partition.length; i++) {
    			hSortLevel = partition[i];
    			// Sortiere die einzelnen "Level" nach dem InsertionSort-Verfahren
    			for (int j = hSortLevel; j < A.length; j++) {
    				marker = A[j];
    				index = j;
    				// InsertionSort-Verfahren
    				while (index >= hSortLevel && A[index - hSortLevel] > marker) {
    					so.inccc();
    					A[index] = A[index - hSortLevel];
    					so.incsc();
    					// Array mit den makierten Feldern ausgeben
    					printArrayAndSwaps(A, A[index]);
    					index -= hSortLevel;
    				}
    				// Dem Index wird der markierte Wert zugewiesen
    				A[index] = marker;
    				printArrayAndSwaps(A, A[index], marker);
    				so.incsc();
    				so.incrc();
    				// Zum Schluss des Durchlaufs das Array ausgeben
    				printArray(A, so);
    			}
    			so.incrc();
    		}
    		System.out.println(so.toString());
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