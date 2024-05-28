package ru.kinozov.mappers;

import org.springframework.stereotype.Service;
import ru.kinozov.DTO.ReviewInputDTO;
import ru.kinozov.entities.Review;

import java.util.function.Function;

@Service
public class ReviewInputDTOToReviewMapper implements Function<ReviewInputDTO, Review> {
    @Override
    public Review apply(ReviewInputDTO reviewInputDTO) {
        return new Review(
                reviewInputDTO.getRating(),
                reviewInputDTO.getBody()
        );
    }
}
