package ru.yandex.practicum.filmorate.model;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.Duration;
import java.time.LocalDate;

@Data
public class User {

    int id;
    String name;

    @NotBlank
    String login;

    @Email
    String email;

    LocalDate birthday;
}
