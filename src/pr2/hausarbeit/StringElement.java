package pr2.hausarbeit;

public class StringElement implements Comparable<StringElement> {
	// Variablen deklarieren
	private String key;

	/**
	 * Konstruktor der Klasse StringElement. Benötigt um ein Element des Typs String
	 * einzulesen und abzuspeichern, damit man den Wert später verwenden kann.
	 * 
	 * @param key String Objekt wird in einer lokalen Variable abgespeichert.
	 */
	public StringElement(String key) {
		this.key = key;
	}

	@Override
	public int compareTo(StringElement key) {
		return this.key.compareTo(((StringElement) key).key);
	}

	@Override
	public String toString() {
		return this.key + "";
	}
}