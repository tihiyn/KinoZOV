package ru.kinozov.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.kinozov.DTO.MovieDTO;
import ru.kinozov.DTO.MovieListDTO;
import ru.kinozov.DTO.ReviewInputDTO;
import ru.kinozov.DTO.ReviewOutputDTO;
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
    public List<MovieListDTO> getMovies(@RequestParam(name = "from", required = false) Integer from,
                                        @RequestParam(name = "limit", required = false) Integer limit,
                                        @RequestParam(name = "sort", required = false) String sort) {
        return movieService.getMovies(from, limit, sort);
    }

    @GetMapping("/movies/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public MovieDTO getMovieById(@PathVariable Integer id) {
        return movieService.getMovieById(id);
    }

    @GetMapping("/movies/{id}/reviews")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public List<ReviewOutputDTO> getReviews(@PathVariable Integer id,
                                            @RequestParam(name = "from", required = false) Integer from,
                                            @RequestParam(name = "limit", required = false) Integer limit) {
        return movieService.getReviews(id, from, limit);
    }

    @PostMapping("/movies/{id}/reviews")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public Integer createReview(@PathVariable Integer id,
                                @RequestBody ReviewInputDTO reviewInputDTO) {

        return movieService.createReview(id, reviewInputDTO);
    }

    @GetMapping("/movies/recommended")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public List<MovieListDTO> getRecommended() {
        return null;
    }
}
