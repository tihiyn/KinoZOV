package ru.kinozov.mappers;

import org.springframework.stereotype.Service;
import ru.kinozov.DTO.MyUserDTO;
import ru.kinozov.entities.MyUser;

import java.util.function.Function;

@Service
public class MyUserDTOMapper implements Function<MyUser, MyUserDTO> {
    @Override
    public MyUserDTO apply(MyUser myUser) {
        return new MyUserDTO(
                myUser.getId(),
                myUser.getName()
        );
    }
}
