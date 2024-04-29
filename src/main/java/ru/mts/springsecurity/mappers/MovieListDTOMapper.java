package ru.mts.springsecurity.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mts.springsecurity.DTO.MovieListDTO;
import ru.mts.springsecurity.entities.Movie;

import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MovieListDTOMapper implements Function<Movie, MovieListDTO> {
    private final GenreDTOMapper genreDTOMapper;

    @Override
    public MovieListDTO apply(Movie movie) {
        return new MovieListDTO(
                movie.getId(),
                movie.getTitle(),
                movie.getReleaseDate(),
                movie.getPosterPath(),
                movie.getAvgRating(),
                movie.getGenres().stream().map(genreDTOMapper).collect(Collectors.toSet()));
    }
}
