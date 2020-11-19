package pr2.uebung04;

public interface Song extends Comparable {

	/**
	 * Liefert den Namen des Songs
	 */
	String getSongName();

	/**
	 * Liefert den Namen des zum Song gehörigen Albums. Die Methode wird nur zu
	 * Testzwecken benötigt!
	 */
	String getAlbumName();

	/**
	 * Liefert ein passend dimensioniertes Array mit einem oder mehreren
	 * Künstlern/Bands. Die Methode wird nur zu Testzwecken benötigt!
	 */
	String[] getArtists();

	/**
	 * Liefert eine gut lesbare Darstellung des Songs
	 */
	String toString();
	
	/**
	 * Vergleicht die Song-Namen des zu übergebenden Song-Elements.
	 */
	int compareTo(Object object);

}
