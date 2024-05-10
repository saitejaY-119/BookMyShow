package com.June.BookMyShow.Transformers;

import com.June.BookMyShow.DTOs.RequestDTOs.ShowRequestDTO;
import com.June.BookMyShow.Models.Show;

public class ShowTransformer {
    public static Show convertDtoToEntity(ShowRequestDTO showRequestDTO) {
        Show show= Show.builder()
                   .time(showRequestDTO.getShowStartTime())
                   .date(showRequestDTO.getShowDate())
                   .build();
        return show;
    }
}
