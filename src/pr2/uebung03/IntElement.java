package pr2.uebung03;

public class IntElement implements Element {
	// Variablen deklarieren
	private int val;
	
	/**
	 * Konstruktor der Klasse IntElement. Benötigt um ein Element des Typs Integer einzulesen und abzuspeichern, damit man den Wert später verwenden kann.
	 * @param element Element des Datentyps Integer wird in einer lokalen Variable abgespeichert.
	 */
	public IntElement(int element) {
		this.val = element;
	}
	
	@Override
	public int compareTo(Element e) {
        IntElement ei = (IntElement)e;
        if(this.val == ei.val) {
                return 0;
        } else if (this.val < ei.val) {
                return -1;
        } else {
                return 1;
        }
	};

	@Override
	public Object getKey() {
		return val;
	}
	
	@Override
	public Element clone() {
		Element temp=new IntElement((Integer)this.getKey());
		return temp;
	}
	
	
	@Override
	public String toString() {
		return this.val + " ";
	}
	
}