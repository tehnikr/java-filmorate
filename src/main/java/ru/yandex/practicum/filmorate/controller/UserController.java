package ru.yandex.practicum.filmorate.controller;

import ch.qos.logback.classic.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(FilmController.class);
    private final List<User> users = new ArrayList<>();

    @GetMapping
    public List<User> findAll() {
        ch.qos.logback.classic.Logger l = (ch.qos.logback.classic.Logger) log;
        l.setLevel(Level.TRACE);

        log.trace("Текущее количество пользователей: " + users.size());
        return users;
    }

    @PostMapping
    public User saveFilm(@RequestBody @Valid User user) {

        ch.qos.logback.classic.Logger l = (ch.qos.logback.classic.Logger) log;
        l.setLevel(Level.TRACE);

        if (user.getBirthday().isAfter(LocalDate.now()))
            throw new ValidationException("Дата рождения в будущем!");

        user.setId(users.size() + 1);

        if (user.getName() == null) user.setName(user.getLogin());


        users.add(user);

        log.trace("Добавление пользователя, количество пользователей: " + users.size());
        return user;
    }

    @PutMapping
    public User put(@RequestBody @Valid User user) {
        ch.qos.logback.classic.Logger l = (ch.qos.logback.classic.Logger) log;
        l.setLevel(Level.TRACE);

        System.out.println("Новые данные: " + user.toString());

        if (user.getBirthday().isAfter(LocalDate.now()))
            throw new ValidationException("Дата рождения в будущем!");

        if (users.size() < user.getId())
            throw new ValidationException("Такого пользователя нет");

        users.set(user.getId()-1, user);
        log.trace("Изменение данных пользователя: " + users.size());

        return users.get(user.getId()-1);
    }

}
