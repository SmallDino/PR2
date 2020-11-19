package pr2.uebung05;

import java.util.Scanner;
import pr2.uebung03.*;

public class HashTableMain {
	//  Paaren (StringElement, String)  oder mit Paaren (StringElement, Song)
	
	public static void main(String[] args) {

		//Variablen deklarien
		int eingabe;
		int hashSize;
		boolean leave = false;
		
		HashTable stringElemAndLin;
		HashTable stringElemAndQuad;
		
		LinearProbing lp = new LinearProbing();
		QuadraticProbing qp = new QuadraticProbing();
		
		//Tastatur
		Scanner in = new Scanner(System.in);
		
		do {
			
			System.out.println("--Menü--");
			System.out.println("[1] StringElement und lineares Sondieren");
			System.out.println("[2] StringElement und quadratisches Sondieren");
			System.out.println("[3] Programm beenden");
			leave = false;
			
			System.out.print("Eingabe: ");
			eingabe = in.nextInt();
			
			//StringElements und lineares Sondieren
			if(eingabe == 1) {
				System.out.println("Bitte Menge der Elemente eingeben: ");
				System.out.print("Eingabe: ");
				hashSize = in.nextInt();
				stringElemAndLin = new HashTable(hashSize, lp);
				do {
					System.out.println("1 File einlesen");
					System.out.println("2 put");
					System.out.println("3 get");
					System.out.println("4 contains - Prüft ob Value in Hashtabelle enthalten ist");
					System.out.println("5 containsKey - Prüft ob Key in Hashtabelle enthalten ist");
					System.out.println("6 isEmpty");
					System.out.println("7 size");
					System.out.println("8 clear");
					System.out.println("9 remove");
					System.out.println("10 reHash");
					System.out.println("11 printHT");
					System.out.println("12 Untermenü beendet");
					System.out.print("Eingabe: ");
					int select = in.nextInt();
					switch(select) {
					case 1: //File einlesen
						
						System.out.print("Bitte Absoluten Pfad eingeben: ");
						String file = in.next();
						
						String[] stringValues = readStringArray(file);
						StringElement[] stringElements = new StringElement[stringValues.length];
						
						for(int i = 0; i<stringValues.length ;i++) {
							stringElements[i] = new StringElement(stringValues[i]);
							System.out.println(stringElements[i]);
						}
						System.out.println("\n--Eingelesen--\n");
						for(int k = 0; k<stringElements.length;k++) {
							StringElement val = stringElements[k];
							stringElemAndLin.put(val, val.getKey());
						}

					break;
					case 2:
						System.out.println("Bitte Value eingeben ");
						System.out.print("Eingabe: ");
						String element = in.next();
						StringElement sElement = new StringElement(element);
						stringElemAndLin.put(sElement, sElement.getKey());

						break;
					case 3: // Get Value mit key 
						System.out.println("Bitte Key eingeben");
						System.out.print("Eingabe: ");
						String getValue = in.next();
						StringElement getTheValue = new StringElement(getValue);
						System.out.println(stringElemAndLin.get(getTheValue.getKey()));
						break;
					case 4:
						System.out.print("Bitte Value eingeben: ");
						String getVal = in.next();
						StringElement getTheVal = new StringElement(getVal);
						System.out.println(stringElemAndLin.get(getTheVal));
						break;
					case 5:
						System.out.println("Bitte Key eingeben");
						System.out.print("Eingabe: ");
						String conKey = in.next();
						StringElement conTheKey = new StringElement(conKey);
						System.out.println(stringElemAndLin.containsKey(conTheKey));
						break;
					case 6:
						System.out.println(stringElemAndLin.isEmpty());
						break;
					case 7:
						System.out.println("\n Anzahl der gespeicherten Elemente in der Hashtabelle: " + stringElemAndLin.size() + "\n");
						break;
					case 8:
						stringElemAndLin.clear();
						System.out.println("\nIst leer\n");
						break;
					case 9:
						System.out.println("Zu entfernenden Key angeben ");
						System.out.print("Eingabe: ");
						String removeElem = in.next();
						StringElement removeTheElem = new StringElement(removeElem);
						stringElemAndLin.remove(removeTheElem);
						System.out.println("Key entfernt");
						break;
					case 10:
						stringElemAndLin.reHash();
						break;
					case 11:
						stringElemAndLin.printHT();
						break;
					case 12:
						leave = true;
						System.out.println("\n--Untermenü beendet--\n");
						break;
						default:
							System.err.println("Ungültige Eingabe");
					}
				}while(!leave);
				
			}
			//StringLEements und quadratisches Sondieren
			if(eingabe == 2) {
				
				System.out.println("Bitte Menge der Elemente eingeben: ");
				hashSize = in.nextInt();
				stringElemAndQuad = new HashTable(hashSize, qp);
				do {
					System.out.println("1 File einlesen");
					System.out.println("2 put");
					System.out.println("3 get");
					System.out.println("4 containsValue");
					System.out.println("5 containsKey");
					System.out.println("6 isEmpty");
					System.out.println("7 size");
					System.out.println("8 clear");
					System.out.println("9 remove");
					System.out.println("10 reHash");
					System.out.println("11 printHT");
					System.out.println("12 Untermenü beendet");
					System.out.print("Eingabe: ");
					int select = in.nextInt();
					switch(select) {
					case 1: // File einlesen
						
						System.out.print("Bitte Absoluten Pfad eingeben: ");
						String file = in.next();
						
						String[] stringValues = readStringArray(file);
						StringElement[] stringElements = new StringElement[stringValues.length];
						
						for(int i = 0; i<stringValues.length ;i++) {
							stringElements[i] = new StringElement(stringValues[i]);
							System.out.println(stringElements[i]);
						}
						System.out.println("\n--Eingelesen--\n");
						for(int k = 0; k<stringElements.length;k++) {
							StringElement val = stringElements[k];
							stringElemAndQuad.put(val, val.getKey());
						}

					break;
					case 2:
						System.out.println("Bitte Value eingeben ");
						System.out.print("Eingabe: ");
						String element = in.next();
						StringElement sElement = new StringElement(element);
						while(stringElemAndQuad.size() < hashSize){
							stringElemAndQuad.put(sElement, sElement.getKey());}

						break;
					case 3: // Get Value mit key 
						System.out.println("Bitte Key eingeben");
						System.out.print("Eingabe: ");
						String getValue2 = in.next();
						StringElement getTheValue = new StringElement(getValue2);
						System.out.println(stringElemAndQuad.get(getTheValue));
						break;
					case 4:
						break;
					case 5:
						System.out.println("Bitte Key eingeben");
						System.out.print("Eingabe: ");
						String conKey = in.next();
						StringElement conTheKey = new StringElement(conKey);
						System.out.println(stringElemAndQuad.containsKey(conTheKey));
						break;
					case 6:
						System.out.println(stringElemAndQuad.isEmpty());
						break;
					case 7:
						System.out.println("\n Anzahl der gespeicherten Elemente in der Hashtabelle: " + stringElemAndQuad.size() + "\n");
						break;
					case 8:
						stringElemAndQuad.clear();
						System.out.println("\nIst leer\n");
						break;
					case 9:
						System.out.println("Zu entfernenden Key angeben ");
						System.out.print("Eingabe: ");
						String removeElem = in.next();
						StringElement removeTheElem = new StringElement(removeElem);
						stringElemAndQuad.remove(removeTheElem);
						System.out.println("Key entfernt");
						break;
					case 10:
						stringElemAndQuad.reHash();
						break;
					case 11:
						stringElemAndQuad.printHT();
						break;
					case 12:
						leave = true;
						System.out.println("\n--Untermenü beendet--\n");
						break;
						default:
							System.err.println("Ungültige Eingabe");
					}
				}while(!leave);	
			}
			
			//Progamm beendet
			if((eingabe < 1) || (eingabe > 3)) 
				System.err.println("\n--Ungültige Eingabe--\n");
	
		} while(eingabe != 3);
		System.out.println(">> Programm beendet <<");
		in.close();

	}
}