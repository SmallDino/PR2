package pr2.hausarbeit;

import java.util.Scanner;


// "Rahmenprogramm" aka Main
public class BTreeMain {
	public static void main(String[] args) {

		boolean leave = false;
		int menueInput;
//		SongImplementation songElem;
		
		Scanner in = new Scanner(System.in);	
		
		do {
			System.out.println("---Menü---");
			System.out.println("[1] Int-Elemente");
			System.out.println("[2] String-Elemente");
			System.out.println("[3] Song-Elemente");
			System.out.println("[4] Beenden");
			menueInput = in.nextInt();
			// Baum mit INTELEMENTEN füllen
			if(menueInput == 1) {
				System.out.print("\nBitte Ordnung eingeben: ");
				int ordnung = in.nextInt();
				BTreeImplementation<IntElement> intTree = new BTreeImplementation<IntElement>(ordnung);
				// Untermenü
				do {
					untermenü();
					int input = in.nextInt();
					
					// Menüauswahl mit Anweisungen belegen
					switch(input) {
					case 1:
						System.out.print("\nBitte Wert eingeben: ");
						int elem = in.nextInt();
						IntElement elemInt = new IntElement(elem); 
						intTree.insert(elemInt);
						System.out.println("Wert wurde eingefügt\n");
						break;
					case 2:
						System.out.print("Bitte absoluten Pfad angeben: ");
						String path = in.next();
						intTree.insert(path, "IntElement");
						System.out.println("Datei wurde eingelesen");
						break;
					case 3:
						System.out.print("Bitte Wert eingeben: ");
						int containedValue = in.nextInt();
						IntElement containedValueInt = new IntElement(containedValue); // In INTELEM!ENT! änder
						System.out.println("Enthält (" + containedValue + ") : " + intTree.contains(containedValueInt));
						break;
					case 4:// Remove()
						break;
					case 5:
						System.out.println("Anzahl der Elemente: " + intTree.size());
						break;
					case 6:
						System.out.println("Höhe des Baums: " + intTree.height());
						break;
					case 7:
						System.out.println("Größtest Element im Baum: " + intTree.getMax());
						break;
					case 8:
						System.out.println("Kleinstes Element im Baum: " + intTree.getMin());
						break;
					case 9:
						System.out.println("Baum leer: " + intTree.isEmpty());
						break;
					case 10:
						intTree.clear();
						System.out.println("\nAlle Elemente wurden entfernt");
						break;
					case 11:// AddAll()
						break;
					case 12:
						intTree.printInOrder();
						break;
					case 13:
						intTree.printPostOrder();
						break;
					case 14:
						intTree.printPreOrder();
						break;
					case 15:
						intTree.printLevelOrder();
						break;
					case 16:
						System.out.print("Bitte absoluten Pfad angeben: ");
						String dataPath = in.next();
						intTree.saveToFile(dataPath);
						break;
					case 17:// ReadFromFile()
						break;
					case 18: 
						intTree.clone();
						System.out.println("Baum wurde kopiert");
						break;
					case 0:
						leave = true;
						break;
						default:
							System.err.println("Ungültige Eingabe");
					}
				}while(! leave); // Untermenü beendet
				
			}
			// Baum mit STRINGELEMENTEN füllen
			if(menueInput == 2) {
				System.out.print("\nBitte Ordnung eingeben: ");
				int ordnung = in.nextInt();
				BTreeImplementation<StringElement> stringTree = new BTreeImplementation<StringElement>(ordnung);
				// Untermenü
				do {
					untermenü();
					int input = in.nextInt();
					
					//Untermenü mit Anweisungen belegen
					switch(input) {
					case 1:
						System.out.print("\nBitte Wert eingeben: ");
						String elem = in.next();
						StringElement elemString = new StringElement(elem); 
						stringTree.insert(elemString);
						System.out.println("Wert wurde eingefügt");
						break;
					case 2:
						System.out.print("Bitte absoluten Pfad angeben: ");
						String path = in.next();
						stringTree.insert(path, "String");
						System.out.println("Datei wurde eingelesen");
						break;
					case 3:
						System.out.print("Bitte Wert eingeben: ");
						String containedValue = in.next();
						StringElement containedValueString = new StringElement(containedValue); // In INTELEM!ENT! änder
						System.out.println("Enthält(" + containedValue + ") : " + stringTree.contains(containedValueString));
						break;
					case 4:// Remove()
						break;
					case 5:
						System.out.println("Anzahl der Elemente: " + stringTree.size());
						break;
					case 6:
						System.out.println("Höhe des Baums: " + stringTree.height());
						break;
					case 7:
						System.out.println("Größtest Element im Baum: " + stringTree.getMax());
						break;
					case 8:
						System.out.println("Kleinstes Element im Baum: " + stringTree.getMin());
						break;
					case 9:
						System.out.println("Baum leer: " + stringTree.isEmpty());
						break;
					case 10:
						stringTree.clear();
						System.out.println("\nAlle Elemente wurden entfernt");
						break;
					case 11:// AddAll()
						break;
					case 12:
						stringTree.printInOrder();
						break;
					case 13:
						stringTree.printPostOrder();
						break;
					case 14:
						stringTree.printPreOrder();
						break;
					case 15:
						stringTree.printLevelOrder();
						break;
					case 16:
						System.out.print("Bitte absoluten Pfad angeben: ");
						String dataPath = in.next();
						stringTree.saveToFile(dataPath);
						break;
					case 17:// ReadFromFile()
						break;
					case 18:
						stringTree.clone();
						System.out.println("Baum wurde kopiert");
						break;
					case 0:
						leave = true;
						break;
					default:
							System.err.println("Ungültige Eingabe");
					}
				}while(! leave); // Untermenü beendet
				
			}
			// Baum mit SONGELEMENTEN füllen
			if(menueInput == 3) {
				System.out.print("\nBitte Ordnung eingeben: ");
				int ordnung = in.nextInt();
				BTreeImplementation<Song> songTree = new BTreeImplementation<Song>(ordnung);
				// Untermenü
				do {
					untermenü();
					int input = in.nextInt();
					
					//Untermenü mit Anweisungen belegen
					switch(input) {
					case 1:
						SongImplementation songElem = readSong(in); 
						songTree.insert(songElem);
						System.out.println("\nWert wurde eingefügt");
						break;
					case 2:
						System.out.print("Bitte absoluten Pfad angeben: ");
						String path = in.next();
						songTree.insert(path, "Song");
						System.out.println("\nDatei wurde eingelesen");
						break;
					case 3:
						System.out.print("Bitte Wert eingeben: ");
//						String containedValue = in.next();
						SongImplementation containedValueSong = readSong(in); // In INTELEM!ENT! änder
						System.out.println("Enthält(" + containedValueSong + ") : " + songTree.contains(containedValueSong));
						break;
					case 4:// Remove()
						break;
					case 5:
						System.out.println("Anzahl der Elemente: " + songTree.size());
						break;
					case 6:
						System.out.println("Höhe des Baums: " + songTree.height());
						break;
					case 7:
						System.out.println("Größtest Element im Baum: " + songTree.getMax());
						break;
					case 8:
						System.out.println("Kleinstes Element im Baum: " + songTree.getMin());
						break;
					case 9:
						System.out.println("Baum leer: " + songTree.isEmpty());
						break;
					case 10:
						songTree.clear();
						System.out.println("Alle Elemente wurden entfernt");
						break;
					case 11:// AddAll()
						break;
					case 12:
						songTree.printInOrder();
						break;
					case 13:
						songTree.printPostOrder();
						break;
					case 14:
						songTree.printPreOrder();
						break;
					case 15:
						songTree.printLevelOrder();
						break;
					case 16:
						System.out.print("Bitte absoluten Pfad angeben: ");
						String dataPath = in.next();
						songTree.saveToFile(dataPath);
						break;
					case 17:// ReadFromFile()
						break;
					case 18:
						songTree.clone();
						System.out.println("Baum wurde kopiert");
						break;
					case 0:
						leave = true;
						break;
						default:
							System.err.println("\nUngültige Eingabe\n");
					}		
				}while(!leave);
			}
			if((menueInput < 1) || (menueInput > 5)) 
				System.err.println("\nUngültige Eingabe\n");
		}while(menueInput != 4); // Untermenü beendetwhile((menueInput > 0 ) && (menueInput < 4));
		System.out.println("\n>>Programm beendet<<");
		
		in.close();
	}
	
	private static void untermenü() {
		System.out.print("\n");
		System.out.println("--Untermenü--");
		System.out.println("[1] insert");
		System.out.println("[2] insert aus einer Datei");
		System.out.println("[3] contains");
		System.out.println("[4] remove");
		System.out.println("[5] size");
		System.out.println("[6] height");
		System.out.println("[7] getMax");
		System.out.println("[8] getMin");
		System.out.println("[9] isEmpty");
		System.out.println("[10] clear");
		System.out.println("[11] addAll");
		System.out.println("[12] printInOrder");
		System.out.println("[13] printPostOrder");
		System.out.println("[14] printPreOrder");
		System.out.println("[15] printLevelOrder");
		System.out.println("[16] saveToFile");
		System.out.println("[17] readFromFile");
		System.out.println("[18] tiefenkopie des Baums");
		System.out.println("[0]Untermenü beenden");
		System.out.print("\n");
		System.out.print("Option:");

	}
	
	private static SongImplementation readSong(Scanner in) {
		
		int artistNumber;
		String nameSong;
		String nameArtist;
		String album;
		String[] artists;
		SongImplementation song;

		
				System.out.println("Bitte Name des Songs eingeben: ");
				nameSong = in.next();
				
				System.out.println("Wie viele Artists: ");
				artistNumber = in.nextInt();
				artists = new String[artistNumber];
				for (int j = 0; j < artistNumber; j++) {
					System.out.println("Bitte Artist eingeben: ");
					nameArtist = in.next();
					artists[j] = nameArtist;
				}
				System.out.println("Bitte Album eingeben: ");
				album = in.next();
				song = new SongImplementation(nameSong, artists, album);
				System.out.println("-----");
				return song;

	}	
}
