package ru.kinozov.mappers;

import org.springframework.stereotype.Service;
import ru.kinozov.DTO.MyUserInputDTO;
import ru.kinozov.entities.MyUser;

import java.util.function.Function;

@Service
public class MyUserInputDTOToMyUserMapper implements Function<MyUserInputDTO, MyUser> {

    @Override
    public MyUser apply(MyUserInputDTO myUserInputDTO) {
        return new MyUser(
                myUserInputDTO.getName(),
                myUserInputDTO.getPassword(),
                myUserInputDTO.getEmail()
        );
    }
}
