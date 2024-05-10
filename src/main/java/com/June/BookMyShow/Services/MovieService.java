package com.June.BookMyShow.Services;

import com.June.BookMyShow.DTOs.RequestDTOs.MovieRequestDTO;
import com.June.BookMyShow.Models.Movie;
import com.June.BookMyShow.Repository.MovieRepository;
import com.June.BookMyShow.Transformers.MovieTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public String addMovie(MovieRequestDTO movieRequestDTO) {
        Movie movie= MovieTransformer.convertEntityToDto(movieRequestDTO);
        movieRepository.save(movie);
        return "Movie is added successfully";
    }
}
