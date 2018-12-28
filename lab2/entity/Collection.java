package entity;

import java.util.ArrayList;

//сборник разных песен разных артистов

public class Collection {

    private String name;
    private ArrayList<Song> songList;

    public Collection(String name) {
        this.name = name;
        songList = new ArrayList<>();
    }

    public void addSong(Song song) {
        this.songList.add(song);
    }

    public String getName() {
        return name;
    }

    public ArrayList<Song> getSongList() {
        return songList;
    }
}