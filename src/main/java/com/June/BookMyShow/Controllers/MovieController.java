package com.June.BookMyShow.Controllers;

import com.June.BookMyShow.DTOs.RequestDTOs.MovieRequestDTO;
import com.June.BookMyShow.Services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @PostMapping("/add")
    public String addMovie(@RequestBody MovieRequestDTO movieRequestDTO){
        return movieService.addMovie(movieRequestDTO);
    }
}
