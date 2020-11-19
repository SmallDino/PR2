package pr2.uebung01;

import org.junit.Test;
import static org.junit.Assert.*;
import java.lang.invoke.MethodHandles;

public class DivSearchesTest {

	int[] F1 = { 6, 12, 18, 42, 44, 55, 55, 67, 94 };
	int[] F2 = { 6 };

	@Test
	public void searchMiddle() {
		assertEquals(6, DivSearches.binarySearch(F1, 55));
		assertEquals(6, DivSearches.binarySearchRec(F1, 55));
		assertEquals(5, DivSearches.sequentialSearchF(F1, 55));
		assertEquals(6, DivSearches.sequentialSearchL(F1, 55));
	}

	@Test
	public void searchFirst() {
		assertEquals(0, DivSearches.binarySearch(F1, 6));
		assertEquals(0, DivSearches.binarySearchRec(F1, 6));
		assertEquals(0, DivSearches.sequentialSearchF(F1, 6));
		assertEquals(0, DivSearches.sequentialSearchL(F1, 6));
	}

	@Test
	public void searchLast() {
		assertEquals(8, DivSearches.binarySearch(F1, 94));
		assertEquals(8, DivSearches.binarySearchRec(F1, 94));
		assertEquals(8, DivSearches.sequentialSearchF(F1, 94));
		assertEquals(8, DivSearches.sequentialSearchL(F1, 94));
	}

	@Test
	public void searchInOne() {
		assertEquals(0, DivSearches.binarySearch(F1, 6));
		assertEquals(0, DivSearches.binarySearchRec(F1, 6));
		assertEquals(0, DivSearches.sequentialSearchF(F1, 6));
		assertEquals(0, DivSearches.sequentialSearchL(F1, 6));
	}

}