package ru.mts.springsecurity.mappers;

import org.springframework.stereotype.Service;
import ru.mts.springsecurity.DTO.GenreDTO;
import ru.mts.springsecurity.entities.Genre;

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
