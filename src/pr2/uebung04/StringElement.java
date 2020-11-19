package pr2.uebung04;

public class StringElement implements Comparable {
	// Variablen deklarieren
	private String val;

	/**
	 * Konstruktor der Klasse StringElement. Benötigt um ein Element des Typs String
	 * einzulesen und abzuspeichern, damit man den Wert später verwenden kann.
	 * 
	 * @param val String Objekt wird in einer lokalen Variable abgespeichert.
	 */
	public StringElement(String val) {
		this.val = val;
	}

	@Override
	public int compareTo(Object object) {
		return this.val.compareTo(((StringElement) object).val);
	}

	@Override
	public String toString() {
		return this.val + " ";
	}
}