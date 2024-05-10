package com.June.BookMyShow.Transformers;

import com.June.BookMyShow.DTOs.RequestDTOs.UserRequestDTO;
import com.June.BookMyShow.DTOs.ResponseDTOs.UserResponseDTO;
import com.June.BookMyShow.Models.User;

public class UserTransformer {
    public static User convertDtoToEntity(UserRequestDTO userDTO){
        User userObj=User.builder().name(userDTO.getName())
                                   .age(userDTO.getAge())
                                   .mobileNo(userDTO.getMobileNo())
                                   .emailId(userDTO.getEmailId())
                                   .build();
        return userObj;
    }

    public static UserResponseDTO convertEntityToDto(User user) {
        UserResponseDTO userResponseDTO=UserResponseDTO.builder()
                                        .name(user.getName())
                                        .age(user.getAge())
                                        .mobileNo(user.getMobileNo())
                                        .build();
        return userResponseDTO;
    }
}
