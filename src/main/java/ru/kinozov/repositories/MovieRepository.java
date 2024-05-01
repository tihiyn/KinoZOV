package ru.kinozov.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kinozov.entities.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
}
