package ru.mts.springsecurity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mts.springsecurity.models.MyUser;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<MyUser, Integer> {
    Optional<MyUser> findMyUserByName(String username);
}
