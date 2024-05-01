package ru.kinozov.mappers;

import org.springframework.stereotype.Service;
import ru.kinozov.DTO.GenreDTO;
import ru.kinozov.entities.Genre;

import java.util.function.Function;

@Service
public class GenreDTOMapper implements Function<Genre, GenreDTO> {

    @Override
    public GenreDTO apply(Genre genre) {
        return new GenreDTO(
                genre.getId(),
                genre.getName()
        );
    }
}
