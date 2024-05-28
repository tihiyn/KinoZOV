package ru.kinozov.mappers;

import org.springframework.stereotype.Service;
import ru.kinozov.DTO.MyUserOutputDTO;
import ru.kinozov.entities.MyUser;

import java.util.function.Function;

@Service
public class MyUserDTOMapper implements Function<MyUser, MyUserOutputDTO> {
    @Override
    public MyUserOutputDTO apply(MyUser myUser) {
        return new MyUserOutputDTO(
                myUser.getId(),
                myUser.getName()
        );
    }
}
