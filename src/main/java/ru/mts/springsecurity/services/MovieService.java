package ru.mts.springsecurity.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mts.springsecurity.DTO.MovieDTO;
import ru.mts.springsecurity.DTO.MovieListDTO;
import ru.mts.springsecurity.DTO.ReviewDTO;
import ru.mts.springsecurity.config.MyUserDetails;
import ru.mts.springsecurity.entities.Movie;
import ru.mts.springsecurity.entities.MyUser;
import ru.mts.springsecurity.entities.Review;
import ru.mts.springsecurity.mappers.MovieDTOMapper;
import ru.mts.springsecurity.mappers.MovieListDTOMapper;
import ru.mts.springsecurity.mappers.ReviewDTOMapper;
import ru.mts.springsecurity.repositories.MovieRepository;
import ru.mts.springsecurity.repositories.ReviewRepository;
import ru.mts.springsecurity.repositories.UserRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MovieService {
    private final MovieRepository movieRepository;
    private final MovieDTOMapper movieDTOMapper;
    private final MovieListDTOMapper movieListDTOMapper;
    private final ReviewDTOMapper reviewDTOMapper;
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;

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

    public List<ReviewDTO> listReviews(Integer id) {
        return movieRepository.findById(id).orElse(null).getReviews().stream()
                .map(reviewDTOMapper)
                .toList();
    }

    @Transactional
    public Integer saveReview(Integer movieId, Review review) {
        review.setCreatedAt(LocalDateTime.now());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
        int userId = userDetails.getId();

        MyUser user = userRepository.findById(userId).get();

        user.getReviews().add(review);
        review.setUser(user);
//        reviewRepository.save(review);

        Movie movie = movieRepository.findById(movieId).get();

        movie.getReviews().add(review);
        review.setMovie(movie);

//        movieRepository.save(movie);

        reviewRepository.save(review);

//        System.out.println(user.getReviews().isEmpty());
//        System.out.println(movie.getReviews().isEmpty());

        return movieId;
    }
}
