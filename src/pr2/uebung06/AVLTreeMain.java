package pr2.uebung06;

import java.util.Scanner;
import pr2.uebung04.IntElement;

public class AVLTreeMain {
		
	public static void main(String[] args) {
		
		int input;
		boolean leave = false;
		
		AVLTreeI tree1 = new AVLTreeI();
		
		// Tastatur
		Scanner in = new Scanner(System.in);
		
		// Menüauswahl
		do {
			System.out.println("-- Menü --");
			System.out.println("[1] AVL Baum erstellen");
			System.out.println("[2] Beenden");
			System.out.print("Option:  ");
			input = in.nextInt();
					
			if(input == 1) {
				do {
					System.out.println("\n-- Untermanü --");
					System.out.println("[1] insert (Comparable)");
					System.out.println("[2] insert(String File)");
					System.out.println("[3] contains");
					System.out.println("[4] size");
					System.out.println("[5] height");
					System.out.println("[6] getMax");
					System.out.println("[7] getMin");
					System.out.println("[8] remove"); 
					System.out.println("[9] isEmpty");
					System.out.println("[10] clear");
					System.out.println("[11] print Inorder");
					System.out.println("[12] print Postorder");
					System.out.println("[13] print Preorder");
					System.out.println("[14] visualize");
					System.out.println("[15] Beenden");
					System.out.print("Option:  ");
					int secondInput = in.nextInt();
					
					switch(secondInput) {
					case 1:
						System.out.println("Bitte Wert eingeben: ");
						int elem = in.nextInt();
						IntElement elemInt = new IntElement(elem); 
						tree1.insert(elemInt);
						System.out.println("Wert wurde eingefügt");
						break;
					case 2:
						System.out.print("Bitte absoluten Pfad angeben: ");
						String path = in.next();
						tree1.insert(path, "IntElement");
						System.out.println("Datei wurde eingelesen");
						break;
					case 3:
						System.out.println("Bitte Wert eingeben: ");
						int containedValue = in.nextInt();
						IntElement containedValueInt = new IntElement(containedValue);
						System.out.println(tree1.contains(containedValueInt));
						break;
					case 4:
						System.out.println("Anzahl der Knoten: " + tree1.size());
						break;
					case 5:
						System.out.println("Höhe des Baums: " + tree1.height());
						break;
					case 6:
						System.out.println("Größtes Element: " + tree1.getMax());
						 break;
					case 7:
						System.out.println("Kleinstes Element: " + tree1.getMin());
						break;
					case 8:
						System.out.println("Zu entfernenden Wert eingeben:");
						int removeVal = in.nextInt();
						IntElement removeValInt = new IntElement(removeVal);
						tree1.remove(removeValInt);
						break;
					case 9:
						System.out.println("Ist Baum leer? " + tree1.isEmpty());
						break;
					case 10:
						tree1.clear();
						System.out.println("Alle Elemente wurden entfernt");
						break;
					case 11:
						tree1.printInorder();
						System.out.println("\n");
						break;
					case 12:
						tree1.printPostorder();
						System.out.println("\n");
						break;
					case 13:
						tree1.printPreorder();
						System.out.println("\n");
						break;
					case 14:
						// Visuelle Darstellung
//						tree1.visualize();
						break;
					case 15:
						// Untermenü beenden
						leave = true;
						System.out.println("\nUntermenü beendet\n");
						break;
						default:
							System.err.println("\nUngültige Eingabe\n");
					}
					
				}while(!leave);				
			}
			if((input < 0) || (input > 3)) {
				System.err.println("\nUngültige Eingabe\n");
			}		
		}while(input != 2);
		System.out.println("\n>>> Programm beendet <<<\n");
		
		in.close();
	}
}