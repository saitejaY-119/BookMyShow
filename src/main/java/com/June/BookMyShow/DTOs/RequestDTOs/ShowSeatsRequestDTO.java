package com.June.BookMyShow.DTOs.RequestDTOs;

import lombok.Data;

@Data
public class ShowSeatsRequestDTO {
    private int showId;
    private int priceForClassicSeats;
    private int priceForPremiumSeats;
}
