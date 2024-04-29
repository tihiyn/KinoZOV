package ru.mts.springsecurity.DTO;

import ru.mts.springsecurity.entities.Genre;

import java.time.LocalDate;
import java.util.Set;

public class MovieListDTO {
    private int id;
    private String title;
    private LocalDate releaseDate;
    private String posterPath;
    private float avgRating;
    private Set<GenreDTO> genres;

    public MovieListDTO(int id, String title, LocalDate releaseDate, String posterPath, float avgRating, Set<GenreDTO> genres) {
        this.id = id;
        this.title = title;
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
