package entity;

public class Song {
    private String name;
    private Artist artist;
    private Album album;
    private Genre genre;

    public Song(String name, Artist artist, Album album, Genre genre) {
        this.name = name;
        this.artist = artist;
        this.album = album;
        this.genre = genre;
        album.addSong(this);
        artist.addSong(this);

    }

    public Artist getArtist() {
        return artist;
    }

    public Album getAlbum() {
        return album;
    }

    public Genre getGenre() {
        return genre;
    }

    public String getName() {
        return name;
    }
}