package ru.yandex.practicum.filmorate.model;

import lombok.Data;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import javax.validation.constraints.*;

@Data
public class Film {
    int id;

    @NotBlank(message = "name should not be blank")
    String name;
    @Size(max=200, message = "description should not be greater 200")
    String description;
    LocalDate releaseDate;

    //@Positive( message = "duration should be greater than 0")
    @Min(value = 0, message = "duration should be greater than 0")
    int duration;



}
