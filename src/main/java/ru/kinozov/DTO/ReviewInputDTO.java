package ru.kinozov.DTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ReviewInputDTO {
    @Min(value = 1, message = "Rating should be greater than 0")
    @Max(value = 10, message = "Rating should be less than or equal 10")
    private int rating;
    @NotBlank(message = "Body is mandatory")
    private String body;
}
