package pr2.uebung04;

import java.util.Scanner;

public class SortsMain {
	/**
	 * 
	 * @param filename Datei mit IntElementen
	 * @return Array mit IntElementen
	 */
	public static Comparable[] fillArrayWithIntElementsFromFile(String filename) {

		int[] intValues = readIntegerArray(filename);

		Comparable[] intElements = new Comparable[intValues.length];

		for (int i = 0; i < intValues.length; i++) {
			intElements[i] = new IntElement(intValues[i]);
			// geht wg. MakeItSimple nicht anders
		}

		return intElements;

	}

	/**
	 * 
	 * @param filename Datei mit StringElementen
	 * @return Array mit StringElementen
	 */
	public static Comparable[] fillArrayWithStringElementsFromFile(String filename) {

		String[] stringValues = readStringArray(filename);

		Comparable[] stringElements = new Comparable[stringValues.length];

		for (int i = 0; i < stringValues.length; i++) {
			stringElements[i] = new StringElement(stringValues[i]);
			// geht wg. MakeItSimple nicht anders
		}

		return stringElements;

	}

	/**
	 * 
	 * @param filename Datei mit Songs
	 * @return Array mit den Songs vom Typ Comparable
	 */
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

			// Element artist = new StringElement(artists[0]); // artist[0] = Künstler_1,
			// // artist[1] = Künstler_2
			// Element singleSong = new StringElement(parts[0]); // parts[0] = Titel

			allSongs[j++] = new SongImplementation(parts[0], artists, parts[1]);
			// parts[1] = Album
		}
		return allSongs;
	}

	public static void sortArray(SortMethod sm, Comparable[] cm) {
		sm.sort(cm);
	}

	public static void main(String[] args) throws Exception {

		int eingabe;
		boolean leave = false;
		Comparable[] com;

		ShakerSort ss = new ShakerSort();
		ShakerSortDescending ssd = new ShakerSortDescending();

		QuickSort qs = new QuickSort();
		QuickSortDescending qsd = new QuickSortDescending();

		// Tastatur
		Scanner in = new Scanner(System.in);

		// "Hauptmenü", Abfrage von welchem Objekt eingelesen werden soll
		do {
			System.out.println("--- Menü ---");
			System.out.println("Bitte geben Sie an, welche Objekte Sie einlesen wollen: ");
			System.out.println("[1] IntElemente ");
			System.out.println("[2] StringElemente ");
			System.out.println("[3] SongElemente ");
			System.out.println("[X] Programm beenden ");
			leave = false;

			eingabe = in.nextInt();

			// Objekt von Typ IntElement soll eingelesen werden
			if (eingabe == 1) {

				// Untermenü, Abfrage worüber Array eingelesen werden soll(Konsole oder Datei)
				do {
					System.out.println("");
					System.out.println("--- Untermenü ---");
					System.out.println("Bitte geben Sie an, wie Sie die Elemente einlesen wollen");
					System.out.println("[1] Über die Konsole ");
					System.out.println("[2] Aus einer Datei ");
					System.out.println("[3] Untermenü beenden ");
					int select = in.nextInt();
					switch (select) {
					case 1: // Int-Array wiird über Konsole eingegeben
						com = arrayEinlesen(in, 1);
						// Unter-untermenü, Abfrage welche Sortierung benutzt werden soll
						do {
							System.out.println("");
							System.out.println("---Untermenü---");
							System.out.println("Bitte geben Sie an, welches Sortierverfahren benutzt werden soll");
							System.out.println("[1] ShakerSort ");
							System.out.println("[2] QuickSort ");
							System.out.println("[3] Untermenü beenden ");
							int selectSorter = in.nextInt();

							switch (selectSorter) {
							case 1: // ShakerSort Untermenü, ob auf- oder absteigend sortiert werden soll
								do {
									System.out.println("");
									System.out.println("--- Untermenü ---");
									System.out.println("Wie soll sortiert werden?");
									System.out.println("[1] ShakerSort aufsteigend sortieren ");
									System.out.println("[2] ShakerSort absteigend sortieren ");
									System.out.println("[3] Untermenü beenden ");

									int sort = in.nextInt();
									switch (sort) {
									case 1: // Aufsteigend sortiert
										sortArray(ss, com);
										System.out.println("\n----- NACH DEM SORTIEREN -----\n");
										for (int i = 0; i < com.length; i++) {
											System.out.println((i + 1) + ". Wert im Array: " + com[i]);
										}
										break;
									case 2: // Absteigend sortiert
										sortArray(ssd, com);
										System.out.println("\n----- NACH DEM SORTIEREN -----\n");
										for (int i = 0; i < com.length; i++) {
											System.out.println((i + 1) + ". Wert im Array: " + com[i]);
										}
										break;
									case 3:
										leave = true;
										System.out.println("--Untermenü beendet--");
										System.out.println("");
										break;
									default:
										System.err.println("\n---Unzulässige Eingabe---\n");
									}
								} while (!leave);

								break;
							case 2: // QuickSort Untermenü, ob ab- oder aussteigend sortiert wird
								do {
									System.out.println("");
									System.out.println("--- Untermenü ---");
									System.out.println("Wie soll sortiert werden?");
									System.out.println("[1] QuickSort aufsteigend ");
									System.out.println("[2] QuickSort absteigend ");
									System.out.println("[3] Untermenü beenden ");

									int sort = in.nextInt();
									switch (sort) {
									case 1: // Aufsteigend sortieren
										sortArray(qs, com);
										System.out.println("\n----- NACH DEM SORTIEREN -----\n");
										for (int i = 0; i < com.length; i++) {
											System.out.println((i + 1) + ". Wert im Array: " + com[i]);
										}
										break;
									case 2: // Absteigend sortieren
										sortArray(qsd, com);
										System.out.println("\n----- NACH DEM SORTIEREN -----\n");
										for (int i = 0; i < com.length; i++) {
											System.out.println((i + 1) + ". Wert im Array: " + com[i]);
										}
										break;
									case 3:
										leave = true;
										System.out.println("--Untermenü beendet--");
										System.out.println("");
										break;
									}
								} while (!leave);
								break;
							case 3:
								leave = true;
								System.out.println("--Untermenü beendet--");
								System.out.println("");
								break;
							default:
								System.err.println("\n---Unzulässige Eingabe---\n");

							}
						} while (!leave);
						// Unter-untermenü wird verlassen(Sortierung)
						break;

					case 2: // Int-File wird eingegeben, Pfad muss einegeben werden
						System.out.println("Bitte Pfad der Datei eingeben: ");
						System.out.print("Pfad: ");

						String file = in.next();
						com = fillArrayWithIntElementsFromFile(file);

						System.out.println("Datei wurde eingelesen");
						System.out.println("\n----- VOR DEM SORTIEREN -----\n");
						for (int k = 0; k < com.length; k++) {
							System.out.println(com[k]);
						}
						// Abfrage welcher Sortieralgorithmus auf Datei angewendet werden soll
						do {
							System.out.println("");
							System.out.println("---Untermenü---");
							System.out.println("Bitte geben Sie an, welches Sortierverfahren benutzt werden soll");
							System.out.println("[1] ShakerSort ");
							System.out.println("[2] QuickSort ");
							System.out.println("[3] Untermenü beenden ");
							int selectSorter = in.nextInt();

							switch (selectSorter) {
							case 1: // ShakerSort Auswahl
								do {
									System.out.println("");
									System.out.println("--- Untermenü ---");
									System.out.println("Wie soll sortiert werden?");
									System.out.println("[1] ShakerSort aufsteigend ");
									System.out.println("[2] ShakerSort absteigend ");
									System.out.println("[3] Untermenü beenden ");

									int sort = in.nextInt();
									switch (sort) {
									case 1: // ShakerSort aufsteigend sortieren
										sortArray(ss, com);
										System.out.println("\n----- NACH DEM SORTIEREN -----\n");
										for (int i = 0; i < com.length; i++) {
											System.out.println((i + 1) + ". Wert im Array: " + com[i]);
										}
										break;
									case 2: // ShakerSort absteigend sortieren
										sortArray(ssd, com);
										System.out.println("\n----- NACH DEM SORTIEREN -----\n");
										for (int i = 0; i < com.length; i++) {
											System.out.println((i + 1) + ". Wert im Array: " + com[i]);
										}
										break;
									case 3:
										leave = true;
										System.out.println("--Untermenü beendet--");
										System.out.println("");
										break;
									default:
										System.err.println("\n---Unzulässige Eingabe---\n");

									}
								} while (!leave);

								break;
							case 2: // QuickSort Auswahl
								do {
									System.out.println("");
									System.out.println("--- Untermenü ---");
									System.out.println("Wie soll sortiert werden?");
									System.out.println("[1] QuickSort aufsteigend ");
									System.out.println("[2] QuickSort absteigend ");
									System.out.println("[3] Untermenü beenden ");

									int sort = in.nextInt();
									switch (sort) {
									case 1: // QuickSort aufsteigend sortieren
										sortArray(qs, com);
										System.out.println("\n----- NACH DEM SORTIEREN -----\n");
										for (int i = 0; i < com.length; i++) {
											System.out.println((i + 1) + ". Wert im Array: " + com[i]);
										}
										break;
									case 2: // QuickSort absteigend sortiert
										sortArray(qsd, com);
										System.out.println("\n----- NACH DEM SORTIEREN -----\n");
										for (int i = 0; i < com.length; i++) {
											System.out.println((i + 1) + ". Wert im Array: " + com[i]);
										}
										break;
									case 3:
										leave = true;
										System.out.println("--Untermenü beendet--");
										System.out.println("");
										break;
									default:
										System.err.println("\n---Unzulässige Eingabe---\n");
									}
								} while (!leave);
								break;
							case 3:
								leave = true;
								System.out.println("--Untermenü beendet--");
								System.out.println("");
								break;
							default:
								System.err.println("\n---Unzulässige Eingabe---\n");
							}
						} while (!leave);
						break;
					case 3:
						leave = true;
						System.out.println("--Untermenü beendet--");
						System.out.println("");
						break;
					default:
						System.err.println("\n---Unzulässige Eingabe---\n");
					}
				} while (!leave);
				// Untermenü wird verlassen(Konsole oder Datei)
			}
			// Objekt von Typ StringElement soll eingelesen werden
			if (eingabe == 2) {
				// Untermenü, Abfrage worüber Array eingelesen werden soll(Konsole oder Datei)
				do {
					System.out.println("");
					System.out.println("--- Untermenü ---");
					System.out.println("Bitte geben Sie an, wie Sie die Elemente einlesen wollen");
					System.out.println("[1] Konsole ");
					System.out.println("[2] Datei ");
					System.out.println("[3] Untermenü beenden ");
					int select = in.nextInt();
					switch (select) {
					case 1: // StringElements wird über Konsole eingelesen
						com = arrayEinlesen(in, 2);
						// Unter-untermenü, Abfrage welche Sortierung benutzt werden soll
						do {
							System.out.println("");
							System.out.println("---Untermenü---");
							System.out.println("Bitte geben Sie an, welches Sortierverfahren benutzt werden soll");
							System.out.println("[1] ShakerSort ");
							System.out.println("[2] QuickSort ");
							System.out.println("[3] Untermenü beenden ");
							int selectSorter = in.nextInt();

							switch (selectSorter) {
							case 1: // ShakerSort Auswahl
								do {
									System.out.println("");
									System.out.println("--- Untermenü ---");
									System.out.println("Wie soll sortiert werden?");
									System.out.println("[1] ShakerSort aufsteigend ");
									System.out.println("[2] ShakerSort absteigend ");
									System.out.println("[3] Untermenü beenden ");

									int sort = in.nextInt();
									switch (sort) {
									case 1: // ShakerSort aufsteigend sortiert
										sortArray(ss, com);
										System.out.println("\n----- NACH DEM SORTIEREN -----\n");
										for (int i = 0; i < com.length; i++) {
											System.out.println((i + 1) + ". Wert im Array: " + com[i]);
										}
										break;
									case 2: // ShakerSort absteigend sortiert
										sortArray(ssd, com);
										System.out.println("\n----- NACH DEM SORTIEREN -----\n");
										for (int i = 0; i < com.length; i++) {
											System.out.println((i + 1) + ". Wert im Array: " + com[i]);
										}
										break;
									case 3:
										leave = true;
										System.out.println("--Untermenü beendet--");
										System.out.println("");
										break;
									default:
										System.err.println("\n---Unzulässige Eingabe---\n");
									}
								} while (!leave);

								break;
							case 2: // QuickSort Auswahl
								do {
									System.out.println("");
									System.out.println("--- Untermenü ---");
									System.out.println("Wie soll sortiert werden?");
									System.out.println("[1] QuickSort aufsteigend ");
									System.out.println("[2] QuickSort absteigend ");
									System.out.println("[3] Untermenü beenden ");

									int sort = in.nextInt();
									switch (sort) {
									case 1: // QuickSort aufsteigend sortiert
										sortArray(qs, com);
										System.out.println("\n----- NACH DEM SORTIEREN -----\n");
										for (int i = 0; i < com.length; i++) {
											System.out.println((i + 1) + ". Wert im Array: " + com[i]);
										}
										break;
									case 2: // QuickSort absteigend sortiert
										sortArray(qsd, com);
										System.out.println("\n----- NACH DEM SORTIEREN -----\n");
										for (int i = 0; i < com.length; i++) {
											System.out.println((i + 1) + ". Wert im Array: " + com[i]);
										}
										break;
									case 3:
										leave = true;
										System.out.println("--Untermenü beendet--");
										System.out.println("");
										break;
									default:
										System.err.println("\n---Unzulässige Eingabe---\n");
									}
								} while (!leave);
								break;
							case 3:
								leave = true;
								System.out.println("--Untermenü beendet--");
								System.out.println("");
								break;
							default:
								System.err.println("\n---Unzulässige Eingabe---\n");
							}
						} while (!leave);
						// Unter-untermenü wird verlassen(Sortierung)
						break;

					case 2: // String-File woll eingelesen werden
						System.out.println("Bitte Pfad der Datei eingeben: ");
						System.out.print("Pfad: ");
						String file = in.next();
						com = fillArrayWithStringElementsFromFile(file);
						System.out.println("Datei wurde eingelesen");

						// Datei wird unsortiert ausgegeben
						System.out.println("\n----- VOR DEM SORTIEREN -----\n");
						for (int k = 0; k < com.length; k++) {
							System.out.println(com[k]);
						}
						do {
							// Auswahl mit welchem Sortierverfahren String-File sortiert werden soll
							System.out.println("");
							System.out.println("---Untermenü---");
							System.out.println("Bitte geben Sie an, welches Sortierverfahren benutzt werden soll");
							System.out.println("[1] ShakerSort ");
							System.out.println("[2] QuickSort ");
							System.out.println("[3] Untermenü beenden ");
							int selectSorter = in.nextInt();

							switch (selectSorter) {
							case 1: // (File)ShakerSort Auswahl
								do {
									System.out.println("");
									System.out.println("--- Untermenü ---");
									System.out.println("Wie soll sortiert werden?");
									System.out.println("[1] ShakerSort aufsteigend ");
									System.out.println("[2] ShakerSort absteigend ");
									System.out.println("[3] Untermenü beenden ");

									int sort = in.nextInt();
									switch (sort) {
									case 1: // ShakerSort aufsteigend sortieren
										sortArray(ss, com);
										System.out.println("\n----- NACH DEM SORTIEREN -----\n");
										for (int i = 0; i < com.length; i++) {
											System.out.println((i + 1) + ". Wert im Array: " + com[i]);
										}
										break;
									case 2: // ShakerSort absteigend sortieren
										sortArray(ssd, com);
										System.out.println("\n----- NACH DEM SORTIEREN -----\n");
										for (int i = 0; i < com.length; i++) {
											System.out.println((i + 1) + ". Wert im Array: " + com[i]);
										}
										break;
									case 3:
										leave = true;
										System.out.println("--Untermenü beendet--");
										System.out.println("");
										break;
									default:
										System.err.println("\n---Unzulässige Eingabe---\n");
									}
								} while (!leave);

								break;
							case 2: // (File)QuickSort Auswahl
								do {
									System.out.println("");
									System.out.println("--- Untermenü ---");
									System.out.println("Wie soll sortiert werden?");
									System.out.println("[1] QuickSort aufsteigend ");
									System.out.println("[2] QuickSort absteigend ");
									System.out.println("[3] Untermenü beenden ");

									int sort = in.nextInt();
									switch (sort) {
									case 1: // QuickSort aufsteigend sortieren
										sortArray(qs, com);
										System.out.println("\n----- NACH DEM SORTIEREN -----\n");
										for (int i = 0; i < com.length; i++) {
											System.out.println((i + 1) + ". Wert im Array: " + com[i]);
										}
										break;
									case 2: // QuickSort absteigend sortieren
										sortArray(qsd, com);
										System.out.println("\n----- NACH DEM SORTIEREN -----\n");
										for (int i = 0; i < com.length; i++) {
											System.out.println((i + 1) + ". Wert im Array: " + com[i]);
										}
										break;
									case 3:
										leave = true;
										System.out.println("--Untermenü beendet--");
										System.out.println("");
										break;
									default:
										System.err.println("\n---Unzulässige Eingabe---\n");
									}
								} while (!leave);
								break;
							case 3:
								leave = true;
								System.out.println("--Untermenü beendet--");
								System.out.println("");
								break;
							default:
								System.err.println("\n---Unzulässige Eingabe---\n");
							}
						} while (!leave);
						break;
					case 3:
						leave = true;
						System.out.println("--Untermenü beendet--");
						System.out.println("");
						break;
					default:
						System.err.println("\n---Unzulässige Eingabe---\n");
					}
				} while (!leave);

			}

			// Objekt von Typ Song
			if (eingabe == 3) {
				// Untermenü, Abfrage worüber Array eingelesen werden soll(Konsole oder Datei)
				do {
					System.out.println("");
					System.out.println("--- Untermenü ---");
					System.out.println("Bitte geben Sie an, wie Sie die Elemente einlesen wollen");
					System.out.println("[1] Konsole ");
					System.out.println("[2] Datei ");
					System.out.println("[3] Untermenü beenden ");
					int select = in.nextInt();
					switch (select) {
					case 1: // Array mit Song Elementen wird über Konsole eingegeben
						com = arrayEinlesen(in, 3);
						// Unter-untermenü, Abfrage welche Sortierung benutzt werden soll
						do {
							System.out.println("");
							System.out.println("---Untermenü---");
							System.out.println("Bitte geben Sie an, welches Sortierverfahren benutzt werden soll");
							System.out.println("[1] ShakerSort ");
							System.out.println("[2] QuickSort ");
							System.out.println("[3] Untermenü beenden ");
							int selectSorter = in.nextInt();

							switch (selectSorter) {
							case 1: // (Konsole) ShakerSort Auswahl
								do {
									System.out.println("");
									System.out.println("--- Untermenü ---");
									System.out.println("Wie soll sortiert werden?");
									System.out.println("[1] ShakerSort aufsteigend ");
									System.out.println("[2] ShakerSort absteigend ");
									System.out.println("[3] Untermenü beenden ");

									int sort = in.nextInt();
									switch (sort) {
									case 1: // ShakerSort aufsteigend sortiert
										sortArray(ss, com);
										System.out.println("\n----- NACH DEM SORTIEREN -----\n");
										for (int i = 0; i < com.length; i++) {
											System.out.println((i + 1) + ". Wert im Array: " + com[i]);
										}
										break;
									case 2: // ShakerSort absteigend sortiert
										sortArray(ssd, com);
										System.out.println("\n----- NACH DEM SORTIEREN -----\n");
										for (int i = 0; i < com.length; i++) {
											System.out.println((i + 1) + ". Wert im Array: " + com[i]);
										}
										break;
									case 3:
										leave = true;
										System.out.println("--Untermenü beendet--");
										System.out.println("");
										break;
									default:
										System.err.println("\n---Unzulässige Eingabe---\n");
									}
								} while (!leave);

								break;
							case 2: // (Konsole) QuickSort Auswahl
								do {
									System.out.println("");
									System.out.println("--- Untermenü ---");
									System.out.println("Wie soll sortiert werden?");
									System.out.println("[1] QuickSort aufsteigend ");
									System.out.println("[2] QuickSort absteigend ");
									System.out.println("[3] Untermenü beenden ");

									int sort = in.nextInt();
									switch (sort) {
									case 1: // QuickSort aufsteigend sortiert
										sortArray(qs, com);
										System.out.println("\n----- NACH DEM SORTIEREN -----\n");
										for (int i = 0; i < com.length; i++) {
											System.out.println((i + 1) + ". Wert im Array: " + com[i]);
										}
										break;
									case 2: // QuickSort absteigend sortiert
										sortArray(qsd, com);
										System.out.println("\n----- NACH DEM SORTIEREN -----\n");
										for (int i = 0; i < com.length; i++) {
											System.out.println((i + 1) + ". Wert im Array: " + com[i]);
										}
										break;
									case 3:
										leave = true;
										System.out.println("--Untermenü beendet--");
										System.out.println("");
										break;
									default:
										System.err.println("\n---Unzulässige Eingabe---\n");
									}
								} while (!leave);
								break;
							case 3:
								leave = true;
								System.out.println("--Untermenü beendet--");
								System.out.println("");
								break;
							default:
								System.err.println("\n---Unzulässige Eingabe---\n");
							}
						} while (!leave);
						// Unter-untermenü wird verlassen(Sortierung)
						break;
					case 2:
						System.out.println("Bitte Pfad der Datei eingeben: ");
						System.out.print("Pfad: ");

						String file = in.next();
						com = fillArrayWithSongsFromFile(file);

						// File wird unsortiert auf Konsole ausgegeben
						System.out.println("Datei wurde eingelesen");
						System.out.println("\n----- VOR DEM SORTIEREN -----\n");
						for (int k = 0; k < com.length; k++) {
							System.out.println(com[k]);
						}

						// Auswahl mit welchem Sortierverfahren Inhalt Song-Datei sortiert werden soll
						do {
							System.out.println("");
							System.out.println("---Untermenü---");
							System.out.println("Bitte geben Sie an, welches Sortierverfahren benutzt werden soll");
							System.out.println("[1] ShakerSort ");
							System.out.println("[2] QuickSort ");
							System.out.println("[3] Untermenü beenden ");
							int selectSorter = in.nextInt();

							switch (selectSorter) {
							case 1: // (File) ShakerSort Auswahl
								do {
									System.out.println("");
									System.out.println("--- Untermenü ---");
									System.out.println("Wie soll sortiert werden?");
									System.out.println("[1] ShakerSort aufsteigend ");
									System.out.println("[2] ShakerSort absteigend ");
									System.out.println("[3] Untermenü beenden ");

									int sort = in.nextInt();
									switch (sort) {
									case 1: // ShakerSort aufsteigend sortiert
										sortArray(ss, com);
										System.out.println("\n----- NACH DEM SORTIEREN -----\n");
										for (int i = 0; i < com.length; i++) {
											System.out.println((i + 1) + ". Wert im Array: " + com[i]);
										}
										break;
									case 2: // ShakerSort absteigend sortiert
										sortArray(ssd, com);
										System.out.println("\n----- NACH DEM SORTIEREN -----\n");
										for (int i = 0; i < com.length; i++) {
											System.out.println((i + 1) + ". Wert im Array: " + com[i]);
										}
										break;
									case 3:
										leave = true;
										System.out.println("--Untermenü beendet--");
										System.out.println("");
										break;
									default:
										System.err.println("\n---Unzulässige Eingabe---\n");
									}
								} while (!leave);

								break;
							case 2: // (File) QuickSort Auswahl
								do {
									System.out.println("");
									System.out.println("--- Untermenü ---");
									System.out.println("Wie soll sortiert werden?");
									System.out.println("[1] QuickSort aufsteigend ");
									System.out.println("[2] QuickSort absteigend ");
									System.out.println("[3] Untermenü beenden ");

									int sort = in.nextInt();
									switch (sort) {
									case 1: // QuickSort aufsteigend sortiert
										sortArray(qs, com);
										System.out.println("\n----- NACH DEM SORTIEREN -----\n");
										for (int i = 0; i < com.length; i++) {
											System.out.println((i + 1) + ". Wert im Array: " + com[i]);
										}
										break;
									case 2: // QuickSort absteigend sortiert
										sortArray(qsd, com);
										System.out.println("\n----- NACH DEM SORTIEREN -----\n");
										for (int i = 0; i < com.length; i++) {
											System.out.println((i + 1) + ". Wert im Array: " + com[i]);
										}
										break;
									case 3:
										leave = true;
										System.out.println("--Untermenü beendet--");
										System.out.println("");
										break;
									default:
										System.err.println("\n---Unzulässige Eingabe---\n");
									}
								} while (!leave);
								break;
							case 3:
								leave = true;
								System.out.println("--Untermenü beendet--");
								System.out.println("");
								break;
							default:
								System.err.println("\n---Unzulässige Eingabe---\n");
							}
						} while (!leave);

						break;
					case 3:
						leave = true;
						System.out.println("--Untermenü beendet--");
						System.out.println("");
						break;
					default:
						System.err.println("\n---Unzulässige Eingabe---\n");
					}
				} while (!leave);
			}

		} while ((eingabe < 4) && (eingabe > 0));
		System.out.println("");
		System.out.println(">>Programm beendet<<");

		in.close();
	}

	/**
	 * 
	 * @param in     Scanner wird übergeben
	 * @param choice Bestimmt welches Objekt über die Konsole eingelesen wird
	 * @return Gibt Array mit den, über Konsole eingegebenen, Elementen zurück
	 */
	static Comparable[] arrayEinlesen(Scanner in, int choice) {

		int arrayGröße;
		int arrayFüllen;
		int artistNumber;
		String stringArray;
		String nameSong;
		String nameArtist;
		String album;
		String[] artists;
		Comparable[] zahlenreihe;
		SongImplementation song;

		System.out.println("Bitte Arraygröße eingeben: ");
		arrayGröße = in.nextInt();
		zahlenreihe = new Comparable[arrayGröße];

		// IntElemente sollen über Konsole eingegeben werden
		if (choice == 1) {
			for (int i = 0; i < zahlenreihe.length; i++) {
				System.out.println("Bitte Werte eingeben: ");
				arrayFüllen = in.nextInt();
				zahlenreihe[i] = arrayFüllen;
			}
			// StringElemente sollen über Konsole eingegeben werden
		} else if (choice == 2) {
			for (int i = 0; i < zahlenreihe.length; i++) {
				System.out.println("Bitte Werte eingeben: ");
				stringArray = in.next();
				zahlenreihe[i] = stringArray;
			}
			// Song soll über Konsole eingegeben werden
		} else if (choice == 3) {
			for (int i = 0; i < zahlenreihe.length; i++) {
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
				zahlenreihe[i] = song;

				System.out.println("------------------");

			}
		}

		// Array wird unsortiert ausgegeben
		System.out.println("Gefüllt");
		System.out.println("\n----- VOR DEM SORTIEREN -----\n");
		for (int k = 0; k < zahlenreihe.length; k++) {
			System.out.print(zahlenreihe[k] + ",");
		}
		System.out.println();

		return zahlenreihe;
	}

}