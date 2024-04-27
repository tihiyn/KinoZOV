package ru.mts.springsecurity.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mts.springsecurity.models.Genre;
import ru.mts.springsecurity.repositories.GenreRepository;
import ru.mts.springsecurity.services.MovieService;

@RestController
@RequestMapping("")
public class MovieController {
    private final MovieService movieService;

    private final GenreRepository genreRepository;

    public MovieController(MovieService movieService, GenreRepository genreRepository) {
        this.movieService = movieService;
        this.genreRepository = genreRepository;
    }

//    @GetMapping("/movies")
//    public String movies(@RequestParam(name = "from", required = false) Integer from,
//                         @RequestParam(name = "limit", required = false) Integer limit,
//                         @RequestParam(name = "sort", required = false) String sort, Model model) {
//        model.addAttribute("movies", movieService.setMovies(from, limit, sort));
//        return "movies";
//    };
//
//    @GetMapping("/genres/{id}")
//    public String genres(@PathVariable Integer id) {
//        return genreRepository.findById(id).orElse(null).getName();
//    }
}
