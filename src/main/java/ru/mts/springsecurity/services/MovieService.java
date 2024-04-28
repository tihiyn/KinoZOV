package ru.mts.springsecurity.services;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.mts.springsecurity.models.Movie;
import ru.mts.springsecurity.repositories.MovieRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> listMovies(Integer from, Integer limit, String sort) {
        if (from == null) {
            from = 0;
        }
        if (from > movieRepository.count()) {
            return new ArrayList<>();
        }
        if (limit == null) {
            limit = 10;
        }
        if (sort == null) {
            sort = "releaseDate";
        }

        return movieRepository.findAll(
                PageRequest.of(from, limit, Sort.by(Sort.Direction.DESC, sort))).toList();
    }

    public Movie getMovieById(Integer id) {
        return movieRepository.findById(id).orElse(null);
    }
}
