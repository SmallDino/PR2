package pr2.uebung03;

public class StringElement implements Element {
	// Variablen deklarieren
	private String val;

	/**
	 * Konstruktor der Klasse StringElement. Benötigt um ein Element des Typs String
	 * einzulesen und abzuspeichern, damit man den Wert später verwenden kann.
	 * 
	 * @param element Element des Datentyps String wird in einer lokalen Variable
	 *                abgespeichert.
	 */
	public StringElement(String element) {
		this.val = element;
	}

	@Override
	public int compareTo(Element e) {
		StringElement se = (StringElement) e;
		return this.val.compareTo(se.val);		
	}
	
    @Override
    public boolean equals(Object o) {
        if(o == null)
            return false;
        if(!(o instanceof StringElement))
            return false;
        return (this.compareTo((Element) o) == 0);
    }

	@Override
	public Object getKey() {
		return this.val;
	}
	
	@Override
	public Element clone() {
		Element temp = new StringElement((String) this.getKey());
		return temp;
	}
	
	@Override
	public int hashCode() {
		int hashCode = 0;
		for(int i = 0; i < this.val.length(); i++) {
			hashCode += this.val.charAt(i);
		}
		return hashCode;
	}

	@Override
	public String toString() {
		return this.val + " ";
	}
}
