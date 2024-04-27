package ru.mts.springsecurity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mts.springsecurity.models.MovieCrew;

@Repository
public interface MovieCrewRepository extends JpaRepository<MovieCrew, Integer> {
}
