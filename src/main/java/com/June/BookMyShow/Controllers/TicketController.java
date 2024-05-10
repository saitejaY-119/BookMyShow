package com.June.BookMyShow.Controllers;

import com.June.BookMyShow.DTOs.RequestDTOs.TicketRequestDTO;
import com.June.BookMyShow.DTOs.ResponseDTOs.TicketResponseDTO;
import com.June.BookMyShow.Exceptions.ShowNotFoundException;
import com.June.BookMyShow.Exceptions.TicketIdInvalidException;
import com.June.BookMyShow.Exceptions.UserNotFoundException;
import com.June.BookMyShow.Services.TheaterService;
import com.June.BookMyShow.Services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ticket")
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @PostMapping("/bookTicket")
    private ResponseEntity<TicketResponseDTO> bookTicket(@RequestBody TicketRequestDTO ticketRequestDTO) throws UserNotFoundException, ShowNotFoundException {
        try{
            TicketResponseDTO response=ticketService.bookTicket(ticketRequestDTO);
            response.setResponseMessage("Ticket booked successfully");
            return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
        } catch (Exception e){
            TicketResponseDTO ticketResponseDTO=new TicketResponseDTO();
            ticketResponseDTO.setResponseMessage(e.getMessage());
            return new ResponseEntity<>(ticketResponseDTO, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/cancelTicket/{ticketId}")
    private ResponseEntity<String> cancelTicket(@PathVariable int ticketId) throws TicketIdInvalidException {
        try {
            return new ResponseEntity(ticketService.cancelTicket(ticketId),HttpStatus.ACCEPTED);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
