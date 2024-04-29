package ru.mts.springsecurity.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @Column(name = "movie_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "tagline")
    private String tagLine;
    @Column(name = "synopsis")
    private String synopsis;
    @Column(name = "release_date")
    private LocalDate releaseDate;
    @Column(name = "poster_path")
    private String posterPath;
    @Column(name = "avg_rating")
    private float avgRating;
    @ManyToMany(mappedBy = "movies")
    private Set<Genre> genres = new HashSet<>();
    @OneToMany(mappedBy = "movie")
    private Set<MovieCrew> crews = new HashSet<>();
    @OneToMany(mappedBy = "movie")
    private Set<Review> reviews;

    public Movie() {
    }

    public Movie(String title, String tagLine, String synopsis, LocalDate releaseDate, String posterPath, float avgRating) {
        this.title = title;
        this.tagLine = tagLine;
        this.synopsis = synopsis;
        this.releaseDate = releaseDate;
        this.posterPath = posterPath;
        this.avgRating = avgRating;
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

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public Set<MovieCrew> getCrews() {
        return crews;
    }

    public void setCrews(Set<MovieCrew> crews) {
        this.crews = crews;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }
}
