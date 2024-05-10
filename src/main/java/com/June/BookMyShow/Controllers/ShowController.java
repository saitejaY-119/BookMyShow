package com.June.BookMyShow.Controllers;

import com.June.BookMyShow.DTOs.RequestDTOs.ShowRequestDTO;
import com.June.BookMyShow.DTOs.RequestDTOs.ShowSeatsRequestDTO;
import com.June.BookMyShow.Exceptions.MovieNotFoundException;
import com.June.BookMyShow.Exceptions.ShowNotFoundException;
import com.June.BookMyShow.Exceptions.TheaterNotFoundException;
import com.June.BookMyShow.Models.Show;
import com.June.BookMyShow.Services.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/show")
public class ShowController {
    @Autowired
    private ShowService showService;

    @PostMapping("/add")
    public ResponseEntity<String> addShow(@RequestBody ShowRequestDTO showRequestDTO) throws MovieNotFoundException, TheaterNotFoundException {
        try{
            return new ResponseEntity<>(showService.addShow(showRequestDTO), HttpStatus.ACCEPTED);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.OK);
        }
    }

    @PostMapping("/associateSeats")
    public ResponseEntity<String> associateSeats(@RequestBody ShowSeatsRequestDTO showSeatsRequestDTO) throws ShowNotFoundException {
        try {
            return new ResponseEntity<>(showService.associateSeats(showSeatsRequestDTO),HttpStatus.ACCEPTED);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getMostRecommendedMovieShow")
    public String getMovieName(ShowRequestDTO showRequestDTO){
        return showService.getMovieName(showRequestDTO);
    }
}
