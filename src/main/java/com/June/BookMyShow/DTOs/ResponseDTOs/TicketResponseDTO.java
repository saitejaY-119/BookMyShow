package com.June.BookMyShow.DTOs.ResponseDTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TicketResponseDTO {
    private String responseMessage;
    private LocalTime showTime;
    private LocalDate showDate;
    private String movieName;
    private String theaterName;
    private String bookedSeats;
    private String location;
    private int totalPrice;
}
