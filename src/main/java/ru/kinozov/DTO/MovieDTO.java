package ru.kinozov.DTO;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class MovieDTO {
    private int id;
    private String title;
    private String tagLine;
    private String synopsis;
    private LocalDate releaseDate;
    private String posterPath;
    private float avgRating;
    private Set<GenreDTO> genres;
}
