package ru.mts.springsecurity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mts.springsecurity.models.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
}
