package ru.yandex.practicum.filmorate.model;

import lombok.Data;

import java.time.Duration;
@Data
public class User {
    int id;
    String name;
    String login;
    String email;
    Duration birthday;
}
