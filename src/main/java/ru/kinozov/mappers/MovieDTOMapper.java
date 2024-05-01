package ru.kinozov.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kinozov.DTO.MovieDTO;
import ru.kinozov.entities.Movie;

import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MovieDTOMapper implements Function<Movie, MovieDTO> {
    private final GenreDTOMapper genreDTOMapper;

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
