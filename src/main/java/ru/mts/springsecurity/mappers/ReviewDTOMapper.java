package ru.mts.springsecurity.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mts.springsecurity.DTO.ReviewDTO;
import ru.mts.springsecurity.entities.Review;

import java.util.function.Function;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Service
public class ReviewDTOMapper implements Function<Review, ReviewDTO> {
    private final MyUserDTOMapper myUserDTOMapper;

    @Override
    public ReviewDTO apply(Review review) {
        return new ReviewDTO(
                review.getId(),
                Stream.of(review.getUser()).map(myUserDTOMapper).findFirst().get(),
                review.getMovie().getId(),
                review.getRating(),
                review.getBody(),
                review.getCreatedAt()
        );
    }
}
