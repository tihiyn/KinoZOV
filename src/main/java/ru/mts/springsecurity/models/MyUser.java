package ru.mts.springsecurity.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "myuser")
public class MyUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;
}
