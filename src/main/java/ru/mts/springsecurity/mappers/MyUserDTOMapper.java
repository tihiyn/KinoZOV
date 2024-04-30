package ru.mts.springsecurity.mappers;

import org.springframework.stereotype.Service;
import ru.mts.springsecurity.DTO.MyUserDTO;
import ru.mts.springsecurity.entities.MyUser;

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
