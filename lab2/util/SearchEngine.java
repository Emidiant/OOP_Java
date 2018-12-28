package util;

import entity.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class SearchEngine {

    //поиск песен по критериям

    public ArrayList<Song> findSongsByArtist(Catalog catalog, Artist artist) {
        return artist.getSongList();
    }

    public ArrayList<Song> findSongsByAlbum(Catalog catalog, Album album) {
        return album.getSongList();
    }

    public ArrayList<Song> findSongsByCollection(Catalog catalog, Collection collection) {
        return collection.getSongList();
    }

    public static ArrayList<Song> findSongsByGenre(Catalog catalog, Genre genre) {
        ArrayList<Song> songs = new ArrayList<>();
        for (Song song : catalog.getSongList()) {
            if ((song.getGenre() == genre) || genre.isMainGenreOf(song.getGenre())) {
                songs.add(song);
            }
        }
        return songs;
    }

    public ArrayList<Song> findSongsByYear(Catalog catalog, int year) {
        ArrayList<Song> searchList = new ArrayList<>();

        for (Album album : catalog.getAlbumList()) {
            if (album.getYear().equals(year)) {
                searchList.addAll(album.getSongList());
            }
        }
        return searchList;
    }

    //поиск артистов по критериям

    public Artist findArtistBySong(Song song) {
        return song.getArtist();
    }

    public Artist findArtistByAlbum(Album album) {
        return album.getArtist();
    }

    public ArrayList<Artist> findArtistsByGenre(Catalog catalog, Genre genre) {
        ArrayList<Artist> searchList = new ArrayList<>();
        for (Song song : catalog.getSongList()) {
            if (song.getGenre().equals(genre) || genre.isMainGenreOf(song.getGenre())) {
                searchList.add(song.getArtist());
            }
        }
        return searchList;
    }

    public ArrayList<Artist> findArtistsBySongCollection(Collection collection) {
        ArrayList<Artist> searchList = new ArrayList<>();
        for (Song song : collection.getSongList()) {
            if (!searchList.contains(song.getArtist())) {
                searchList.add(song.getArtist());
            }
        }
        return searchList;
    }

    //поиск альбомов по критериям

    public ArrayList<Album> findAlbumsByArtist(Artist artist) {
        ArrayList<Album> searchList = new ArrayList<>();
        for (Song song : artist.getSongList()) {
            if (song.getArtist() == artist) {
                searchList.add(song.getAlbum());
            }
        }
        Set<Album> set = new HashSet<>(searchList);//удаление повторяющихся альбомов
        searchList.clear();
        searchList.addAll(set);
        return searchList;
    }

    public Album findAlbumBySong(Song song) {
        return song.getAlbum();
    }

    public ArrayList<Album> findAlbumByGenre(Catalog catalog, Genre genre){
        ArrayList<Album> searchList = new ArrayList<>();
        for (Song song : catalog.getSongList()) {
            if (song.getGenre() == genre) {
                searchList.add(song.getAlbum());
            }
        }
        Set<Album> set = new HashSet<>(searchList);
        searchList.clear();
        searchList.addAll(set);
        return searchList;
    }

    public ArrayList<Album> findAlbumByYear(Catalog catalog, Integer year){
        ArrayList<Album> searchList = new ArrayList<>();
        for (Album album : catalog.getAlbumList()) {
            if (album.getYear().equals(year)) {
                searchList.add(album);
            }
        }
        return searchList;
    }

    //поиск сборников по критериям, не знаю есть ли смысл делать больше критериев, но всегда можно добавить год, жанр, исполнитель...
    //просто сборник - это хаос треков...

    public ArrayList<Collection> findSongsCollectionBySong(Catalog catalog, Song song) {
        ArrayList<Collection> searchList = new ArrayList<>();
        for (Collection collection : catalog.getCollectionList()) {
            if (collection.getSongList().contains(song)) {
                searchList.add(collection);
            }
        }
        return searchList;
    }
}