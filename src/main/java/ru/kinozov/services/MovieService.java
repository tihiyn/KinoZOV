package ru.kinozov.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kinozov.DTO.MovieDTO;
import ru.kinozov.DTO.MovieListDTO;
import ru.kinozov.DTO.ReviewDTO;
import ru.kinozov.config.MyUserDetails;
import ru.kinozov.entities.Movie;
import ru.kinozov.entities.MyUser;
import ru.kinozov.entities.Review;
import ru.kinozov.mappers.MovieDTOMapper;
import ru.kinozov.mappers.MovieListDTOMapper;
import ru.kinozov.repositories.MovieRepository;
import ru.kinozov.repositories.ReviewRepository;
import ru.kinozov.repositories.UserRepository;
import ru.kinozov.mappers.ReviewDTOMapper;

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

    private int getCurrentSessionUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
        return userDetails.getId();
    }

    @Transactional
    public Integer saveReview(Integer movieId, Review review) {
        review.setCreatedAt(LocalDateTime.now());

        MyUser user = userRepository.findById(getCurrentSessionUserId()).get();
        user.getReviews().add(review);
        review.setUser(user);

        Movie movie = movieRepository.findById(movieId).get();
        movie.getReviews().add(review);
        review.setMovie(movie);

        reviewRepository.save(review);

//        user.getReviews().stream().map(Review::getId).forEach(System.out::println);
//        movie.getReviews().stream().map(Review::getId).forEach(System.out::println);
//        System.out.println(user.getReviews().isEmpty());
//        System.out.println(movie.getReviews().isEmpty());

        return movieId;
    }
}
