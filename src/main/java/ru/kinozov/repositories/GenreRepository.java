package ru.kinozov.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kinozov.entities.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {

}
