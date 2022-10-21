package ru.yandex.practicum.filmorate.controller;

import ch.qos.logback.classic.Level;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;

import javax.validation.Valid;
import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/films")
public class FilmController {
    private static final Logger log = LoggerFactory.getLogger(FilmController.class);
    private final List<Film> films = new ArrayList<>();

    @GetMapping
    public List<Film> findAll() {
        ch.qos.logback.classic.Logger l = (ch.qos.logback.classic.Logger) log;
        l.setLevel(Level.TRACE);
        log.trace("Текущее количество фильмов: " + films.size());
        return films;
    }

    @PostMapping
    public Film saveFilm(@RequestBody @Valid Film film) {

        ch.qos.logback.classic.Logger l = (ch.qos.logback.classic.Logger) log;
        l.setLevel(Level.TRACE);

        if (film.getReleaseDate().isBefore(LocalDate.of(1895,12,28)))
            throw new ValidationException("Дата раньше 12 декабря 1895 г.");


        film.setId(films.size() + 1);
        films.add( film);

        log.trace("Добавление фильма: " + films.size());
        return film;
    }

    @PutMapping
    public Film put(@RequestBody @Valid Film film) {
        ch.qos.logback.classic.Logger l = (ch.qos.logback.classic.Logger) log;
        l.setLevel(Level.TRACE);

        System.out.println("Новые данные: " + film.toString());

        if (film.getReleaseDate().isBefore(LocalDate.of(1895,12,28)))
            throw new ValidationException("Дата раньше 12 декабря 1895 г.");

        if (films.size() < film.getId())
            throw new ValidationException("Такого фильма нет");

        films.set(film.getId()-1, film);
        log.trace("Изменение фильма: " + films.size());

        return films.get(film.getId()-1);

    }
}
