package ru.mts.springsecurity.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.mts.springsecurity.entities.Genre;

import java.time.LocalDate;
import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
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
