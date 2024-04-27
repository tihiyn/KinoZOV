package ru.mts.springsecurity.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.mts.springsecurity.models.Movie;
import ru.mts.springsecurity.repositories.MovieRepository;

import java.util.HashSet;
import java.util.Set;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Page<Movie> setMovies(Integer from, Integer limit, String sort) {
        if (from == null) {
            from = 0;
        }
        if (from > movieRepository.count()) {
            return null;
        }
        if (limit == null) {
            limit = 10;
        }
        if (sort == null) {
            sort = "release_date";
        }

        return movieRepository.findAll(
                PageRequest.of(from, limit, Sort.by(Sort.Direction.DESC, sort)));
    }
}
