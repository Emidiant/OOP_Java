package entity;

import util.SearchEngine;

import java.util.ArrayList;

public class Catalog {
    private String name;
    private ArrayList<Album> albumList;
    private ArrayList<Song> songList;
    private ArrayList<Collection> collectionList;

    public Catalog(String name) {
        this.name = name;
        this.songList = new ArrayList<>();
        this.collectionList = new ArrayList<>();
        this.albumList = new ArrayList<>();
    }

    void addAlbum(Album alb){
        albumList.add(alb);
    }

    void addSong(Song song) {
        this.songList.add(song);
        addAlbum(song.getAlbum());
    }

    public void addArtistSongs(Artist artist) {
        ArrayList<Album> albums = new SearchEngine().findAlbumsByArtist(artist);
        for (Album album : albums){
            addAlbum(album);
        }
        songList.addAll(artist.getSongList());
    }

    public void addCollection(Collection collection){
        collectionList.add(collection);
    }

    public ArrayList<Collection> getCollectionList() {
        return collectionList;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Song> getSongList() {
        return songList;
    }

    public ArrayList<Album> getAlbumList(){
        return albumList;
    }
}