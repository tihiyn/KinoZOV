package ru.mts.springsecurity.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "movie_crews")
@IdClass(MovieCrewId.class)
public class MovieCrew {
    @Id
    @ManyToOne
    @JoinColumn(name = "movie_id")
    @MapsId(value = "movie")
    private Movie movie;

    @Id
    @ManyToOne
    @JoinColumn(name = "person_id")
    @MapsId(value = "person")
    private Person person;

    @Id
    @Enumerated(EnumType.STRING)
    @Column(name = "person_job")
    @MapsId(value = "personJob")
    private Job job;

    public MovieCrew() {
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }
}
