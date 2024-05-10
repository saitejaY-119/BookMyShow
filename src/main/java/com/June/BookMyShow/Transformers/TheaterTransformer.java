package com.June.BookMyShow.Transformers;

import com.June.BookMyShow.DTOs.RequestDTOs.TheaterRequestDTO;
import com.June.BookMyShow.Models.Theater;

public class TheaterTransformer {
    public static Theater convertDtoToEntity(TheaterRequestDTO theaterRequestDTO) {
        Theater theater= Theater.builder()
                         .name(theaterRequestDTO.getName())
                         .location(theaterRequestDTO.getLocation())
                         .build();
        return theater;
    }
}
