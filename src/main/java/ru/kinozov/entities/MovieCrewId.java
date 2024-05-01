package ru.kinozov.entities;

import lombok.*;

import java.io.Serializable;

@Data
public class MovieCrewId implements Serializable {
    private Integer movie;
    private Integer person;
    private Job job;
}
