package ru.kinozov.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kinozov.entities.MovieCrew;

@Repository
public interface MovieCrewRepository extends JpaRepository<MovieCrew, Integer> {
}
