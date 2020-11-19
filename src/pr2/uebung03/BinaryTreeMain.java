package pr2.uebung03;

//import static pr.MakeItSimple.*;
import java.util.*;

public class BinaryTreeMain {

	public static final int MENU_ELEMENT_CHOICE_INTEGER = 1;
	public static final int MENU_ELEMENT_CHOICE_STRING = 2;
	public static final int MENU_ELEMENT_CHOICE_INSERT_VAL = 1;
	public static final int MENU_ELEMENT_CHOICE_INSERT = 2;
	public static final int MENU_ELEMENT_CHOICE_CONTAINS = 3;
	public static final int MENU_ELEMENT_CHOICE_SIZE = 4;
	public static final int MENU_ELEMENT_CHOICE_HEIGHT = 5;
	public static final int MENU_ELEMENT_CHOICE_GETMAX = 6;
	public static final int MENU_ELEMENT_CHOICE_GETMIN = 7;
	public static final int MENU_ELEMENT_CHOICE_REMOVE = 8;
	public static final int MENU_ELEMENT_CHOICE_ISEMPTY = 9;
	public static final int MENU_ELEMENT_CHOICE_CLEAR = 10;
	public static final int MENU_ELEMENT_CHOICE_ADDALL = 11;
	public static final int MENU_ELEMENT_CHOICE_INORDER = 12;
	public static final int MENU_ELEMENT_CHOICE_POSTORDER = 13;
	public static final int MENU_ELEMENT_CHOICE_PREORDER = 14;
	public static final int MENU_ELEMENT_CHOICE_LEVELORDER = 15;
	public static final int MENU_ELEMENT_CHOICE_SAVETOFILE = 16;
	public static final int MENU_ELEMENT_CHOICE_CLONE = 17;
	public static final int MENU_ELEMENT_CHOICE_EQUAL = 18;
	public static final int MENU_ELEMENT_CHOICE_EQUALS = 19;
	public static final int MENU_ELEMENT_CHOICE_VISUALIZE = 20;
	public static final int MENU_ELEMENT_QUIT_SUBMENU = 21;
	public static final int MENU_ELEMENT_QUIT_MENU = 3;

	public static void main(String[] args) {
		// Variablen deklarieren
		boolean leave = false;
		int input;
		Scanner in = new Scanner(System.in);
		MyBinaryTree treeInt = new MyBinaryTree();
		MyBinaryTree treeInt2 = new MyBinaryTree();
		MyBinaryTree treeString = new MyBinaryTree();
		MyBinaryTree treeString2 = new MyBinaryTree();

		do {
			// Auswahlmenü
			System.out.println("Welchen Datentyp wollen Sie zu Ihrem Binären-Baum hinzufügen?");
			System.out.println("1: Integer-Wert");
			System.out.println("2: String-Wert");
			System.out.println("3: Programm beenden");
			leave = false;

			input = in.nextInt();
			if (input == 1) {
				do {
					switch (input) {

					case MENU_ELEMENT_CHOICE_INTEGER:
						System.out.println("1: insert Element val");
						System.out.println("2: insert File");
						System.out.println("3: tree contains val");
						System.out.println("4: size");
						System.out.println("5: height");
						System.out.println("6: max. Element");
						System.out.println("7: min. Element");
						System.out.println("8: remove");
						System.out.println("9: isempty");
						System.out.println("10: clear");
						System.out.println("11: addAll");
						System.out.println("12: Inorder");
						System.out.println("13: Postorder");
						System.out.println("14: Preorder");
						System.out.println("15: Levelorder");
						System.out.println("16: saveToFile");
						System.out.println("17: clone");
						System.out.println("18: equal");
						System.out.println("19: equals");
						System.out.println("20: visualize");
						System.out.println("21: end");
						int a = in.nextInt();
						switch (a) {
						case MENU_ELEMENT_CHOICE_INSERT_VAL:
							System.out.println("Wert eingeben: ");
							int elem = in.nextInt();
							Element num = new IntElement(elem);
							System.out.println(treeInt.insert(num));
							break;

						case MENU_ELEMENT_CHOICE_INSERT:
							System.out.println("Bitte Datei eingeben: ");
							String fileInt = in.nextLine();
							System.out.println(treeInt.insert(fileInt));
							break;

						case MENU_ELEMENT_CHOICE_CONTAINS:
							System.out.println("Wert eingeben: ");
							int elem2 = in.nextInt();
							Element num2 = new IntElement(elem2);
							System.out.println(treeInt.contains(num2));
							break;

						case MENU_ELEMENT_CHOICE_SIZE:
							System.out.println(treeInt.size());
							break;

						case MENU_ELEMENT_CHOICE_HEIGHT:
							System.out.println(treeInt.height());
							break;

						case MENU_ELEMENT_CHOICE_GETMAX:
							System.out.println(treeInt.getMax());
							break;

						case MENU_ELEMENT_CHOICE_GETMIN:
							System.out.println(treeInt.getMin());
							break;

						case MENU_ELEMENT_CHOICE_REMOVE:
							System.out.println("Wert eingeben: ");
							int elem3 = in.nextInt();
							Element num3 = new IntElement(elem3);
							System.out.println(treeInt.remove(num3));
							break;

						case MENU_ELEMENT_CHOICE_ISEMPTY:
							System.out.println(treeInt.isEmpty());
							break;

						case MENU_ELEMENT_CHOICE_CLEAR:
							treeInt.clear();
							break;
						case MENU_ELEMENT_CHOICE_ADDALL:
							treeInt.addAll(treeInt2);
							break;

						case MENU_ELEMENT_CHOICE_INORDER:
							treeInt.printInorder();
							System.out.println();
							break;

						case MENU_ELEMENT_CHOICE_POSTORDER:
							treeInt.printPostorder();
							System.out.println();
							break;

						case MENU_ELEMENT_CHOICE_PREORDER:
							treeInt.printPreorder();
							System.out.println();
							break;

						case MENU_ELEMENT_CHOICE_LEVELORDER:
							treeInt.printLevelorder();
							System.out.println();
							break;

						case MENU_ELEMENT_CHOICE_SAVETOFILE:
							System.out.println("Bitte Datei eingeben: ");
							String outfileInt = in.nextLine();
							System.out.println(treeInt.saveToFile(outfileInt));
							break;

						case MENU_ELEMENT_CHOICE_CLONE:
							treeInt.clone();
							break;

						case MENU_ELEMENT_CHOICE_EQUAL:
							System.out.println(treeInt.equal(treeInt2));
							break;

						case MENU_ELEMENT_CHOICE_EQUALS:
							System.out.println(treeInt.equals((MyBinaryTree) treeInt2));
							break;

						case MENU_ELEMENT_CHOICE_VISUALIZE:
//							treeInt.visualize();
							break;
						case MENU_ELEMENT_QUIT_SUBMENU:
							leave = true;
							System.out.println("Untermenü Beendet");
							break;

						default:
							System.err.println("Unzulässige Eingabe");
						}
					}
				} while (!leave);
			} else if (input == 2) {
				do {
					switch (input) {
					case MENU_ELEMENT_CHOICE_STRING:
						System.out.println("1: insert Element val");
						System.out.println("2: insert File");
						System.out.println("3: tree contains val");
						System.out.println("4: size");
						System.out.println("5: height");
						System.out.println("6: max. Element");
						System.out.println("7: min. Element");
						System.out.println("8: remove");
						System.out.println("9: isempty");
						System.out.println("10: clear");
						System.out.println("11: addAll");
						System.out.println("12: Inorder");
						System.out.println("13: Postorder");
						System.out.println("14: Preorder");
						System.out.println("15: Levelorder");
						System.out.println("16: saveToFile");
						System.out.println("17: clone");
						System.out.println("18: equal");
						System.out.println("19: equals");
						System.out.println("20: visualize");
						System.out.println("21: end");
						int a = in.nextInt();
						switch (a) {
						case MENU_ELEMENT_CHOICE_INSERT_VAL:
							System.out.println("Wert eingeben: ");
							String elem = in.nextLine();
							Element num = new StringElement(elem);
							System.out.println(treeString.insert(num));
							break;

						case MENU_ELEMENT_CHOICE_INSERT:
							System.out.println("Bitte Datei eingeben: ");
							String fileInt = in.nextLine();
							System.out.println(treeString.insert(fileInt));
							break;

						case MENU_ELEMENT_CHOICE_CONTAINS:
							System.out.println("Wert eingeben: ");
							String elem2 = in.nextLine();
							Element num2 = new StringElement(elem2);
							System.out.println(treeString.contains(num2));
							break;

						case MENU_ELEMENT_CHOICE_SIZE:
							System.out.println(treeString.size());
							break;

						case MENU_ELEMENT_CHOICE_HEIGHT:
							System.out.println(treeString.height());
							break;

						case MENU_ELEMENT_CHOICE_GETMAX:
							System.out.println(treeString.getMax());
							break;

						case MENU_ELEMENT_CHOICE_GETMIN:
							System.out.println(treeString.getMin());
							break;

						case MENU_ELEMENT_CHOICE_REMOVE:
							System.out.println("Wert eingeben: ");
							String elem3 = in.nextLine();
							Element num3 = new StringElement(elem3);
							System.out.println(treeString.remove(num3));
							break;

						case MENU_ELEMENT_CHOICE_ISEMPTY:
							System.out.println(treeString.isEmpty());
							break;

						case MENU_ELEMENT_CHOICE_CLEAR:
							treeString.clear();
							break;
						case MENU_ELEMENT_CHOICE_ADDALL:
							treeString.addAll(treeString2);
							break;

						case MENU_ELEMENT_CHOICE_INORDER:
							treeString.printInorder();
							System.out.println();
							break;

						case MENU_ELEMENT_CHOICE_POSTORDER:
							treeString.printPostorder();
							System.out.println();
							break;

						case MENU_ELEMENT_CHOICE_PREORDER:
							treeString.printPreorder();
							System.out.println();
							break;

						case MENU_ELEMENT_CHOICE_LEVELORDER:
							treeString.printLevelorder();
							System.out.println();
							break;

						case MENU_ELEMENT_CHOICE_SAVETOFILE:
							System.out.println("Bitte Datei eingeben: ");
							String outfileInt = in.nextLine();
							System.out.println(treeString.saveToFile(outfileInt));
							break;

						case MENU_ELEMENT_CHOICE_CLONE:
							treeString.clone();
							break;

						case MENU_ELEMENT_CHOICE_EQUAL:
							System.out.println(treeString.equal(treeString2));
							break;

						case MENU_ELEMENT_CHOICE_EQUALS:
							System.out.println(treeString.equals((MyBinaryTree) treeString2));
							break;

						case MENU_ELEMENT_CHOICE_VISUALIZE:
//							treeString.visualize();
							break;
						case MENU_ELEMENT_QUIT_SUBMENU:
							leave = true;
							System.out.println("Untermenü Beendet");
							break;

						default:
							System.err.println("Unzulässige Eingabe");
						}
					}
				} while (!leave);
			}
		} while (input != MENU_ELEMENT_QUIT_MENU);
		in.close();
		System.out.println(">>> Programm beendet");
	}
}