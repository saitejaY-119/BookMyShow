package com.June.BookMyShow.DTOs.RequestDTOs;

import com.June.BookMyShow.Enums.Genre;
import com.June.BookMyShow.Enums.Language;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class MovieRequestDTO {
    private String movieName;
    private double duration;
    private double rating;
    private LocalDate releaseDate;
    private Genre genre;
    private Language language;
}
