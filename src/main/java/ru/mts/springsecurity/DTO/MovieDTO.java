package ru.mts.springsecurity.DTO;

import ru.mts.springsecurity.entities.Genre;

import java.time.LocalDate;
import java.util.Set;

public class MovieDTO {
    private int id;
    private String title;
    private String tagLine;
    private String synopsis;
    private LocalDate releaseDate;
    private String posterPath;
    private float avgRating;
    private Set<GenreDTO> genres;

    public MovieDTO(int id, String title, String tagLine, String synopsis, LocalDate releaseDate, String posterPath, float avgRating, Set<GenreDTO> genres) {
        this.id = id;
        this.title = title;
        this.tagLine = tagLine;
        this.synopsis = synopsis;
        this.releaseDate = releaseDate;
        this.posterPath = posterPath;
        this.avgRating = avgRating;
        this.genres = genres;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTagLine() {
        return tagLine;
    }

    public void setTagLine(String tagLine) {
        this.tagLine = tagLine;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public float getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(float avgRating) {
        this.avgRating = avgRating;
    }

    public Set<GenreDTO> getGenres() {
        return genres;
    }

    public void setGenres(Set<GenreDTO> genres) {
        this.genres = genres;
    }
}
