package ru.mts.springsecurity.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import ru.mts.springsecurity.views.Views;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @Column(name = "movie_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonView({Views.LongInfo.class, Views.ShortInfo.class})
    private int id;
    @Column(name = "title")
    @JsonView({Views.LongInfo.class, Views.ShortInfo.class})
    private String title;
    @Column(name = "tagline")
    @JsonView(Views.LongInfo.class)
    private String tagLine;
    @Column(name = "synopsis")
    @JsonView(Views.LongInfo.class)
    private String synopsis;
    @Column(name = "release_date")
    @JsonView({Views.LongInfo.class, Views.ShortInfo.class})
    private LocalDate releaseDate;
    @Column(name = "poster_path")
    @JsonView({Views.LongInfo.class, Views.ShortInfo.class})
    private String posterPath;
    @Column(name = "avg_rating")
    @JsonView({Views.LongInfo.class, Views.ShortInfo.class})
    private float avgRating;
    @ManyToMany(mappedBy = "movies")
    @JsonView({Views.LongInfo.class, Views.ShortInfo.class})
    private Set<Genre> genres = new HashSet<>();
    @OneToMany(mappedBy = "movie")
    @JsonIgnore
    private Set<MovieCrew> crews = new HashSet<>();
    @OneToMany(mappedBy = "movie")
    @JsonIgnore
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
