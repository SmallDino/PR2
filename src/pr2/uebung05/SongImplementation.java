package pr2.uebung05;

public class SongImplementation implements Song {

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
		return this.name.compareTo(((Song) s).getSongName());
	}

	@Override
	public boolean equals(Object s) {
		return this.name.equals(((Song) s).getSongName()) && this.albumName.equals(((Song) s).getAlbumName())
				&& this.artists[0].equals(((Song) s).getArtists()[0]);

		// this.name.compareTo(((Song)s).getSongName()) == 0 &&
		// this.name.compareTo(((Song)s).getAlbumName()) == 0; // &&
		// this.name.compareTo(((Song)s).getArtists()[0]) == 0;
	}

	@Override
	public String toString() {
		// String s = name + "\n";
		// s += " Künstler: ";
		// for (int i = 0; i < artists.length - 1; i++)
		// s += artists[i] + ", ";
		// s += artists[artists.length - 1] + "\n";
		// s += " Album: " + albumName;
		// return s;

		// short form of output: 1 line per song
		String s = name + "; ";
		for (int i = 0; i < artists.length - 1; i++)
			s += artists[i] + ", ";
		s += artists[artists.length - 1] + "; ";
		s += albumName + "\n";
		return s;
	}
	
	public int hashCode() {
		String newString = this.name + this.albumName;
		int hashCode = 0;
		
		for(int i = 0; i < newString.length(); i++) {
			hashCode += newString.charAt(i);
		}
		return hashCode;
	}

	@Override
	public String getSongName() {
		return name;
	}

	public String[] getArtists() {
		return artists;
	}

	public String getAlbumName() {
		return albumName;
	}
}