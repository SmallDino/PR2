package pr2.uebung03;

import org.junit.Test;

import static org.junit.Assert.*;

import java.lang.invoke.MethodHandles;

public class BinaryTreeTest {

	private BinaryTree tree1 = new MyBinaryTree();
	private BinaryTree tree2 = new MyBinaryTree();
	private MyBinaryTree tree3 = new MyBinaryTree();
	String filename = MethodHandles.lookup().lookupClass().getResource("treeInput.txt").getPath();

	@Test
	public void demoDatei() throws Exception {
		MethodHandles.lookup().lookupClass().getResource("treeInput.txt").getPath();
		assertEquals(true, tree1.insert(filename));
		assertEquals(false, tree1.insert(filename));
		assertEquals(16, tree1.size());
		assertEquals(6, tree1.height());
		assertEquals(0, tree1.getMax().compareTo(new IntElement(80)));
		assertEquals(0, tree1.getMin().compareTo(new IntElement(1)));
		assertEquals(false, tree1.remove(new IntElement(31)));
		assertEquals(false, tree1.isEmpty());
		assertEquals(false, tree1.contains(new IntElement(3)));
		assertEquals(true, tree1.contains(new IntElement(30)));
		tree3 = (MyBinaryTree) tree1.clone();

		tree3.remove(new IntElement(10));
		assertEquals(false, tree3.contains(new IntElement(10)));
		assertEquals(true, tree1.contains(new IntElement(10)));

		tree1.remove(new IntElement(40));
		assertEquals(15, tree1.size());
		assertEquals(6, tree1.height());

		tree1.remove(new IntElement(30));
		assertEquals(14, tree1.size());
		assertEquals(5, tree1.height());

		tree1.remove(new IntElement(35));
		assertEquals(13, tree1.size());
		assertEquals(5, tree1.height());

		tree1.clear();
		assertEquals(0, tree1.size());

		tree1.insert(filename);
		tree2 = (BinaryTree) tree1.clone();
		tree1.addAll(tree2);
		assertEquals(tree1.size(), tree2.size());

	}

	@Test
	public void testsOnEmpty() {
		assertEquals(0, tree1.size());
		assertEquals(0, tree1.height());
		assertEquals(null, tree1.getMax());
		assertEquals(null, tree1.getMin());
		assertEquals(false, tree1.remove(new IntElement(1)));
		assertEquals(true, tree1.isEmpty());
		assertEquals(false, tree1.contains(new IntElement(1)));
		assertEquals(true, ((BinaryTree) tree1.clone()).isEmpty());
	}

	@Test
	public void insertOnlyOneElement() {
		tree1.insert(new IntElement(10));
		assertEquals(1, tree1.size());
		assertEquals(1, tree1.height());
		assertEquals(0, tree1.getMax().compareTo(new IntElement(10)));
		assertEquals(0, tree1.getMin().compareTo(new IntElement(10)));
		assertEquals(false, tree1.remove(new IntElement(1)));
		assertEquals(false, tree1.isEmpty());
		assertEquals(false, tree1.contains(new IntElement(1)));
		assertEquals(true, tree1.contains(new IntElement(10)));
		assertEquals(false, ((BinaryTree) tree1.clone()).isEmpty());
		assertEquals(true, tree1.remove(new IntElement(10)));
		assertEquals(true, tree1.isEmpty());
		assertEquals(false, tree1.contains(new IntElement(10)));
		assertEquals(true, ((BinaryTree) tree1.clone()).isEmpty());
	}

	
	@Test
	public void demoDateiStrings() throws Exception {
		String filename = MethodHandles.lookup().lookupClass().getResource("treeInputStrings.txt").getPath();
		tree1.insert(new StringElement("Hans")); // damit die Tests klappen
		assertEquals(true, tree1.insert(filename));
		assertEquals(false, tree1.insert(filename));
		assertEquals(16, tree1.size());
		assertEquals(6, tree1.height());
		assertEquals(0, tree1.getMax().compareTo(new StringElement("Willy")));
		assertEquals(0, tree1.getMin().compareTo(new StringElement("Andrea")));
		assertEquals(false, tree1.remove(new StringElement("Willi")));
		assertEquals(false, tree1.isEmpty());
		assertEquals(false, tree1.contains(new StringElement("Willibald")));
		assertEquals(true, tree1.contains(new StringElement("Lothar")));
		tree3 = (MyBinaryTree) tree1.clone();

		tree3.remove(new StringElement("Hans"));
		assertEquals(false, tree3.contains(new StringElement("Hans")));
		assertEquals(true, tree1.contains(new StringElement("Hans")));

		tree1.remove(new StringElement("Rolf"));
		assertEquals(15, tree1.size());
		assertEquals(6, tree1.height());

		tree1.remove(new StringElement("Norbert"));
		assertEquals(14, tree1.size());
		assertEquals(5, tree1.height());

		tree1.remove(new StringElement("Paul"));
		assertEquals(13, tree1.size());
		assertEquals(5, tree1.height());

		tree1.clear();
		assertEquals(0, tree1.size());

		tree1.insert(filename);
		tree2 = (BinaryTree) tree1.clone();
		tree1.addAll(tree2);
		assertEquals(tree1.size(), tree2.size());

	}
	

	@Test
	public void testsOnEmptyStrings() {
		assertEquals(0, tree1.size());
		assertEquals(0, tree1.height());
		assertEquals(null, tree1.getMax());
		assertEquals(null, tree1.getMin());
		assertEquals(false, tree1.remove(new StringElement("Hans")));
		assertEquals(true, tree1.isEmpty());
		assertEquals(false, tree1.contains(new StringElement("Hans")));
		assertEquals(true, ((BinaryTree) tree1.clone()).isEmpty());
	}

	@Test
	public void insertOnlyOneElementStrings() {
		tree1.insert(new StringElement("Hans"));
		assertEquals(1, tree1.size());
		assertEquals(1, tree1.height());
		assertEquals(0, tree1.getMax().compareTo(new StringElement("Hans")));
		assertEquals(0, tree1.getMin().compareTo(new StringElement("Hans")));
		assertEquals(false, tree1.remove(new StringElement("Hansi")));
		assertEquals(false, tree1.isEmpty());
		assertEquals(false, tree1.contains(new StringElement("Hansi")));
		assertEquals(true, tree1.contains(new StringElement("Hans")));
		assertEquals(false, ((BinaryTree) tree1.clone()).isEmpty());
		assertEquals(true, tree1.remove(new StringElement("Hans")));
		assertEquals(true, tree1.isEmpty());
		assertEquals(false, tree1.contains(new StringElement("Hans")));
		assertEquals(true, ((BinaryTree) tree1.clone()).isEmpty());
	}

	@Test
	public void test2TreesOnEquals() {

		String filename = MethodHandles.lookup().lookupClass().getResource("treeInput.txt").getPath();
		String filename3 = MethodHandles.lookup().lookupClass().getResource("treeInput3.txt").getPath();
		String filename4 = MethodHandles.lookup().lookupClass().getResource("treeInput4.txt").getPath();
		String filename5 = MethodHandles.lookup().lookupClass().getResource("treeInput5.txt").getPath();
		assertEquals(true, tree1.insert(filename));
		assertEquals(true, tree2.insert(filename));
		assertEquals(true, tree1.equals(tree2));
		assertEquals(true, tree1.remove(new IntElement(10)));
		assertEquals(true, tree2.remove(new IntElement(10)));
		assertEquals(true, tree1.equals(tree2));
		tree1.clear();
		tree2.clear();
		assertEquals(true, tree1.equals(tree2));
		assertEquals(true, tree1.equal(tree2));

		tree1.clear();
		tree2.clear();
		assertEquals(true, tree1.insert(filename));
		assertEquals(true, tree2.insert(filename3));
		assertEquals(false, tree1.equals(tree2));

		assertEquals(true, tree1.equal(tree2));

		tree1.clear();
		tree2.clear();
		assertEquals(true, tree1.insert(filename4));
		assertEquals(true, tree2.insert(filename5));
		assertEquals(true, tree1.equal(tree2));

	}
}