package ru.kinozov.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ActiveProfiles;
import ru.kinozov.DTO.ReviewInputDTO;
import ru.kinozov.DTO.ReviewOutputDTO;
import ru.kinozov.config.MyUserDetails;
import ru.kinozov.entities.Movie;
import ru.kinozov.entities.MyUser;
import ru.kinozov.entities.Review;
import ru.kinozov.mappers.MovieDTOMapper;
import ru.kinozov.mappers.MovieListDTOMapper;
import ru.kinozov.mappers.ReviewDTOMapper;
import ru.kinozov.mappers.ReviewInputDTOToReviewMapper;
import ru.kinozov.repositories.MovieRepository;
import ru.kinozov.repositories.ReviewRepository;
import ru.kinozov.repositories.UserRepository;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@DisplayName("Class for testing MovieService")
@SpringBootTest
@ActiveProfiles("test")
public class MovieServiceTest {
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    MovieListDTOMapper movieListDTOMapper;
    @Autowired
    MovieDTOMapper movieDTOMapper;
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    ReviewDTOMapper reviewDTOMapper;
    @Autowired
    ReviewInputDTOToReviewMapper reviewInputDTOToReviewMapper;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private MovieService movieService;
    @Mock
    private SecurityContext securityContext;

    @Mock
    private Authentication authentication;

    @DisplayName("Test method getMovies")
    @ParameterizedTest(name = "Test {arguments}")
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7})
    public void getMovies(int value) {
        List<Movie> movies;
        List<Movie> moviesSortedByReleaseDate;
        List<Movie> moviesSortedByAlphabet;
        List<Movie> moviesSortedBtAvgRating;

        Movie movie1 = new Movie("Брат", "tagLine1", "synopsis1", LocalDate.of(1994, 9, 23), "posterPath1", 8.7f);
        Movie movie2 = new Movie("Джанго освобождённый", "tagLine2", "synopsis2", LocalDate.of(2003, 2, 4), "posterPath2", 6.4f);
        Movie movie3 = new Movie("Тачки", "tagLine3", "synopsis3", LocalDate.of(1997, 6, 14), "posterPath3", 7.3f);
        Movie movie4 = new Movie("Американский психопат", "tagLine4", "synopsis4", LocalDate.of(2012, 1, 31), "posterPath4", 6.5f);
        Movie movie5 = new Movie("Барби", "tagLine5", "synopsis5", LocalDate.of(2008, 12, 18), "posterPath5", 5.9f);
        Movie movie6 = new Movie("Холодное сердце", "tagLine6", "synopsis6", LocalDate.of(1986, 2, 7), "posterPath6", 7.8f);
        Movie movie7 = new Movie("Солярис", "tagLine7", "synopsis7", LocalDate.of(1992, 4, 11), "posterPath7", 8.0f);
        Movie movie8 = new Movie("Дом, который построил Джек", "tagLine8", "synopsis8", LocalDate.of(1975, 3, 2), "posterPath8", 8.2f);
        Movie movie9 = new Movie("Большой Лебовски", "tagLine9", "synopsis9", LocalDate.of(2000, 10, 22), "posterPath9", 6.3f);
        Movie movie10 = new Movie("Сумерки", "tagLine10", "synopsis10", LocalDate.of(2018, 5, 17), "posterPath10", 7.1f);
        movies = List.of(movie1, movie2, movie3, movie4, movie5, movie6, movie7, movie8, movie9, movie10);

        movieRepository.saveAll(movies);

        switch (value) {
            case 0 -> {
                moviesSortedByReleaseDate = List.of(movie10, movie4, movie5, movie2, movie9, movie3, movie1, movie7, movie6, movie8);
                assertEquals(moviesSortedByReleaseDate.stream().map(movieListDTOMapper).collect(Collectors.toList()),
                        movieService.getMovies(0, 10, "releaseDate"));
            }
            case 1 -> {
                moviesSortedByAlphabet = List.of(movie3, movie10, movie7, movie8);
                assertEquals(moviesSortedByAlphabet.stream().map(movieListDTOMapper).collect(Collectors.toList()),
                        movieService.getMovies(1, 4, "alphabet"));
            }
            case 2 -> {
                moviesSortedBtAvgRating = List.of(movie1, movie8, movie7, movie6, movie3, movie10, movie4, movie2, movie9, movie5);
                assertEquals(moviesSortedBtAvgRating.stream().map(movieListDTOMapper).collect(Collectors.toList()),
                        movieService.getMovies(0, 10, "avgRating"));
            }
            case 3 -> {
                assertEquals(new ArrayList<>(), movieService.getMovies(15, 20, "releaseDate"));
            }
            case 4 -> {
                moviesSortedByAlphabet = List.of(movie6, movie3, movie10, movie7);
                assertEquals(moviesSortedByAlphabet.stream().map(movieListDTOMapper).collect(Collectors.toList()),
                        movieService.getMovies(null, 4, "alphabet"));
            }
            case 5 -> {
                moviesSortedByAlphabet = List.of(movie9, movie5, movie4);
                assertEquals(moviesSortedByAlphabet.stream().map(movieListDTOMapper).collect(Collectors.toList()),
                        movieService.getMovies(7, null, "alphabet"));
            }
            case 6 -> {
                moviesSortedByReleaseDate = List.of(movie10, movie4, movie5, movie2, movie9, movie3, movie1, movie7, movie6, movie8);
                assertEquals(moviesSortedByReleaseDate.stream().map(movieListDTOMapper).collect(Collectors.toList()),
                        movieService.getMovies(0, 10, null));
            }
            case 7 -> {
                moviesSortedByReleaseDate = List.of(movie10, movie4, movie5, movie2, movie9, movie3, movie1, movie7, movie6, movie8);
                assertEquals(moviesSortedByReleaseDate.stream().map(movieListDTOMapper).collect(Collectors.toList()),
                        movieService.getMovies(null, null, null));
            }
        }

        movieRepository.deleteAll();
    }

    @DisplayName("Test method getMovieById")
    @ParameterizedTest(name = "Test {arguments}")
    @ValueSource(ints = {0, 1, 2, 3})
    public void getMovieById(int value) {
        List<Movie> movies;
        List<Integer> idList = new ArrayList<>();

        Movie movie1 = new Movie("Брат", "tagLine1", "synopsis1", LocalDate.of(1994, 9, 23), "posterPath1", 8.7f);
        Movie movie2 = new Movie("Джанго освобождённый", "tagLine2", "synopsis2", LocalDate.of(2003, 2, 4), "posterPath2", 6.4f);
        Movie movie3 = new Movie("Тачки", "tagLine3", "synopsis3", LocalDate.of(1997, 6, 14), "posterPath3", 7.3f);
        Movie movie4 = new Movie("Американский психопат", "tagLine4", "synopsis4", LocalDate.of(2012, 1, 31), "posterPath4", 6.5f);
        Movie movie5 = new Movie("Барби", "tagLine5", "synopsis5", LocalDate.of(2008, 12, 18), "posterPath5", 5.9f);
        Movie movie6 = new Movie("Холодное сердце", "tagLine6", "synopsis6", LocalDate.of(1986, 2, 7), "posterPath6", 7.8f);
        Movie movie7 = new Movie("Солярис", "tagLine7", "synopsis7", LocalDate.of(1992, 4, 11), "posterPath7", 8.0f);
        Movie movie8 = new Movie("Дом, который построил Джек", "tagLine8", "synopsis8", LocalDate.of(1975, 3, 2), "posterPath8", 8.2f);
        Movie movie9 = new Movie("Большой Лебовски", "tagLine9", "synopsis9", LocalDate.of(2000, 10, 22), "posterPath9", 6.3f);
        Movie movie10 = new Movie("Сумерки", "tagLine10", "synopsis10", LocalDate.of(2018, 5, 17), "posterPath10", 7.1f);
        movies = List.of(movie1, movie2, movie3, movie4, movie5, movie6, movie7, movie8, movie9, movie10);

        movieRepository.saveAll(movies);

        for (Movie movie : movieRepository.findAll()) {
            idList.add(movie.getId());
        }

        switch (value) {
            case 0 -> assertEquals(movieDTOMapper.apply(movies.get(0)), movieService.getMovieById(idList.get(0)));
            case 1 -> assertEquals(movieDTOMapper.apply(movies.get(7)), movieService.getMovieById(idList.get(7)));
            case 2 -> assertNull(movieService.getMovieById(idList.get(idList.size() - 1) + 1));
            case 3 -> assertNull(movieService.getMovieById(-1));
        }

        movieRepository.deleteAll();
    }

    @DisplayName("Test method getMovieById")
    @ParameterizedTest(name = "Test {arguments}")
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7})
    public void getReviews(int value) throws InterruptedException {
        MyUser user = new MyUser("Nikita", "12345678", "lala@mail.ru");

        Movie movie = new Movie("Американский психопат", "tagLine4", "synopsis4", LocalDate.of(2012, 1, 31), "posterPath4", 6.5f);

        Review review1 = new Review(8, "Хороший фильм");
        Review review2 = new Review(7, "Пойдёт");
        Review review3 = new Review(9, "Супер");
        Review review4 = new Review(5, "На один раз");
        Review review5 = new Review(7, "Достойно");
        Review review6 = new Review(6, "Средне");
        Review review7 = new Review(10, "Великолепно");
        Review review8 = new Review(3, "Не зашло");
        Review review9 = new Review(5, "Скучно");
        Review review10 = new Review(8, "Не пожалел");
        Set<Review> reviews = new LinkedHashSet<>(List.of(review1, review2, review3, review4, review5, review6, review7, review8, review9, review10));

        movieRepository.save(movie);
        int movieId = movieRepository.findAll().get(0).getId();
        userRepository.save(user);

        for (Review review : reviews) {
            review.setMovie(movie);
            review.setUser(user);
            review.setCreatedAt(LocalDateTime.now());
            Thread.sleep(10);
        }

        reviewRepository.saveAll(reviews);

        switch (value) {
            case 0 -> assertThat(reviews.stream().map(reviewDTOMapper).toList())
                    .usingElementComparatorIgnoringFields("createdAt")
                    .containsExactlyElementsOf(movieService.getReviews(movieId, 0, 10));
            case 1 -> assertThat(reviews.stream().map(reviewDTOMapper).toList())
                    .usingElementComparatorIgnoringFields("createdAt")
                    .containsExactlyElementsOf(movieService.getReviews(movieId, null, 10));
            case 2 -> assertThat(reviews.stream().map(reviewDTOMapper).toList())
                    .usingElementComparatorIgnoringFields("createdAt")
                    .containsExactlyElementsOf(movieService.getReviews(movieId, 0, null));
            case 3 -> assertThat(reviews.stream().map(reviewDTOMapper).toList())
                    .usingElementComparatorIgnoringFields("createdAt")
                    .containsExactlyElementsOf(movieService.getReviews(movieId, null, null));
            case 4 -> assertThat(Stream.of(review9, review10).map(reviewDTOMapper).toList())
                    .usingElementComparatorIgnoringFields("createdAt")
                    .containsExactlyElementsOf(movieService.getReviews(movieId, 8, 5));
            case 5 -> assertThat(new ArrayList<>())
                    .usingElementComparatorIgnoringFields("createdAt")
                    .containsExactlyElementsOf(movieService.getReviews(movieId, 12, 10));
            case 6 -> assertThat(new ArrayList<>(Stream.of(review2, review3, review4).map(reviewDTOMapper).toList()))
                    .usingElementComparatorIgnoringFields("createdAt")
                    .containsExactlyElementsOf(movieService.getReviews(movieId, 1, 3));
            case 7 -> assertThrows(NullPointerException.class, () -> movieService.getReviews(movieId + 1, 0, 10));
        }

        movieRepository.deleteAll();
        reviewRepository.deleteAll();
        userRepository.deleteAll();
    }

    @DisplayName("Test method createReview")
    @ParameterizedTest(name = "Test {arguments}")
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6})
    public void createReview(int value) {
        SecurityContextHolder.setContext(securityContext);

        MyUser user = new MyUser("Nikita", "12345678", "lala@mail.ru");

        Movie movie = new Movie("Американский психопат", "tagLine4", "synopsis4", LocalDate.of(2012, 1, 31), "posterPath4", 6.5f);

        ReviewInputDTO review1 = new ReviewInputDTO(8, "Хороший фильм");
        ReviewInputDTO review2 = new ReviewInputDTO(7, "Пойдёт");
        ReviewInputDTO review3 = new ReviewInputDTO(9, "Супер");
        Set<ReviewInputDTO> reviews = new LinkedHashSet<>(List.of(review1, review2, review3));

        List<Review> reviewList = reviews.stream()
                .map(reviewInputDTOToReviewMapper)
                .peek(review -> {
                    review.setMovie(movie);
                    review.setUser(user);
                }).toList();

        movieRepository.save(movie);
        int movieId = movieRepository.findAll().get(0).getId();
        userRepository.save(user);

        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(new MyUserDetails(user));

        switch (value) {
            case 0 -> movieService.createReview(movieId, review1);
            case 1 -> movieService.createReview(movieId, review2);
            case 2 -> movieService.createReview(movieId, review3);
            case 3 -> assertThrows(NoSuchElementException.class, () -> movieService.createReview(movieId + 1, review1));
            case 4 -> assertThrows(NullPointerException.class, () -> movieService.createReview(movieId, null));
            case 5 -> assertThrows(InvalidDataAccessApiUsageException.class, () -> movieService.createReview(null, review1));
            case 6 -> assertThrows(NullPointerException.class, () -> movieService.createReview(null, null));
        }

        if (value < 3) {
            ReviewOutputDTO testReview = movieService.getReviews(movieId, 0, 1).get(0);

            assertThat(testReview)
                    .usingRecursiveComparison()
                    .ignoringFields("id", "createdAt")
                    .isEqualTo(reviewDTOMapper.apply(reviewList.get(value)));
        }

        movieRepository.deleteAll();
        reviewRepository.deleteAll();
        userRepository.deleteAll();

    }

}
