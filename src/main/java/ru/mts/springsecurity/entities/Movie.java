package ru.mts.springsecurity.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
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

    public Movie(String title, String tagLine, String synopsis, LocalDate releaseDate, String posterPath, float avgRating) {
        this.title = title;
        this.tagLine = tagLine;
        this.synopsis = synopsis;
        this.releaseDate = releaseDate;
        this.posterPath = posterPath;
        this.avgRating = avgRating;
    }
}
