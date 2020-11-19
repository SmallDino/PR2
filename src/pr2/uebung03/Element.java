
package pr2.uebung03;

public interface Element {

	/**
	 * CompareTo Methode, welche das aktuelle Element mit dem übergebenen Element vergleicht.
	 * 
	 * @param e Übergebenes Element, welches zum Vergleich benötigt wird.
	 * @return Gibt 0 zurück, wenn das aktuelle Element mit dem übergebenen Element übereinstimmt.
	 * Gibt -1 zurück, wenn das aktuelle Element kleiner ist, als das übergebene Element.
	 * Gibt 1 zurück, wenn das aktuelle Element größer ist, als das übergebene Element.
	 */
	public int compareTo(Element e);

	public Element clone();
	
	public Object getKey();
	
	public int hashCode();
}
