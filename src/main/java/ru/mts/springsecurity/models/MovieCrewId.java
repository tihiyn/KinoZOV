package ru.mts.springsecurity.models;

import java.io.Serializable;
import java.util.Objects;

public class MovieCrewId implements Serializable {
    private Integer movie;
    private Integer person;
    private Job job;

    public MovieCrewId() {
    }

    public MovieCrewId(Integer movie, Integer person, Job personJob) {
        this.movie = movie;
        this.person = person;
        this.job = personJob;
    }

    public Integer getMovie() {
        return movie;
    }

    public void setMovie(Integer movie) {
        this.movie = movie;
    }

    public Integer getPerson() {
        return person;
    }

    public void setPerson(Integer person) {
        this.person = person;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieCrewId that = (MovieCrewId) o;
        return Objects.equals(movie, that.movie) && Objects.equals(person, that.person) && Objects.equals(job, that.job);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movie, person, job);
    }
}
