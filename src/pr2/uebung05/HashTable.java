package pr2.uebung05;

import pr2.uebung03.StringElement;
import pr2.uebung03.Element;

public class HashTable {
	// Variablen deklarieren
	private Object[][] hashTable;
	private Probing probing;
	private int numberOfCollisions = 0; // statistics counter for collisions

	/**
	 * Konstruktor der Klasse HashTable.
	 * 
	 * @param size    gibt die Initial-Länge der HashTable an.
	 * @param probing Probingverfahren, welches benötigt wird, um freie Plätze in
	 *                der HashTable zu berechnen.
	 */
	public HashTable(int size, Probing probing) { // hash table with default size = 10, default probing = linear
		this.hashTable = new Object[size][2];
		if (probing instanceof LinearProbing) {
			this.probing = (LinearProbing) probing;
		} else if (probing instanceof QuadraticProbing) {
			this.probing = (QuadraticProbing) probing;
		}
	}

	/**
	 * Key-Value Paare werden in die HashTable eingegeben.
	 * 
	 * @param key   der einzufügende Key.
	 * @param value der einzufügende Value-Wert.
	 * @return Gibt null zurück, wenn der HashTable-Index leer ist und das Key-Value
	 *         Paar erfolgreich eingefügt wurde. Ist der HashTable-Index nicht leer,
	 *         so wird der zuvor gespeicherte Value-Wert zurückgegeben und der neue
	 *         Value-Wert wird stattdessen gespeichert.
	 */
	public Object put(Object key, Object value) {
		if (key instanceof Element && (value instanceof String || value instanceof Song)) {
			do {
				// Bei Füllgrad > 75% wird die HashTable reHashed
				if (getHashTableLevel() < 75.0) {
					Object oldValue;
					int index = ((((StringElement) key).hashCode()) % this.hashTable.length);
					int newIndex = 0;
					boolean isProbingFinished = false;
					// HashTable an der errechneten Index-Stelle ist leer (null)
					if (this.hashTable[index][0] == null) {
						this.hashTable[index][0] = key;
						this.hashTable[index][1] = value;
						return null;
					} else { // HashTable ist am errechneten Index nicht leer (nicht null)
						// Überprüfen ob in der HashTable schon der Key vorhanden ist
						if (containsKey(key)) {
							// Key ist in der HashTable vorhanden
							StringElement stringElementInTable;
							for (int i = 0; i < this.hashTable.length; i++) {
								stringElementInTable = (StringElement) this.hashTable[i][0];
								if (((StringElement) key).equals(stringElementInTable)) {
									// Alten Wert am errechneten Index zwischenspeichern
									oldValue = this.hashTable[i][1];
									// Neuen Wert einfügen
									this.hashTable[i][1] = value;
									// Alten Wert zurückgeben
									return oldValue;
								}
							}
						} else {
							// Key ist nicht in der HashTable vorhanden
							probing.startProbing();
							do {
								// Neuen Index ermitteln
								newIndex = Math.abs((index + probing.nextNum()) % this.hashTable.length);
								// Überprüfen ob der neue Index leer (null) ist
								if (this.hashTable[newIndex][0] == null) {
									this.hashTable[newIndex][0] = key;
									this.hashTable[newIndex][1] = value;
									isProbingFinished = true;
									return null;
								}
								numberOfCollisions++;
							} while (!isProbingFinished);
						}
					}
				} else {
					// HashTable neu aufsetzen
					reHash();
				}
			} while (true);
		}
		return null;
	}

	/**
	 * Überprüft, ob der übergebene Schlüssel in der HashTable vorhanden ist und
	 * gibt, falls gefunden, den abgespeicherten Value-Wert zurück.
	 * 
	 * @param key der gesuchte Key.
	 * @return Wenn key in der HashTable vorhanden ist, wird sein Value-Wert
	 *         zurückgegeben, sonst null.
	 */
	public Object get(Object key) {
		if (key instanceof Element) {
			StringElement stringElementInTable;
			for (int i = 0; i < this.hashTable.length; i++) {
				stringElementInTable = (StringElement) this.hashTable[i][0];
				if (((StringElement) key).equals(stringElementInTable)) {
					return this.hashTable[i][1];
				}
			}
		}
		return null;
	}

	/**
	 * Statistik Methode, welche die Kollisionen zurück gibt.
	 * 
	 * @return Liefert die Anzahl der aufgetretenen Kollisionen zurück.
	 */
	public int getStat() {
		return this.numberOfCollisions;
	}

	/**
	 * Setzt die HashTable auf 0.
	 */
	public void clear() {
		for (int i = 0; i < hashTable.length; i++) {
			this.hashTable[i][0] = null;
			this.hashTable[i][1] = null;
		}
	}

	/**
	 * Überprüft, ob die HashTable leer ist oder schon befüllt wurde.
	 * 
	 * @return isEmpty Gibt true zurück, wenn die HashTable leer ist, sonst false.
	 */
	public boolean isEmpty() {
		if (this.hashTable.length == 0) {
			return true;
		}
		for (int i = 0; i < this.hashTable.length; i++) {
			if (this.hashTable[i][0] != null) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Gibt die HashTable im Format Index | Key | Value aus.
	 */
	public void printHT() {
		System.out.println("----- HashTable -----");
		for (int i = 0; i < this.hashTable.length; i++) {
			System.out.println((i + 1) + ". Key: " + this.hashTable[i][0] + "  |  Value: " + hashTable[i][1]);
		}
		System.out.println();
	}

	/**
	 * Ermittelt die Länge der HashTable. Länge ist definiert durch die Anzahl
	 * belegter Plätze innerhalb der HashTable.
	 * 
	 * @return size gibt die Anzahl der belegten Plätze der HashTable zurück
	 */
	public int size() {
		int counter = 0;
		for (int i = 0; i < hashTable.length; i++) {
			if (this.hashTable[i][1] != null) {
				counter++;
			}
		}
		return counter;
	}

	/**
	 * Rehashing der HashTable. Löscht markierte Plätze und organisiert die
	 * HashTable neu.
	 */
	public void reHash() {
		// Variablen deklarieren
		int newIndex = 0;
		int size = this.hashTable.length * 2;
		
		// Größere HashTable anlegen
		Object[][] newHashTable = new Object[size][2];
		// HashTable durchlaufen
		for (int i = 0; i < this.hashTable.length; i++) {
			// Kopiert Key + Value, wenn Value ungleich null ist
			if (this.hashTable[i][1] != null) {
				newHashTable[newIndex][0] = this.hashTable[i][0];
				newHashTable[newIndex][1] = this.hashTable[i][1];
				newIndex++;
			}
		}
		// Neue HashTable überschreiben
		this.hashTable = newHashTable;
	}

	/**
	 * Löscht ein Wert in der HashTable, indem dieser Wert zum löschen markiert
	 * wird.
	 * 
	 * @param key der zu löschende key.
	 * @return gibt true zurück, wenn erfolgreich gelöscht wurde, sonst falls.
	 */
	public boolean remove(Object key) {
		if (key instanceof Element) {
			// Variablen deklarieren
			StringElement stringElementInTable;
			// Überprüft ob übergebener Key vorhanden ist
			if (containsKey(key)) {
				// HashTable durchlaufen und VALUE mit null markieren
				for (int i = 0; i < this.hashTable.length; i++) {
					stringElementInTable = (StringElement) this.hashTable[i][0];
					if (((StringElement) key).equals(stringElementInTable)) {
						this.hashTable[i][1] = null;
					}
				}
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	/**
	 * Überprüft, ob der übergebene Schlüssel in der HashTable vorhanden ist.
	 * 
	 * @param key der gesuchte Key.
	 * @return Gibt true zurück, wenn der key in der HashTable gefunden wurde, sonst
	 *         false.
	 */
	public boolean containsKey(Object key) {
		if (key instanceof Element) {
			StringElement stringElementInTable;
			for (int i = 0; i < this.hashTable.length; i++) {
				stringElementInTable = (StringElement) this.hashTable[i][0];
				if (key.equals(stringElementInTable)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Überprüft, ob der übergebene Value-Wert in der HashTable vorhanden ist.
	 * 
	 * @param value der gesuchte Wert.
	 * @return Gibt true zurück, wenn der key in der HashTable gefunden wurde, sonst
	 *         false.
	 */
	public boolean contains(Object value) {
		String stringInTable;
		if (value instanceof String) {
			for (int i = 0; i < this.hashTable.length; i++) {
				stringInTable = (String) this.hashTable[i][1];
				if (((String) value).equals(stringInTable)) {
					return true;
				}
			}
		} else if (value instanceof Song) {
			for (int i = 0; i < this.hashTable.length; i++) {
				stringInTable = (String) this.hashTable[i][1];
				if (((Song) value).equals(stringInTable)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Hilfsmethode, um den Füllgrad der HashTable zu ermitteln.
	 * 
	 * @return Gibt den Füllgrad der HashTable zurück.
	 */
	private double getHashTableLevel() {
		double usedIndizes = 0;
		double füllgrad = 0.0;
		for (int i = 0; i < this.hashTable.length; i++) {
			if (this.hashTable[i][0] != null) {
				usedIndizes++;
			}
			füllgrad = ((usedIndizes / this.hashTable.length) * 100);
			if (füllgrad >= 75) {
				return füllgrad;
			}
		}
		return füllgrad;
	}
	
}