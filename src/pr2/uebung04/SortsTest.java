package pr2.uebung04;

import org.junit.Test;

import static org.junit.Assert.*;

import java.lang.invoke.MethodHandles;

public class SortsTest {

	public static Comparable[] fillArrayWithIntElementsFromFile(String filename) {

		/**
		 * 
		 * @param filename Datei mit IntElementen
		 * @return Array mit IntElementen
		 */

		int[] intValues = readIntegerArray(filename);

		Comparable[] intElements = new Comparable[intValues.length];

		for (int i = 0; i < intValues.length; i++) {
			intElements[i] = new IntElement(intValues[i]);
			// geht wg. MakeItSimple nicht anders
		}

		return intElements;

	}

	public static Comparable[] fillArrayWithStringElementsFromFile(String filename) {

		/**
		 * 
		 * @param filename Datei mit StringElementen
		 * @return Array mit StringElementen
		 */

		String[] stringValues = readStringArray(filename);

		Comparable[] stringElements = new Comparable[stringValues.length];

		for (int i = 0; i < stringValues.length; i++) {
			stringElements[i] = new StringElement(stringValues[i]);
			// geht wg. MakeItSimple nicht anders
		}

		return stringElements;

	}

	public static Comparable[] fillArrayWithSongsFromFile(String filename) {

		String[] songs = readStringArray(filename); // all songs from file - line by line

		Comparable[] allSongs = new Comparable[songs.length];
		int j = 0;

		// Hier werden die einzelnen Zeilen der Eingabedatei auseinandergenommen
		// und in ein Objekt vom Typ SongImplementation überführt.
		// In jeder Zeile sind Song, Interpret(en) und Album durch ; getrennt

		for (String songString : songs) {
			String[] parts = songString.split(";");
			String[] artists = new String[parts.length - 2];
			for (int i = 2; i < parts.length; i++)
				artists[i - 2] = parts[i];

			allSongs[j++] = new SongImplementation(parts[0], artists, parts[1]);
			// parts[1] = Album
		}
		return allSongs;
	}

	public Comparable[] setUpIntElements(String name) {

		Comparable[] arrayToSort;

		String filename = MethodHandles.lookup().lookupClass().getResource(name).getPath();

		return fillArrayWithIntElementsFromFile(filename);

	}

	public Comparable[] setUpStringElements(String name) {

		String filename = MethodHandles.lookup().lookupClass().getResource(name).getPath();

		return fillArrayWithStringElementsFromFile(filename);

	}

	public Comparable[] setUpSongs(String name) {

		String filename = MethodHandles.lookup().lookupClass().getResource(name).getPath();

		return fillArrayWithSongsFromFile(filename);

	}

	public boolean sorted(Comparable[] array) {
		for (int i = 1; i < array.length; i++)
			if (array[i - 1].compareTo(array[i]) > 0)
				return false;
		return true;

	}

	@Test
	public void testsOnIntElements10() throws Exception { // ShellSort - file 1

		Comparable[] arrayToSort = setUpIntElements("treeInput.txt");

		assertEquals(16, arrayToSort.length); //
		SortMethod m = new QuickSort();
		m.sort(arrayToSort);
		// Testen, ob Array richtig und vollständig sortiert wurde
		assertEquals(16, arrayToSort.length); //
		assertTrue(sorted(arrayToSort));
	}

	@Test
	public void testsOnIntElements11() throws Exception { // ShakerSort - file 1

		Comparable[] arrayToSort = setUpIntElements("treeInput.txt");

		assertEquals(16, arrayToSort.length); //
		SortMethod m = new ShakerSort();
		m.sort(arrayToSort);
		// Testen, ob Array richtig und vollständig sortiert wurde
		assertEquals(16, arrayToSort.length); //
		assertTrue(sorted(arrayToSort));
	}

	@Test
	public void testsOnStringElements10() throws Exception { // ShellSort - file 2

		Comparable[] arrayToSort = setUpStringElements("treeInputStrings.txt");

		assertEquals(16, arrayToSort.length); //
		SortMethod m = new QuickSort();
		m.sort(arrayToSort);
		// Testen, ob Array richtig und vollständig sortiert wurde
		assertEquals(16, arrayToSort.length); //
		assertTrue(sorted(arrayToSort));
	}

	@Test
	public void testsOnStringElements11() throws Exception { // ShakerSort - file 2

		Comparable[] arrayToSort = setUpStringElements("treeInputStrings.txt");

		assertEquals(16, arrayToSort.length); //
		SortMethod m = new ShakerSort();
		m.sort(arrayToSort);
		// Testen, ob Array richtig und vollständig sortiert wurde
		assertEquals(16, arrayToSort.length); //
		assertTrue(sorted(arrayToSort));
	}

	@Test
	public void testsOnSongElements10() throws Exception { // ShellSort - file 2

		Comparable[] arrayToSort = setUpSongs("songs.txt");

		assertEquals(2928, arrayToSort.length); //
		SortMethod m = new QuickSort();
		m.sort(arrayToSort);
		// Testen, ob Array richtig und vollständig sortiert wurde
		assertEquals(2928, arrayToSort.length); //
		assertTrue(sorted(arrayToSort));
	}

	@Test
	public void testsOnSongElements11() throws Exception { // ShakerSort - file 2

		Comparable[] arrayToSort = setUpSongs("songs.txt");

		assertEquals(2928, arrayToSort.length); //
		SortMethod m = new ShakerSort();
		m.sort(arrayToSort);
		// Testen, ob Array richtig und vollständig sortiert wurde
		assertEquals(2928, arrayToSort.length); //
		assertTrue(sorted(arrayToSort));
	}

}
