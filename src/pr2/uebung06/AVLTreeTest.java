package pr2.uebung06;

import org.junit.Test;

import pr2.uebung04.IntElement;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.invoke.MethodHandles;

public class AVLTreeTest {

	private AVLTreeI tree1 = new AVLTreeI();

	// Tests mit Int-Elementen

	@Test
	public void testsWithSmallTree1() {
		
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		// PrintStream oldOut = System.out;
		System.setOut(new PrintStream(outContent));

		tree1.insert(new IntElement(10));
		tree1.insert(new IntElement(7));
		tree1.insert(new IntElement(5));

		tree1.printPostorder();
		String expectedOutputPO1 = "5(0) 10(0) 7(0) ";

		assertEquals(expectedOutputPO1, outContent.toString()); // Test Postorder
	}
	
	@Test
	public void testsWithSmallTree2() {
		
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		// PrintStream oldOut = System.out;
		System.setOut(new PrintStream(outContent));

		tree1.insert(new IntElement(10));
		tree1.insert(new IntElement(5));
		tree1.insert(new IntElement(7));

		tree1.printPostorder();
		String expectedOutputPO1 = "5(0) 10(0) 7(0) ";

		assertEquals(expectedOutputPO1, outContent.toString()); // Test Postorder

	}
	
	@Test
	public void testsWithSmallTree3() {
		
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		// PrintStream oldOut = System.out;
		System.setOut(new PrintStream(outContent));

		tree1.insert(new IntElement(5));
		tree1.insert(new IntElement(7));
		tree1.insert(new IntElement(10));

		tree1.printPostorder();
		String expectedOutputPO1 = "5(0) 10(0) 7(0) ";

		assertEquals(expectedOutputPO1, outContent.toString()); // Test Postorder
	}
	
	@Test
	public void testsWithSmallTree4() {
		
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		// PrintStream oldOut = System.out;
		System.setOut(new PrintStream(outContent));

		tree1.insert(new IntElement(5));
		tree1.insert(new IntElement(10));
		tree1.insert(new IntElement(7));

		tree1.printPostorder();
		String expectedOutputPO1 = "5(0) 10(0) 7(0) ";

		assertEquals(expectedOutputPO1, outContent.toString()); // Test Postorder
	}
	
	@Test
	public void testsWith9Values() { // throws Exception {


		String filename = MethodHandles.lookup().lookupClass().getResource("treeInput2.txt").getPath();

		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		// PrintStream oldOut = System.out;
		System.setOut(new PrintStream(outContent));

		// After this all System.out.println() statements will come to
		// outContent stream.

		// Ausgabe der Werte des Baums von Konsole auf outContent umgeleitet

		tree1.insert(filename, "IntElement");

		tree1.printPostorder();
		String expectedOutputPO = "5(0) 7(1) 9(0) 13(0) 10(0) 30(0) 20(-1) 15(0) 8(-1) ";

		assertEquals(expectedOutputPO, outContent.toString()); // Test Postorder

		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));

		tree1.printInorder();
		String expectedOutputIO = "5(0) 7(1) 8(-1) 9(0) 10(0) 13(0) 15(0) 20(-1) 30(0) ";

		assertEquals(expectedOutputIO, outContent.toString()); // Test Inorder

		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));

		tree1.printPreorder();
		String expectedOutputPreO = "8(-1) 7(1) 5(0) 15(0) 10(0) 9(0) 13(0) 20(-1) 30(0) ";

		assertEquals(expectedOutputPreO, outContent.toString()); // Test Preorder

		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));

	}

	@Test
	public void testsWith16Values() { // throws Exception {

		String filename = MethodHandles.lookup().lookupClass().getResource("treeInput1.txt").getPath();

		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		// PrintStream oldOut = System.out;
		System.setOut(new PrintStream(outContent));

		// After this all System.out.println() statements will come to
		// outContent stream.

		// Ausgabe der Werte des Baums von Konsole auf outContent umgeleitet

		tree1.insert(filename, "IntElement");

		tree1.printPostorder();
		String expectedOutputPO1 = "1(0) 8(0) 5(0) 12(0) 18(0) 15(0) 10(0) 30(0) 38(0) 35(-1) 33(-1) 50(0) 80(0) 60(0) 40(1) 20(-1) ";

		assertEquals(expectedOutputPO1, outContent.toString()); // Test Postorder
		
        
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

		tree1.remove(new IntElement(20));   // delete root

		tree1.printPostorder();
		String expectedOutputPO2 = "1(0) 8(0) 5(0) 12(0) 18(0) 15(0) 10(0) 33(0) 38(0) 35(0) 50(0) 80(0) 60(0) 40(0) 30(0) ";

		assertEquals(expectedOutputPO2, outContent.toString()); // Test Postorder

	}
	
	@Test
	public void testsWith17Values() { // throws Exception {


		String filename = MethodHandles.lookup().lookupClass().getResource("treeInput3.txt").getPath();

		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		// PrintStream oldOut = System.out;
		System.setOut(new PrintStream(outContent));

		// After this all System.out.println() statements will come to
		// outContent stream.

		// Ausgabe der Werte des Baums von Konsole auf outContent umgeleitet

		tree1.insert(filename, "IntElement");

		tree1.printPostorder();
		String expectedOutputPO = "4(0) 3(-1) 7(0) 5(1) 9(0) 13(0) 10(0) 18(0) 23(0) 20(0) 15(0) 33(0) 30(-1) 40(0) 35(1) 25(0) 8(-1) ";

		assertEquals(expectedOutputPO, outContent.toString()); // Test Postorder

		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));

		tree1.printInorder();
		String expectedOutputIO = "3(-1) 4(0) 5(1) 7(0) 8(-1) 9(0) 10(0) 13(0) 15(0) 18(0) 20(0) 23(0) 25(0) 30(-1) 33(0) 35(1) 40(0) ";

		assertEquals(expectedOutputIO, outContent.toString()); // Test Inorder

		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));

		tree1.printPreorder();
		String expectedOutputPreO = "8(-1) 5(1) 3(-1) 4(0) 7(0) 25(0) 15(0) 10(0) 9(0) 13(0) 20(0) 18(0) 23(0) 35(1) 30(-1) 33(0) 40(0) ";

		assertEquals(expectedOutputPreO, outContent.toString()); // Test Preorder

		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));

	}
	
	@Test
	public void testsWith17ValuesRemoves() { // throws Exception {


		String filename = MethodHandles.lookup().lookupClass().getResource("treeInput3.txt").getPath();

		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		// PrintStream oldOut = System.out;
		System.setOut(new PrintStream(outContent));

		// After this all System.out.println() statements will come to
		// outContent stream.

		// Ausgabe der Werte des Baums von Konsole auf outContent umgeleitet

		tree1.insert(filename, "IntElement");

		tree1.printPostorder();
		String expectedOutputPO = "4(0) 3(-1) 7(0) 5(1) 9(0) 13(0) 10(0) 18(0) 23(0) 20(0) 15(0) 33(0) 30(-1) 40(0) 35(1) 25(0) 8(-1) ";

		assertEquals(expectedOutputPO, outContent.toString()); // Test Postorder

		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));

		tree1.remove(new IntElement(8));
		tree1.printPostorder();
		expectedOutputPO = "4(0) 3(-1) 7(0) 5(1) 13(0) 10(-1) 18(0) 23(0) 20(0) 15(0) 33(0) 30(-1) 40(0) 35(1) 25(0) 9(-1) ";
		assertEquals(expectedOutputPO, outContent.toString()); // Test Postorder
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		
		tree1.remove(new IntElement(25));
		tree1.printPostorder();
		expectedOutputPO = "4(0) 3(-1) 7(0) 5(1) 13(0) 10(-1) 18(0) 23(0) 20(0) 15(0) 33(0) 40(0) 35(0) 30(1) 9(-1) ";
		assertEquals(expectedOutputPO, outContent.toString()); // Test Postorder
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		
		tree1.remove(new IntElement(35)); 	
		tree1.printPostorder();
		expectedOutputPO = "4(0) 3(-1) 7(0) 5(1) 13(0) 10(-1) 18(0) 23(0) 20(0) 15(0) 33(0) 40(1) 30(1) 9(-1) ";
		assertEquals(expectedOutputPO, outContent.toString()); // Test Postorder
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		
		tree1.remove(new IntElement(15));
		tree1.printPostorder();
		expectedOutputPO = "4(0) 3(-1) 7(0) 5(1) 13(0) 10(-1) 23(0) 20(-1) 18(0) 33(0) 40(1) 30(1) 9(-1) ";
		assertEquals(expectedOutputPO, outContent.toString()); // Test Postorder
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		
		tree1.remove(new IntElement(30));		
		tree1.printPostorder();
		expectedOutputPO = "4(0) 3(-1) 7(0) 5(1) 13(0) 10(-1) 23(0) 20(-1) 40(0) 33(1) 18(-1) 9(-1) ";
		assertEquals(expectedOutputPO, outContent.toString()); // Test Postorder
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		
		tree1.remove(new IntElement(4));
		tree1.printPostorder();
		expectedOutputPO = "3(0) 7(0) 5(0) 13(0) 10(-1) 9(0) 23(0) 20(-1) 40(0) 33(1) 18(0) ";
		assertEquals(expectedOutputPO, outContent.toString()); // Test Postorder
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		
		tree1.remove(new IntElement(40));
		tree1.printPostorder();
		expectedOutputPO = "3(0) 7(0) 5(0) 13(0) 10(-1) 9(0) 20(0) 33(0) 23(0) 18(1) ";
		assertEquals(expectedOutputPO, outContent.toString()); // Test Postorder
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		
		tree1.remove(new IntElement(5));
		tree1.printPostorder();
		expectedOutputPO = "3(0) 7(1) 13(0) 10(-1) 9(0) 20(0) 33(0) 23(0) 18(1) ";
		assertEquals(expectedOutputPO, outContent.toString()); // Test Postorder
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));

		tree1.remove(new IntElement(3));
		tree1.printPostorder();
		expectedOutputPO = "7(0) 13(0) 10(-1) 9(-1) 20(0) 33(0) 23(0) 18(1) ";
		assertEquals(expectedOutputPO, outContent.toString()); // Test Postorder
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));

		tree1.remove(new IntElement(23));
		tree1.printPostorder();
		expectedOutputPO = "7(0) 13(0) 10(-1) 9(-1) 20(0) 33(1) 18(1) ";
		assertEquals(expectedOutputPO, outContent.toString()); // Test Postorder
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));

		tree1.remove(new IntElement(7));
		tree1.printPostorder();
		expectedOutputPO = "9(0) 13(0) 10(0) 20(0) 33(1) 18(0) ";
		assertEquals(expectedOutputPO, outContent.toString()); // Test Postorder
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));

		tree1.remove(new IntElement(9));
		tree1.printPostorder();
		expectedOutputPO = "13(0) 10(-1) 20(0) 33(1) 18(0) ";
		assertEquals(expectedOutputPO, outContent.toString()); // Test Postorder
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));

		tree1.remove(new IntElement(10));
		tree1.printPostorder();
		expectedOutputPO = "13(0) 20(0) 33(1) 18(-1) ";
		assertEquals(expectedOutputPO, outContent.toString()); // Test Postorder
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));

		tree1.remove(new IntElement(13));
		tree1.printPostorder();
		expectedOutputPO = "18(0) 33(0) 20(0) ";
		assertEquals(expectedOutputPO, outContent.toString()); // Test Postorder
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));

		tree1.remove(new IntElement(18));
		tree1.printPostorder();
		expectedOutputPO = "33(0) 20(-1) ";
		assertEquals(expectedOutputPO, outContent.toString()); // Test Postorder
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));

		tree1.remove(new IntElement(20));
		tree1.printPostorder();
		expectedOutputPO = "33(0) ";
		assertEquals(expectedOutputPO, outContent.toString()); // Test Postorder
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));

		tree1.remove(new IntElement(33));
		tree1.printPostorder();
		expectedOutputPO = "";
		assertEquals(expectedOutputPO, outContent.toString()); // Test Postorder
	}
}