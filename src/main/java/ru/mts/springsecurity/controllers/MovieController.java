package ru.mts.springsecurity.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.mts.springsecurity.DTO.MovieDTO;
import ru.mts.springsecurity.DTO.MovieListDTO;
import ru.mts.springsecurity.DTO.ReviewDTO;
import ru.mts.springsecurity.services.MovieService;

import java.util.List;

@RestController
@RequestMapping("")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movies")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public List<MovieListDTO> movies(@RequestParam(name = "from", required = false) Integer from,
                                     @RequestParam(name = "limit", required = false) Integer limit,
                                     @RequestParam(name = "sort", required = false) String sort) {
        return movieService.listMovies(from, limit, sort);
    }

    @GetMapping("/movies/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public MovieDTO movieInfo(@PathVariable Integer id) {
        return movieService.getMovieById(id);
    }

    @GetMapping("/movies/{id}/reviews")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public List<ReviewDTO> movieReviews(@PathVariable Integer id) {
        return movieService.listReviews(id);
    }
}
