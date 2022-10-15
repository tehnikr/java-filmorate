package ru.yandex.practicum.filmorate.controller;

import ch.qos.logback.classic.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.ArrayList;
import java.util.List;

@RestController
public class FilmController {
    private static final Logger log = LoggerFactory.getLogger(FilmController.class);
    private final List<Film> films = new ArrayList<>();

    @GetMapping("/films")
    public List<Film> findAll() {
        ch.qos.logback.classic.Logger l = (ch.qos.logback.classic.Logger) log;
        l.setLevel(Level.TRACE);
        log.trace("Текущее количество фильмов: " + films.size());
        return films;
    }
    @PostMapping("/films")
    public List<Film> create(@RequestBody Film film) {

        ch.qos.logback.classic.Logger l = (ch.qos.logback.classic.Logger) log;
        l.setLevel(Level.TRACE);
        log.trace("Добавление фильма log: " + films.size());
        System.out.println("Добавление фильма");
        film.setId(films.size()+1);
        films.add(film);
        return films;
    }
}
