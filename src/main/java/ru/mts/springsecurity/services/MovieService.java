package ru.mts.springsecurity.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.mts.springsecurity.DTO.MovieDTO;
import ru.mts.springsecurity.DTO.MovieListDTO;
import ru.mts.springsecurity.mappers.MovieDTOMapper;
import ru.mts.springsecurity.mappers.MovieListDTOMapper;
import ru.mts.springsecurity.repositories.MovieRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MovieService {
    private final MovieRepository movieRepository;
    private final MovieDTOMapper movieDTOMapper;
    private final MovieListDTOMapper movieListDTOMapper;


    public List<MovieListDTO> listMovies(Integer from, Integer limit, String sort) {
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
                PageRequest.of(from, limit, Sort.by(Sort.Direction.DESC, sort))).stream()
                .map(movieListDTOMapper).collect(Collectors.toList());
    }

    public MovieDTO getMovieById(Integer id) {
        return movieRepository.findById(id).map(movieDTOMapper).orElse(null);
    }
}
