package ru.kinozov.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.kinozov.DTO.MyUserInputDTO;
import ru.kinozov.entities.MyUser;
import ru.kinozov.services.AppService;
import ru.kinozov.entities.Application;

import java.util.List;

@Tag(name = "authentication_methods")
@RestController
@RequestMapping("")
public class AppController {
    private final AppService appService;


    public AppController(AppService appService) {
        this.appService = appService;
    }

    @GetMapping("/")
    public String welcome() {
        return "Welcome to unprotected home page";
    }


    @PostMapping("/new-user")
    public String addUser(@RequestBody MyUserInputDTO myUserInputDTO) {
        appService.addUser(myUserInputDTO);
        return "User is saved";
    }
}
