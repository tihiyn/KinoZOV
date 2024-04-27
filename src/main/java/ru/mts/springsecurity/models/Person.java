package ru.mts.springsecurity.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "persons")
public class Person {
    @Id
    @Column(name = "person_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "person")
    private Set<MovieCrew> movieCrews = new HashSet<>();

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<MovieCrew> getMovieCrews() {
        return movieCrews;
    }

    public void setMovieCrews(Set<MovieCrew> movieCrews) {
        this.movieCrews = movieCrews;
    }
}
