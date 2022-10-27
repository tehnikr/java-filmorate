package ru.yandex.practicum.filmorate.model;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.Duration;
import java.time.LocalDate;

@Data
public class User {

    private int id;
    private String name;

    @NotBlank
    private String login;

    @Email
    private String email;

    private LocalDate birthday;
}
