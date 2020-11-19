package pr2.hausarbeit;

public class IntElement implements Comparable<IntElement> {
	// Variablen deklarieren
	private int key;

	/**
	 * Konstruktor der Klasse IntElement. Benötigt um ein Element des Typs Integer
	 * einzulesen und abzuspeichern, damit man den Wert später verwenden kann.
	 * 
	 * @param val Integer Objekt wird in einer lokalen Variable abgespeichert.
	 */
	public IntElement(Integer key) {
		this.key = key;
	}

	@Override
	public int compareTo(IntElement key) {
		IntElement ei = (IntElement) key;
		if (this.key == ei.key) { // Übergebenes Element ist gleich
			return 0;
		} else if (this.key < ei.key) {
			return -1;
		} else {
			return 1;
		}
	};

	@Override
	public String toString() {
		return this.key + "";
	}
}