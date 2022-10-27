package ru.yandex.practicum.filmorate.model;

import lombok.Data;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import javax.validation.constraints.*;

@Data
public class Film {
    private int id;

    @NotBlank(message = "name should not be blank")
    private String name;
    @Size(max=200, message = "description should not be greater 200")
    private String description;
    private LocalDate releaseDate;

    //@Positive( message = "duration should be greater than 0")
    @Min(value = 0, message = "duration should be greater than 0")
    private int duration;



}
