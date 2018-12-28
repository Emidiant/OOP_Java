package entity;

import java.util.ArrayList;

//альбом содержит треки одного артиста

public class Album {
    private String name;
    private Artist artist;
    private Integer year;
    private ArrayList<Song> songList;

    public Album(String name, Artist artist, Integer year) {
        this.name = name;
        this.artist = artist;
        this.year = year;
        songList = new ArrayList<>();
    }

    void addSong(Song song) {
        this.songList.add(song);
    }

    public Artist getArtist() {
        return artist;
    }

    public String getName() {
        return name;
    }

    public Integer getYear(){
        return year;
    }

    public ArrayList<Song> getSongList() {
        return songList;
    }
}