package ru.kinozov.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ReviewInputDTO {
    private int rating;
    private String body;
}
