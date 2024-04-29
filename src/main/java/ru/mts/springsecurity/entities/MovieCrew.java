package ru.mts.springsecurity.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
}
