package ru.kinozov.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import ru.kinozov.DTO.MyUserInputDTO;
import ru.kinozov.entities.MyUser;
import ru.kinozov.services.AppService;
import ru.kinozov.entities.Application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Tag(name = "authentication_methods")
@RestController
@RequestMapping()
public class AppController {
    private final AppService appService;


    public AppController(AppService appService) {
        this.appService = appService;
    }

    @GetMapping("/")
    public String welcome() {
        return "Welcome to unprotected home page";
    }

    @PostMapping("/auth/register")
    public String addUser(@Valid @RequestBody MyUserInputDTO myUserInputDTO) {
        appService.addUser(myUserInputDTO);
        return "User is saved";
    }
}
