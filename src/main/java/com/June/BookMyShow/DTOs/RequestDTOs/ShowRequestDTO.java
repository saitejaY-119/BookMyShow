package com.June.BookMyShow.DTOs.RequestDTOs;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ShowRequestDTO {

    private LocalTime showStartTime;
    private LocalDate showDate;
    private int theaterId;
    private int movieId;
}
