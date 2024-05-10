package com.June.BookMyShow.DTOs.RequestDTOs;

import lombok.Data;

@Data
public class TheaterSeatsRequestDTO {
    private int noOfSeatsIn1Row;
    private int noOfClassicSeats;
    private int noOfPremiumSeats;
    private String location;
}
