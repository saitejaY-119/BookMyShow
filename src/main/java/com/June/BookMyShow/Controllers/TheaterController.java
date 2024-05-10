package com.June.BookMyShow.Controllers;

import com.June.BookMyShow.DTOs.RequestDTOs.TheaterRequestDTO;
import com.June.BookMyShow.DTOs.RequestDTOs.TheaterSeatsRequestDTO;
import com.June.BookMyShow.Services.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/theater")
public class TheaterController {
    @Autowired
    private TheaterService theaterService;

    @PostMapping("/add")
    public ResponseEntity<String> addTheater(@RequestBody TheaterRequestDTO theaterRequestDTO){
        return new ResponseEntity<>(theaterService.addTheater(theaterRequestDTO), HttpStatus.ACCEPTED);
    }

    @PostMapping("/addTheaterSeats")
    public ResponseEntity<String> addTheaterSeats(@RequestBody TheaterSeatsRequestDTO theaterSeatsRequestDTO){
        return new ResponseEntity<>(theaterService.addTheaterSeats(theaterSeatsRequestDTO),HttpStatus.ACCEPTED);
    }
}
