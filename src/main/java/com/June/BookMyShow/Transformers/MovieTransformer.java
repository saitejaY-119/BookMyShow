package com.June.BookMyShow.Transformers;

import com.June.BookMyShow.DTOs.RequestDTOs.MovieRequestDTO;
import com.June.BookMyShow.Models.Movie;

public class MovieTransformer {
    public static Movie convertEntityToDto(MovieRequestDTO movieRequestDTO) {
        Movie movie= Movie.builder()
                     .movieName(movieRequestDTO.getMovieName())
                     .duration(movieRequestDTO.getDuration())
                     .releaseDate(movieRequestDTO.getReleaseDate())
                     .genre(movieRequestDTO.getGenre())
                     .language(movieRequestDTO.getLanguage())
                     .rating(movieRequestDTO.getRating())
                     .build();
        return movie;
    }
}
