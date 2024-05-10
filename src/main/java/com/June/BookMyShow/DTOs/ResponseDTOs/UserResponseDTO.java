package com.June.BookMyShow.DTOs.ResponseDTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
    private String name;
    private int age;
    private String mobileNo;
    private String statusCode;
    private String statusMessage;
}
