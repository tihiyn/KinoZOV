package ru.kinozov.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import ru.kinozov.DTO.MovieDTO;
import ru.kinozov.DTO.MovieListDTO;
import ru.kinozov.DTO.ReviewInputDTO;
import ru.kinozov.DTO.ReviewOutputDTO;
import ru.kinozov.entities.Review;
import ru.kinozov.services.MovieService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Tag(name = "movie_methods")
@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @GetMapping()
    public List<MovieListDTO> getMovies(@RequestParam(name = "from", required = false) Integer from,
                                        @RequestParam(name = "limit", required = false) Integer limit,
                                        @RequestParam(name = "sort", required = false) String sort) {
        return movieService.getMovies(from, limit, sort);
    }

    @GetMapping("/{id}")
    public MovieDTO getMovieById(@PathVariable Integer id) {
        return movieService.getMovieById(id);
    }

    @GetMapping("/{id}/reviews")
    public List<ReviewOutputDTO> getReviews(@PathVariable Integer id,
                                            @RequestParam(name = "from", required = false) Integer from,
                                            @RequestParam(name = "limit", required = false) Integer limit) {
        return movieService.getReviews(id, from, limit);
    }

    @PostMapping("/{id}/reviews")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public Integer createReview(@PathVariable Integer id,
                                @Valid @RequestBody ReviewInputDTO reviewInputDTO) {

        return movieService.createReview(id, reviewInputDTO);
    }

    @GetMapping("/recommended")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public List<MovieListDTO> getRecommended() {
        return null;
    }
}
