package pr2.uebung02;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import java.lang.invoke.MethodHandles;

public class TestAllSorts {
	
	int[] F1;
	int[] F2;
	int[] F3;
	StatObj so;
	

	@Before
	public void prepareTest() {
		F1 = new int[] { 10, 4, 33, 44, 17, 20, 3, 2, 9, 82, 38, 67, 55, 11, 32, 23, 19, 7, 6, 14, 29 };
		F2 = new int[] { 6 };
		F3 = new int[] {};

		so = new StatObj();
	}

	// ShakerSort
	@Test
	public void testShakerSort1() {
		SortInterface sm = new ShakerSort();
		sm.sort(F1, so);
		assertThat(F1, is(new int[] { 2, 3, 4, 6, 7, 9, 10, 11, 14, 17, 19, 20, 23, 29, 32, 33, 38, 44, 55, 67, 82 }));

	}
	
	@Test
	public void testShakerSort2() {
		SortInterface sm = new ShakerSort();
		sm.sort(F2, so);
		assertThat(F2, is(new int[] { 6 }));

	}
	
	@Test
	public void testShakerSort3() {
		SortInterface sm = new ShakerSort();
		sm.sort(F3, so);
		assertThat(F3, is(new int[] {}));

	}
	
	// InsertionSort
	@Test
	public void testInsertion1() {
		SortInterface sm = new InsertionSort();
		sm.sort(F1, so);
		assertThat(F1, is(new int[] { 2, 3, 4, 6, 7, 9, 10, 11, 14, 17, 19, 20, 23, 29, 32, 33, 38, 44, 55, 67, 82 }));

	}
	
	@Test
	public void testInsertion2() {
		SortInterface sm = new InsertionSort();
		sm.sort(F2, so);
		assertThat(F2, is(new int[] { 6 }));

	}
	
	@Test
	public void testInsertion3() {
		SortInterface sm = new InsertionSort();
		sm.sort(F3, so);
		assertThat(F3, is(new int[] {}));

	}	

	// QuickSort
	@Test
	public void testQuick1() {
		SortInterface sm = new QuickSort();
		sm.sort(F1, so);
		assertThat(F1, is(new int[] { 2, 3, 4, 6, 7, 9, 10, 11, 14, 17, 19, 20, 23, 29, 32, 33, 38, 44, 55, 67, 82 }));

	}
	
	@Test
	public void testQuick2() {
		SortInterface sm = new QuickSort();
		sm.sort(F2, so);
		assertThat(F2, is(new int[] { 6 }));

	}
	
	@Test
	public void testQuick3() {
		SortInterface sm = new QuickSort();
		sm.sort(F3, so);
		assertThat(F3, is(new int[] {}));

	}


	// ShellSort
	@Test
	public void testShellSort1() {
		SortInterface sm = new ShellSort();
		sm.sort(F1, so);
		assertThat(F1, is(new int[] { 2, 3, 4, 6, 7, 9, 10, 11, 14, 17, 19, 20, 23, 29, 32, 33, 38, 44, 55, 67, 82 }));

	}
	
	@Test
	public void testShellSort2() {
		SortInterface sm = new ShellSort();
		sm.sort(F2, so);
		assertThat(F2, is(new int[] { 6 }));

	}
	
	@Test
	public void testShellSort3() {
		SortInterface sm = new ShellSort();
		sm.sort(F3, so);
		assertThat(F3, is(new int[] {}));

	}
}