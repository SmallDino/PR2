package pr2.uebung04;

public class IntElement implements Comparable {
	// Variablen deklarieren
	private int val;

	/**
	 * Konstruktor der Klasse IntElement. Benötigt um ein Element des Typs Integer
	 * einzulesen und abzuspeichern, damit man den Wert später verwenden kann.
	 * 
	 * @param val Integer Objekt wird in einer lokalen Variable abgespeichert.
	 */
	public IntElement(Integer val) {
		this.val = val;
	}

	@Override
	public int compareTo(Object object) {
		IntElement ei = (IntElement) object;
		if (this.val == ei.val) {
			return 0;
		} else if (this.val < ei.val) {
			return -1;
		} else {
			return 1;
		}
	};

	@Override
	public String toString() {
		return this.val + "";
	}
}