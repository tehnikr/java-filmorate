package ru.yandex.practicum.filmorate.controller;

import ch.qos.logback.classic.Level;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@RequestMapping(value = "/users")
public class UserController {
    private final List<User> users = new ArrayList<>();

    @GetMapping
    public List<User> findAll() {
        log.info("Текущее количество пользователей: " + users.size());
        return users;
    }

    @PostMapping
    public User saveFilm(@RequestBody @Valid User user) {

        dateValid(user);

        user.setId(users.size() + 1);

        if (user.getName() == null) user.setName(user.getLogin());

        users.add(user);

        log.info("Добавление пользователя, количество пользователей: " + users.size());
        return user;
    }

    @PutMapping  // "Правильный HTTP метод"? В этом месте я не понял что не так...
    public User put(@RequestBody @Valid User user) {

        System.out.println("Новые данные: " + user.toString());

        dateValid(user);

        if (users.size() < user.getId()) {
            throw new ValidationException("Такого пользователя нет");
        }

        users.set(user.getId() - 1, user);
        log.info("Изменение данных пользователя: " + users.size());

        return users.get(user.getId() - 1);
    }

    void dateValid(User user) {
        if (user.getBirthday().isAfter(LocalDate.now())) {
            throw new ValidationException("Дата рождения в будущем!");
        }
    }

}
