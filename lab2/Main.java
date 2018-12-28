import entity.*;
import util.SearchEngine;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        Genre rock = new Genre("Rock");
        Genre punk = new Genre("Punk");
        Genre altRock = new Genre("Alternative rock", rock);
        Genre darkCabaret = new Genre("Dark cabaret", punk);

        Artist louna = new Artist("Louna");
        Album weAreLouna = new Album("Мы - это Louna", louna, 2013);
        Song orwell = new Song("1.9.8.4", louna, weAreLouna, rock);

        Album timeX = new Album("Время X", louna, 2012);
        Song timex = new Song("Время X", louna, timeX, rock);
        Song stormingHeavens = new Song("Штурмуя небеса", louna, timeX, rock);

        Artist linkinPark = new Artist("Linkin Park");
        Album wastelands = new Album("Wastelands", linkinPark, 2014);
        Song wstl = new Song("Wastelands", linkinPark, wastelands, altRock);
        Song untilItsGone = new Song("Until It's Gone", linkinPark, wastelands, altRock);

        Artist theTigerLillies = new Artist("The Tiger Lillies");
        Album coldNightInSoho = new Album("Cold Night in Soho", theTigerLillies, 2017);
        Song heroin = new Song("Heroin", theTigerLillies, coldNightInSoho, altRock);

        Artist splin = new Artist("Сплин");
        Album garnetAlbum = new Album("Гранатовый альбом", splin, 1998);
        Song noExit = new Song("Выхода нет", splin, garnetAlbum, rock);
        Song allThisNonsense = new Song("Весь этот бред", splin, garnetAlbum, rock);
        Song godIsTiredLovingUs = new Song("Бог устал нас любить", splin, garnetAlbum, rock);

        Catalog catalog = new Catalog("Russian songs");

        catalog.addArtistSongs(splin);
        catalog.addArtistSongs(louna);

        Collection collection = new Collection("Chaos");
        collection.addSong(noExit);
        collection.addSong(wstl);
        collection.addSong(timex);

        catalog.addCollection(collection);

        List<Song> songs = new SearchEngine().findSongsByGenre(catalog, rock);
        for (Song song: songs){
            System.out.println(song.getName() + " " + song.getAlbum().getName()+ " " + song.getGenre().getName());
        }

        System.out.println("\n");

        List<Album> album = new SearchEngine().findAlbumByYear(catalog, 1998);
        for (Album alb: album){
            System.out.println("Album name: " + alb.getName() + " 1998");
        }

        System.out.println("\n");

        List<Album> album1 = new SearchEngine().findAlbumByGenre(catalog, rock);
        for (Album alb: album1){
            System.out.println("Album name: " + alb.getName() + " " + rock.getName());
        }

        System.out.println("\n");

        List<Song> songs1 = new SearchEngine().findSongsByYear(catalog, 1998);
        for (Song song: songs1){
            System.out.println(song.getName() + " " + 1998);
        }

        System.out.println("\n");

        List<Collection> collections = new SearchEngine().findSongsCollectionBySong(catalog, noExit);
        for (Collection coll: collections){
            System.out.println(coll.getName());
        }

        System.out.println("\n");

        List<Song> songs2 = new SearchEngine().findSongsByAlbum(catalog, wastelands);
        for (Song song: songs2){
            System.out.println(song.getName() + " " + wastelands.getName());
        }
    }

}