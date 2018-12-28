package entity;

public class Genre {
    private String name;
    private Genre mainGenre;

    public Genre(String name) {
        this.name = name;
        this.mainGenre = null;
    }

    public Genre(String name, Genre mainGenre) {
        this.name = name;
        this.mainGenre = mainGenre;
    }

    public String getName() {
        return name;
    }

    public Genre getMainGenre() {
        return mainGenre;
    }

    public boolean isMainGenreOf(Genre genre) {
        Genre bufGenre = genre.mainGenre;
        while (bufGenre != null)
        {
            if (bufGenre == this) return true;
            bufGenre = bufGenre.mainGenre;
        }
        return false;
    }
}