package com.June.BookMyShow.DTOs.RequestDTOs;

import lombok.Data;

import java.util.List;

@Data
public class TicketRequestDTO {
    private int showId;
    private int userId;
    private List<String> requestedSeats;
    private int isFood;
}
