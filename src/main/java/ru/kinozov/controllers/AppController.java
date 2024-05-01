package ru.kinozov.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/apps")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public List<Application> applicationList() {
        return appService.allApplications();
    }

    @GetMapping("/apps/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Application applicationById(@PathVariable int id) {
        return appService.applicationById(id);
    }

    @PostMapping("/new-user")
    public String addUser(@RequestBody MyUser user) {
        appService.addUser(user);
        return "User is saved";
    }
}
