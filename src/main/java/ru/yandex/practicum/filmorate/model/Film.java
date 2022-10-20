package ru.yandex.practicum.filmorate.model;

import lombok.Data;

import java.time.Duration;
import java.time.Instant;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class Film {
    int id;

    @NotBlank(message = "name should not be blank")
    String name;
    @Size(max=200, message = "description should not be greater 200")
    String description;
    String releaseDate;

    @Min(value = 0, message = "duration should be greater than 0")
    int duration;

}
