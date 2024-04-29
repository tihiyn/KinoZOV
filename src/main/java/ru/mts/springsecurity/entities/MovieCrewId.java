package ru.mts.springsecurity.entities;

import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Data
public class MovieCrewId implements Serializable {
    private Integer movie;
    private Integer person;
    private Job job;
}
