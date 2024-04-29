package ru.mts.springsecurity.mappers;

import org.springframework.stereotype.Service;
import ru.mts.springsecurity.DTO.MovieDTO;
import ru.mts.springsecurity.entities.Movie;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class MovieDTOMapper implements Function<Movie, MovieDTO> {
    private final GenreDTOMapper genreDTOMapper;

    public MovieDTOMapper(GenreDTOMapper genreDTOMapper) {
        this.genreDTOMapper = genreDTOMapper;
    }

    @Override
    public MovieDTO apply(Movie movie) {
        return new MovieDTO(
                movie.getId(),
                movie.getTitle(),
                movie.getTagLine(),
                movie.getSynopsis(),
                movie.getReleaseDate(),
                movie.getPosterPath(),
                movie.getAvgRating(),
                movie.getGenres().stream().map(genreDTOMapper).collect(Collectors.toSet()));
    }
}
