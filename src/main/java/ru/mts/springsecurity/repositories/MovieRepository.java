package ru.mts.springsecurity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mts.springsecurity.entities.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
}
