package ru.kinozov.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kinozov.entities.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
}
