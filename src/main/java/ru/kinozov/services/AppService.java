package ru.kinozov.services;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kinozov.DTO.MyUserInputDTO;
import ru.kinozov.entities.MyUser;
import ru.kinozov.mappers.MyUserInputDTOToMyUserMapper;
import ru.kinozov.repositories.UserRepository;

@Service
@RequiredArgsConstructor
public class AppService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MyUserInputDTOToMyUserMapper myUserMapper;

    public void addUser(MyUserInputDTO myUserInputDTO) {
        MyUser user = myUserMapper.apply(myUserInputDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}
