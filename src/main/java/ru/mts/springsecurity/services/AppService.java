package ru.mts.springsecurity.services;

import com.github.javafaker.Faker;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.mts.springsecurity.entities.Application;
import ru.mts.springsecurity.entities.MyUser;
import ru.mts.springsecurity.repositories.UserRepository;

import java.util.List;
import java.util.stream.IntStream;

@Service
@AllArgsConstructor
public class AppService {
    private List<Application> applications;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void loadAppInDB() {
        Faker faker = new Faker();

        applications = IntStream.range(0, 100)
                .mapToObj(i -> Application.builder()
                        .id(i)
                        .name(faker.app().name())
                        .author(faker.app().author())
                        .version(faker.app().version())
                        .build())
                .toList();
    }

    public List<Application> allApplications() {
        return applications;
    }

    public Application applicationById(int id) {
        return applications.get(id);
    }

    public void addUser(MyUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}
