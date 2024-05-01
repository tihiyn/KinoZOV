package ru.kinozov.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.kinozov.DTO.MovieDTO;
import ru.kinozov.DTO.MovieListDTO;
import ru.kinozov.DTO.ReviewDTO;
import ru.kinozov.entities.Review;
import ru.kinozov.services.MovieService;

import java.util.List;

@Tag(name = "movie_methods")
@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

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

    @PostMapping("/movies/{id}/reviews")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public Integer createReview(@PathVariable Integer id,
                                @RequestBody Review review) {

        return movieService.saveReview(id, review);
    }
}
