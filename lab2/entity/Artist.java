package entity;

import java.util.ArrayList;

public class Artist {
    private String name;
    private ArrayList<Song> songList;

    public Artist(String name) {
        this.name = name;
        this.songList = new ArrayList<>();
    }

    void addSong(Song song) {
        this.songList.add(song);
    }

    public ArrayList<Song> getSongList() {
        return songList;
    }

    public String getName() {
        return name;
    }

}