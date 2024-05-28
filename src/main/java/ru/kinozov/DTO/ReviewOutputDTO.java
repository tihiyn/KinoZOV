package ru.kinozov.DTO;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ReviewOutputDTO {
    private int id;
    private MyUserOutputDTO myUserOutputDTO;
    private int movieId;
    private int rating;
    private String body;
    private LocalDateTime createdAt;
}
