package com.example.BookMyShow.Controller;

import com.example.BookMyShow.Models.MovieEntity;
import com.example.BookMyShow.RequestDTOs.UserRequestDto;
import com.example.BookMyShow.ResponseDTOs.UserResponseDto;
import com.example.BookMyShow.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/add_user")
    public String addUser(@RequestBody UserRequestDto userRequestDto){

        return userService.createUser(userRequestDto);
    }

//     find user by name
    @GetMapping("/get_user")
    public ResponseEntity<UserResponseDto> getUserByName(@RequestParam()String name){

    try{
       return new ResponseEntity<>(userService.getUserByName(name),HttpStatus.FOUND);
      }catch (Exception e){
        return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
    }

  }



}
