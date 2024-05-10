package com.June.BookMyShow.Controllers;

import com.June.BookMyShow.DTOs.RequestDTOs.UserRequestDTO;
import com.June.BookMyShow.DTOs.ResponseDTOs.UserResponseDTO;
import com.June.BookMyShow.Exceptions.UserNotFoundException;
import com.June.BookMyShow.Models.User;
import com.June.BookMyShow.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/add")
    private ResponseEntity<String> addUser(@RequestBody UserRequestDTO user){
        try {
            return new ResponseEntity<>(userService.addUser(user),HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("getOldestUser")
    public ResponseEntity<UserResponseDTO> getOldestUser(){
        try{
            UserResponseDTO userResponseDTO=userService.getOldestUser();
            userResponseDTO.setStatusCode("200");
            userResponseDTO.setStatusMessage("SUCCESS");
            return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
        } catch (Exception e){
            UserResponseDTO userResponseDTO=new UserResponseDTO();
            userResponseDTO.setStatusCode("500");
            userResponseDTO.setStatusMessage("FAILURE");
            return new ResponseEntity<>(userResponseDTO,HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/findAllUsersGreaterThanAge")
    public ResponseEntity<?> getAllUsers(@RequestParam("age") int age){
        try{
            return new ResponseEntity<>(userService.getAllUsersGreaterThanAge(age),HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
