package pr2.hausarbeit;

import static org.junit.Assert.*;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.invoke.MethodHandles;

public class BTreeTest {
	// --- IntElement ---
	private BTreeImplementation<IntElement> bt1 = new BTreeImplementation<IntElement>(1);
	private BTreeImplementation<IntElement> bt2;
	private BTreeImplementation<IntElement> bt3 = new BTreeImplementation<IntElement>(2);
	// --- StringElement ---
	private BTreeImplementation<StringElement> bt5 = new BTreeImplementation<StringElement>(1);
	// --- Song ---
	private BTreeImplementation<Song> bt7 = new BTreeImplementation<Song>(1);

	/*
	 * --------------- Tests insert() IntElement ---------------
	 */
	@Test
	public void testInsertIntElementWithSmallestDegree() {
		// Deep Test
		assertEquals(true, bt1.isEmpty()); // Leerer Baum
		assertEquals(0, bt1.height()); // Leerer Baum = Höhe 0
		assertEquals(0, bt1.size()); // Leerer Baum = Size 0
		assertEquals(true, bt1.insert(new IntElement(30)));
		assertEquals(false, bt1.isEmpty()); // Element wurde eingefügt - Baum befüllt
		assertEquals(1, bt1.height()); // Element wurde eingefügt - Wurzel hat Höhe 1
		assertEquals(1, bt1.size()); // Element wurde eingefügt - Size ist 1
		assertEquals(true, bt1.insert(new IntElement(70)));
		assertEquals(true, bt1.insert(new IntElement(110))); // Node split - Rechts eingefügt
		assertEquals(2, bt1.height()); // Da Node split - Baum ist in Höhe gewachsen
		assertEquals(3, bt1.size());
		assertEquals(0, bt1.getMin().compareTo(new IntElement(30))); // Ermittle Minimum
		assertEquals(0, bt1.getMax().compareTo(new IntElement(110))); // Ermittle Maximum
		assertEquals(true, bt1.insert(new IntElement(55)));
		assertEquals(true, bt1.insert(new IntElement(25))); // Node split - Links eingefügt
		assertEquals(2, bt1.height()); // Baum muss immernoch Höhe 2 haben
		assertEquals(true, bt1.insert(new IntElement(150)));
		assertEquals(true, bt1.insert(new IntElement(65)));
		assertEquals(true, bt1.insert(new IntElement(10)));
		assertEquals(2, bt1.height()); // Baum muss immernoch Höhe 2 haben
		assertEquals(true, bt1.insert(new IntElement(60))); // Root split - Double split
		assertEquals(3, bt1.height()); // Baum ist um 1 Höhe gewachsen
		assertEquals(9, bt1.size());
		assertEquals(false, bt1.insert(new IntElement(25))); // Schon vorhanden
		assertEquals(false, bt1.insert(new IntElement(55))); // Schon vorhanden
		assertEquals(false, bt1.insert(new IntElement(65))); // Schon vorhanden
		assertEquals(false, bt1.insert(new IntElement(110))); // Schon vorhanden
		assertEquals(true, bt1.insert(new IntElement(2))); // Node split - Links eingefügt
		assertEquals(0, bt1.getMin().compareTo(new IntElement(2))); // Ermittle Minimum
		assertEquals(0, bt1.getMax().compareTo(new IntElement(150))); // Ermittle Maximum
		assertEquals(true, bt1.insert(new IntElement(8)));
		assertEquals(true, bt1.insert(new IntElement(27)));
		assertEquals(true, bt1.insert(new IntElement(59)));
		assertEquals(true, bt1.insert(new IntElement(40)));
		assertEquals(true, bt1.insert(new IntElement(9)));
		assertEquals(3, bt1.height());
		assertEquals(true, bt1.insert(new IntElement(29))); // Root split - Tripple split
		assertEquals(4, bt1.height());
		assertEquals(16, bt1.size());
		assertEquals(false, bt1.insert(new IntElement(2))); // Schon vorhanden
		assertEquals(false, bt1.insert(new IntElement(9))); // Schon vorhanden
		assertEquals(false, bt1.insert(new IntElement(25))); // Schon vorhanden
		assertEquals(false, bt1.insert(new IntElement(29))); // Schon vorhanden
		assertEquals(false, bt1.insert(new IntElement(40))); // Schon vorhanden
		assertEquals(false, bt1.insert(new IntElement(59))); // Schon vorhanden
		assertEquals(false, bt1.insert(new IntElement(65))); // Schon vorhanden
		assertEquals(false, bt1.insert(new IntElement(110))); // Schon vorhanden
		assertEquals(false, bt1.insert(new IntElement(150))); // Schon vorhanden
		assertEquals(0, bt1.getMin().compareTo(new IntElement(2))); // Ermittle Minimum
		assertEquals(0, bt1.getMax().compareTo(new IntElement(150))); // Ermittle Maximum

		// Tests der B-Tree Reihenfolge
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();

		// InOrder
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		bt1.printInOrder();
		String expectedOutputIO = "2 8 9 10 25 27 29 30 40 55 59 60 65 70 110 150 ";
		assertEquals(expectedOutputIO, outContent.toString()); // Test InOrder

		// PostOrder
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		bt1.printPostOrder();
		String expectedOutputPO = "2 9 8 25 29 27 10 40 59 55 65 110 150 70 60 30 ";
		assertEquals(expectedOutputPO, outContent.toString()); // Test PostOrder

		// PreOrder
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		bt1.printPreOrder();
		String expectedOutputPREO = "30 10 8 2 9 27 25 29 60 55 40 59 70 65 110 150 ";
		assertEquals(expectedOutputPREO, outContent.toString()); // Test PreOrder

		// LevelOrder
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		bt1.printLevelOrder();
		String expectedOutputLO = "30 10 60 8 27 55 70 2 9 25 29 40 59 65 110 150 ";
		assertEquals(expectedOutputLO, outContent.toString()); // Test LevelOrder

		// Weitere B-Tree Tests
		assertEquals(true, bt1.insert(new IntElement(349)));
		assertEquals(true, bt1.insert(new IntElement(3)));
		assertEquals(0, bt1.getMin().compareTo(new IntElement(2))); // Ermittle Minimum
		assertEquals(0, bt1.getMax().compareTo(new IntElement(349))); // Ermittle Maximum
		bt1.clear(); // Baum löschen
		assertEquals(0, bt1.height());
		assertEquals(0, bt1.size());
		assertEquals(true, bt1.isEmpty());
		assertEquals(true, bt1.insert(new IntElement(10)));
		assertEquals(1, bt1.height());
		assertEquals(1, bt1.size());
		assertEquals(false, bt1.isEmpty());
		assertEquals(false, bt1.insert(new IntElement(10)));
	}

	@Test
	public void testInsertIntElementWithSmallestDegreeFromFile() {
		// InsertFromFileTest
		assertEquals(true, bt1.isEmpty());
		assertEquals(0, bt1.height());
		assertEquals(0, bt1.size());
		String fileWith16 = MethodHandles.lookup().lookupClass().getResource("bTreeInput1.txt").getPath();
		String fileWith17 = MethodHandles.lookup().lookupClass().getResource("bTreeInput2.txt").getPath();
		assertEquals(true, bt1.insert(fileWith16, "IntElement"));
		assertEquals(false, bt1.insert(fileWith16, "IntElement")); // Kein zusätzliches Element wird eingefügt
		assertEquals(false, bt1.isEmpty());
		assertEquals(4, bt1.height()); // Höhe des Baums
		assertEquals(16, bt1.size()); // Anzahl der Elemente im Baum
		assertEquals(false, bt1.insert(new IntElement(2))); // Schon vorhanden
		assertEquals(false, bt1.insert(new IntElement(9))); // Schon vorhanden
		assertEquals(false, bt1.insert(new IntElement(25))); // Schon vorhanden
		assertEquals(false, bt1.insert(new IntElement(29))); // Schon vorhanden
		assertEquals(false, bt1.insert(new IntElement(40))); // Schon vorhanden
		assertEquals(false, bt1.insert(new IntElement(59))); // Schon vorhanden
		assertEquals(false, bt1.insert(new IntElement(65))); // Schon vorhanden
		assertEquals(false, bt1.insert(new IntElement(110))); // Schon vorhanden
		assertEquals(false, bt1.insert(new IntElement(150))); // Schon vorhanden
		assertEquals(0, bt1.getMin().compareTo(new IntElement(2))); // Ermittle Minimum
		assertEquals(0, bt1.getMax().compareTo(new IntElement(150))); // Ermittle Maximum

		// Tests der B-Tree Reihenfolge
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();

		// InOrder
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		bt1.printInOrder();
		String expectedOutputIO = "2 8 9 10 25 27 29 30 40 55 59 60 65 70 110 150 ";
		assertEquals(expectedOutputIO, outContent.toString()); // Test InOrder

		// PostOrder
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		bt1.printPostOrder();
		String expectedOutputPO = "2 9 8 25 29 27 10 40 59 55 65 110 150 70 60 30 ";
		assertEquals(expectedOutputPO, outContent.toString()); // Test PostOrder

		// PreOrder
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		bt1.printPreOrder();
		String expectedOutputPREO = "30 10 8 2 9 27 25 29 60 55 40 59 70 65 110 150 ";
		assertEquals(expectedOutputPREO, outContent.toString()); // Test PreOrder

		// LevelOrder
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		bt1.printLevelOrder();
		String expectedOutputLO = "30 10 60 8 27 55 70 2 9 25 29 40 59 65 110 150 ";
		assertEquals(expectedOutputLO, outContent.toString()); // Test LevelOrder

		// Weitere B-Tree Tests
		assertEquals(true, bt1.insert(fileWith17, "IntElement")); // File ist identisch, bis auf 1 verstecktes Element
		assertEquals(false, bt1.insert(fileWith17, "IntElement"));
		assertEquals(4, bt1.height()); // Höhe des Baums
		assertEquals(17, bt1.size()); // Anzahl der Elemente im Baum
		assertEquals(0, bt1.getMin().compareTo(new IntElement(2))); // Ermittle Minimum
		assertEquals(0, bt1.getMax().compareTo(new IntElement(150))); // Ermittle Maximum
		bt1.clear(); // Baum löschen
		assertEquals(0, bt1.height());
		assertEquals(0, bt1.size());
		assertEquals(true, bt1.isEmpty());
		assertEquals(true, bt1.insert(new IntElement(10)));
		assertEquals(1, bt1.height());
		assertEquals(1, bt1.size());
		assertEquals(false, bt1.isEmpty());
		assertEquals(false, bt1.insert(new IntElement(10)));
	}

	@Test
	public void testInsertIntElementWithBiggerDegree() {
		// Deep Test
		assertEquals(true, bt3.isEmpty()); // Leerer Baum
		assertEquals(0, bt3.height()); // Leerer Baum = Höhe 0
		assertEquals(0, bt3.size()); // Leerer Baum = Size 0
		assertEquals(true, bt3.insert(new IntElement(100)));
		assertEquals(false, bt3.isEmpty()); // Element wurde eingefügt - Baum befüllt
		assertEquals(1, bt3.height()); // Element wurde eingefügt - Wurzel hat Höhe 1
		assertEquals(1, bt3.size()); // Element wurde eingefügt - Size ist 1
		assertEquals(true, bt3.insert(new IntElement(50)));
		assertEquals(true, bt3.insert(new IntElement(75)));
		assertEquals(true, bt3.insert(new IntElement(150)));
		assertEquals(1, bt3.height()); // Baum muss immernoch Höhe 1 haben
		assertEquals(true, bt3.insert(new IntElement(125))); // Root Split
		assertEquals(2, bt3.height()); // Baum ist um 1 Höhe gewachsen
		assertEquals(5, bt3.size());
		assertEquals(0, bt3.getMin().compareTo(new IntElement(50))); // Ermittle Minimum
		assertEquals(0, bt3.getMax().compareTo(new IntElement(150))); // Ermittle Maximum
		assertEquals(true, bt3.insert(new IntElement(70)));
		assertEquals(true, bt3.insert(new IntElement(61)));
		assertEquals(true, bt3.insert(new IntElement(175)));
		assertEquals(true, bt3.insert(new IntElement(25))); // Node Split
		assertEquals(2, bt3.height()); // Baum muss immernoch Höhe 2 haben
		assertEquals(9, bt3.size());
		assertEquals(0, bt3.getMin().compareTo(new IntElement(25))); // Ermittle Minimum
		assertEquals(0, bt3.getMax().compareTo(new IntElement(175))); // Ermittle Maximum
		assertEquals(true, bt3.insert(new IntElement(200)));
		assertEquals(true, bt3.insert(new IntElement(120))); // Node Split
		assertEquals(2, bt3.height()); // Baum muss immernoch Höhe 2 haben
		assertEquals(true, bt3.insert(new IntElement(15)));
		assertEquals(true, bt3.insert(new IntElement(60)));
		assertEquals(2, bt3.height()); // Baum muss immernoch Höhe 2 haben
		assertEquals(true, bt3.insert(new IntElement(62)));
		assertEquals(true, bt3.insert(new IntElement(99)));
		assertEquals(2, bt3.height()); // Baum muss immernoch Höhe 2 haben
		assertEquals(true, bt3.insert(new IntElement(101)));
		assertEquals(true, bt3.insert(new IntElement(149)));
		assertEquals(2, bt3.height()); // Baum muss immernoch Höhe 2 haben
		assertEquals(true, bt3.insert(new IntElement(110)));
		assertEquals(2, bt3.height()); // Baum muss immernoch Höhe 2 haben
		assertEquals(0, bt3.getMin().compareTo(new IntElement(15))); // Ermittle Minimum
		assertEquals(0, bt3.getMax().compareTo(new IntElement(200))); // Ermittle Maximum
		assertEquals(true, bt3.insert(new IntElement(72))); // Double Split - Node / Root
		assertEquals(3, bt3.height()); // Baum ist um 1 Höhe gewachsen
		assertEquals(19, bt3.size());
		assertEquals(false, bt3.insert(new IntElement(15))); // Schon vorhanden
		assertEquals(false, bt3.insert(new IntElement(60))); // Schon vorhanden
		assertEquals(false, bt3.insert(new IntElement(70))); // Schon vorhanden
		assertEquals(false, bt3.insert(new IntElement(99))); // Schon vorhanden
		assertEquals(false, bt3.insert(new IntElement(110))); // Schon vorhanden
		assertEquals(false, bt3.insert(new IntElement(149))); // Schon vorhanden
		assertEquals(false, bt3.insert(new IntElement(200))); // Schon vorhanden
		assertEquals(false, bt3.insert(new IntElement(150))); // Schon vorhanden
		assertEquals(false, bt3.insert(new IntElement(72))); // Schon vorhanden
		assertEquals(false, bt3.insert(new IntElement(100))); // Schon vorhanden
		assertEquals(0, bt3.getMin().compareTo(new IntElement(15))); // Ermittle Minimum
		assertEquals(0, bt3.getMax().compareTo(new IntElement(200))); // Ermittle Maximum

		// Tests der B-Tree Reihenfolge
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();

		// InOrder
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		bt3.printInOrder();
		String expectedOutputIO = "15 25 50 60 61 62 70 72 75 99 100 101 110 120 125 149 150 175 200 ";
		assertEquals(expectedOutputIO, outContent.toString()); // Test InOrder

		// PostOrder
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		bt3.printPostOrder();
		String expectedOutputPO = "15 25 50 60 62 70 75 99 61 72 101 110 125 149 175 200 120 150 100 ";
		assertEquals(expectedOutputPO, outContent.toString()); // Test PostOrder

		// PreOrder
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		bt3.printPreOrder();
		String expectedOutputPREO = "100 61 72 15 25 50 60 62 70 75 99 120 150 101 110 125 149 175 200 ";
		assertEquals(expectedOutputPREO, outContent.toString()); // Test PreOrder

		// LevelOrder
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		bt3.printLevelOrder();
		String expectedOutputLO = "100 61 72 120 150 15 25 50 60 62 70 75 99 101 110 125 149 175 200 ";
		assertEquals(expectedOutputLO, outContent.toString()); // Test LevelOrder

		// Weitere B-Tree Tests
		assertEquals(true, bt3.insert(new IntElement(349)));
		assertEquals(true, bt3.insert(new IntElement(14)));
		assertEquals(21, bt3.size());
		assertEquals(0, bt3.getMin().compareTo(new IntElement(14))); // Ermittle Minimum
		assertEquals(0, bt3.getMax().compareTo(new IntElement(349))); // Ermittle Maximum
		bt3.clear(); // Baum löschen
		assertEquals(0, bt3.height());
		assertEquals(0, bt3.size());
		assertEquals(true, bt3.isEmpty());
		assertEquals(true, bt3.insert(new IntElement(10)));
		assertEquals(1, bt3.height());
		assertEquals(1, bt3.size());
		assertEquals(false, bt3.isEmpty());
		assertEquals(false, bt3.insert(new IntElement(10)));
	}

	@Test
	public void testInsertIntElementWithBiggerDegreeFromFile() {
		// InsertFromFileTest
		assertEquals(true, bt3.isEmpty());
		assertEquals(0, bt3.height());
		assertEquals(0, bt3.size());
		String fileWith19 = MethodHandles.lookup().lookupClass().getResource("bTreeInput3.txt").getPath();
		String fileWith20 = MethodHandles.lookup().lookupClass().getResource("bTreeInput4.txt").getPath();
		assertEquals(true, bt3.insert(fileWith19, "IntElement"));
		assertEquals(false, bt3.insert(fileWith19, "IntElement")); // Kein zusätzliches Element wird eingefügt
		assertEquals(false, bt3.isEmpty());
		assertEquals(3, bt3.height()); // Höhe des Baums
		assertEquals(19, bt3.size()); // Anzahl der Elemente im Baum
		assertEquals(false, bt3.insert(new IntElement(15))); // Schon vorhanden
		assertEquals(false, bt3.insert(new IntElement(60))); // Schon vorhanden
		assertEquals(false, bt3.insert(new IntElement(70))); // Schon vorhanden
		assertEquals(false, bt3.insert(new IntElement(99))); // Schon vorhanden
		assertEquals(false, bt3.insert(new IntElement(110))); // Schon vorhanden
		assertEquals(false, bt3.insert(new IntElement(149))); // Schon vorhanden
		assertEquals(false, bt3.insert(new IntElement(200))); // Schon vorhanden
		assertEquals(false, bt3.insert(new IntElement(150))); // Schon vorhanden
		assertEquals(false, bt3.insert(new IntElement(72))); // Schon vorhanden
		assertEquals(false, bt3.insert(new IntElement(100))); // Schon vorhanden
		assertEquals(0, bt3.getMin().compareTo(new IntElement(15))); // Ermittle Minimum
		assertEquals(0, bt3.getMax().compareTo(new IntElement(200))); // Ermittle Maximum

		// Tests der B-Tree Reihenfolge
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();

		// InOrder
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		bt3.printInOrder();
		String expectedOutputIO = "15 25 50 60 61 62 70 72 75 99 100 101 110 120 125 149 150 175 200 ";
		assertEquals(expectedOutputIO, outContent.toString()); // Test InOrder

		// PostOrder
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		bt3.printPostOrder();
		String expectedOutputPO = "15 25 50 60 62 70 75 99 61 72 101 110 125 149 175 200 120 150 100 ";
		assertEquals(expectedOutputPO, outContent.toString()); // Test PostOrder

		// PreOrder
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		bt3.printPreOrder();
		String expectedOutputPREO = "100 61 72 15 25 50 60 62 70 75 99 120 150 101 110 125 149 175 200 ";
		assertEquals(expectedOutputPREO, outContent.toString()); // Test PreOrder

		// LevelOrder
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		bt3.printLevelOrder();
		String expectedOutputLO = "100 61 72 120 150 15 25 50 60 62 70 75 99 101 110 125 149 175 200 ";
		assertEquals(expectedOutputLO, outContent.toString()); // Test LevelOrder

		// Weitere B-Tree Tests
		assertEquals(true, bt3.insert(fileWith20, "IntElement")); // File ist identisch, bis auf 1 verstecktes Element
		assertEquals(false, bt3.insert(fileWith20, "IntElement"));
		assertEquals(3, bt3.height()); // Höhe des Baums
		assertEquals(20, bt3.size()); // Anzahl der Elemente im Baum
		assertEquals(0, bt3.getMin().compareTo(new IntElement(15))); // Ermittle Minimum
		assertEquals(0, bt3.getMax().compareTo(new IntElement(315))); // Ermittle Maximum
		bt3.clear(); // Baum löschen
		assertEquals(0, bt3.height());
		assertEquals(0, bt3.size());
		assertEquals(true, bt3.isEmpty());
		assertEquals(true, bt3.insert(new IntElement(10)));
		assertEquals(1, bt3.height());
		assertEquals(1, bt3.size());
		assertEquals(false, bt3.isEmpty());
		assertEquals(false, bt3.insert(new IntElement(10)));
	}

	/*
	 * --------------- Tests insert() StringElement ---------------
	 */

	@Test
	public void testInsertStringElementWithSmallestDegreeFromFile() {
		// InsertFromFileTest
		assertEquals(true, bt5.isEmpty());
		assertEquals(0, bt5.height());
		assertEquals(0, bt5.size());
		String fileWith8 = MethodHandles.lookup().lookupClass().getResource("bTreeInput5.txt").getPath();
		String fileWith9 = MethodHandles.lookup().lookupClass().getResource("bTreeInput6.txt").getPath();
		assertEquals(true, bt5.insert(fileWith8, "StringElement"));
		assertEquals(false, bt5.insert(fileWith8, "StringElement")); // Kein zusätzliches Element wird eingefügt
		assertEquals(false, bt5.isEmpty());
		assertEquals(3, bt5.height()); // Höhe des Baums
		assertEquals(8, bt5.size()); // Anzahl der Elemente im Baum
		assertEquals(false, bt5.insert(new StringElement("M"))); // Schon vorhanden
		assertEquals(false, bt5.insert(new StringElement("F"))); // Schon vorhanden
		assertEquals(false, bt5.insert(new StringElement("X"))); // Schon vorhanden
		assertEquals(false, bt5.insert(new StringElement("B"))); // Schon vorhanden
		assertEquals(false, bt5.insert(new StringElement("T"))); // Schon vorhanden
		assertEquals(0, bt5.getMin().compareTo(new StringElement("B"))); // Ermittle Minimum
		assertEquals(0, bt5.getMax().compareTo(new StringElement("Y"))); // Ermittle Maximum

		// Tests der B-Tree Reihenfolge
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();

		// InOrder
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		bt5.printInOrder();
		String expectedOutputIO = "B D F M S T X Y ";
		assertEquals(expectedOutputIO, outContent.toString()); // Test InOrder

		// PostOrder
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		bt5.printPostOrder();
		String expectedOutputPO = "B F D S X Y T M ";
		assertEquals(expectedOutputPO, outContent.toString()); // Test PostOrder

		// PreOrder
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		bt5.printPreOrder();
		String expectedOutputPREO = "M D B F T S X Y ";
		assertEquals(expectedOutputPREO, outContent.toString()); // Test PreOrder

		// LevelOrder
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		bt5.printLevelOrder();
		String expectedOutputLO = "M D T B F S X Y ";
		assertEquals(expectedOutputLO, outContent.toString()); // Test LevelOrder

		// Weitere B-Tree Tests
		assertEquals(true, bt5.insert(fileWith9, "StringElement")); // File ist identisch, bis auf 1 verstecktes Element
		assertEquals(false, bt5.insert(fileWith9, "StringElement"));
		assertEquals(3, bt5.height()); // Höhe des Baums
		assertEquals(9, bt5.size()); // Anzahl der Elemente im Baum
		assertEquals(0, bt5.getMin().compareTo(new StringElement("B"))); // Ermittle Minimum
		assertEquals(0, bt5.getMax().compareTo(new StringElement("Y"))); // Ermittle Maximum
		bt5.clear(); // Baum löschen
		assertEquals(0, bt5.height());
		assertEquals(0, bt5.size());
		assertEquals(true, bt5.isEmpty());
		assertEquals(true, bt5.insert(new StringElement("A")));
		assertEquals(1, bt5.height());
		assertEquals(1, bt5.size());
		assertEquals(false, bt5.isEmpty());
		assertEquals(false, bt5.insert(new StringElement("A")));
	}

	/*
	 * --------------- Tests insert() Songs ---------------
	 */

	@Test
	public void testInsertSongsWithSmallestDegreeFromFile() {
		// InsertFromFileTest
		assertEquals(true, bt7.isEmpty());
		assertEquals(0, bt7.height());
		assertEquals(0, bt7.size());
		String songFile = MethodHandles.lookup().lookupClass().getResource("songs.txt").getPath();
		String songFileOneMore = MethodHandles.lookup().lookupClass().getResource("songs2.txt").getPath();
		assertEquals(true, bt7.insert(songFile, "Song"));
		assertEquals(false, bt7.insert(songFile, "Song")); // Kein zusätzliches Element wird eingefügt
		assertEquals(false, bt7.isEmpty());
		assertEquals(881, bt7.height()); // Höhe des Baums
		assertEquals(2928, bt7.size()); // Anzahl der Elemente im Baum

		// Weitere B-Tree Tests
		assertEquals(true, bt7.insert(songFileOneMore, "Song")); // File ist identisch, bis auf 1 verstecktes Element
		assertEquals(false, bt7.insert(songFileOneMore, "Song"));
		assertEquals(881, bt7.height()); // Höhe des Baums
		assertEquals(2929, bt7.size()); // Anzahl der Elemente im Baum
		bt7.clear(); // Baum löschen
		assertEquals(0, bt7.height());
		assertEquals(0, bt7.size());
		assertEquals(true, bt7.isEmpty());
	}

	/*
	 * --------------- Tests Remove ---------------
	 */

}