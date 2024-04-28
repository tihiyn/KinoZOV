package ru.mts.springsecurity.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.bind.annotation.*;
import ru.mts.springsecurity.models.Movie;
import ru.mts.springsecurity.repositories.GenreRepository;
import ru.mts.springsecurity.services.MovieService;
import ru.mts.springsecurity.views.Views;

import java.util.List;

@RestController
@RequestMapping("")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movies")
    @JsonView(Views.ShortInfo.class)
    public List<Movie> movies(@RequestParam(name = "from", required = false) Integer from,
                              @RequestParam(name = "limit", required = false) Integer limit,
                              @RequestParam(name = "sort", required = false) String sort) {
        return movieService.listMovies(from, limit, sort);
    };

    @GetMapping("/movie/{id}")
    @JsonView(Views.LongInfo.class)
    public Movie movieInfo(@PathVariable Integer id) {
        return movieService.getMovieById(id);
    }
}
