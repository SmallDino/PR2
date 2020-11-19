package pr2.hausarbeit;

public class SongImplementation<E> implements Song, Comparable<Song>   {
	
	private String name;
	private String[] artists;
	private String albumName;
	
	public SongImplementation(String name, String[] artists, String albumName) {
		this.name = name;
		this.artists = artists;
		this.albumName = albumName;
	}
	
	@Override
	public int compareTo(Song s) {
		return this.name.compareTo(((Song)s).getSongName());
	}
	
	@Override
	public boolean equals (Object s) {
		return
				this.name.equals(((Song)s).getSongName()) &&
                this.albumName.equals(((Song)s).getAlbumName()) &&
                this.artists[0].equals(((Song)s).getArtists()[0]);
	}

	@Override
	public String toString() {  // hier kann man das angeben, was man möchte
//		String s = name + "\n";
//		s += "  Künstler: ";
//		for (int i = 0; i < artists.length - 1; i++)
//			s += artists[i] + ", ";
//		s += artists[artists.length - 1] + "\n";
//		s += "  Album:    " + albumName;
//		return s;
		
	    // short form of output: 1 line per song
        String s = name;  // für verkürzte Ausgabe
//        String s = name + "; ";
//        for (int i = 0; i < artists.length - 1; i++)
//            s += artists[i] + ", ";
//        s += artists[artists.length - 1] + "; ";
//        s += albumName + "\n";
        return s + "";
	}

	@Override
	public String getSongName() {
		return this.name;
	}

	public String[] getArtists() {
		return this.artists;
	}

	public String getAlbumName() {
		return this.albumName;
	}
}