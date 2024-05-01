package ru.kinozov.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kinozov.entities.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
}
