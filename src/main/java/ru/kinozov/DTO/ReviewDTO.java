package ru.kinozov.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class ReviewDTO {
    private int id;
    private MyUserDTO myUserDTO;
    private int movieId;
    private int rating;
    private String body;
    private LocalDateTime createdAt;
}
