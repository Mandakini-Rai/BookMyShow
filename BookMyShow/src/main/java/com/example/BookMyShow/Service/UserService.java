package com.example.BookMyShow.Service;

import com.example.BookMyShow.Models.TheaterEntity;
import com.example.BookMyShow.RequestDTOs.UserRequestDto;
import com.example.BookMyShow.Models.UserEntity;
import com.example.BookMyShow.Repository.UserRepository;
import com.example.BookMyShow.ResponseDTOs.TheatreResponseDto;
import com.example.BookMyShow.ResponseDTOs.UserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public String createUser(UserRequestDto userRequestDto){

//        converted user DTO to user entity
        UserEntity userEntity= UserEntity.builder().name(userRequestDto.getName()).mobile(userRequestDto.getMobile()).build();
       try {
           userRepository.save(userEntity);
       }catch (Exception e){
           return "user cannot be added";
       }
      return "User added successfully";
    }

    public UserResponseDto getUserByName(String name) {
        UserEntity user = userRepository.findByName(name);
        UserResponseDto userResponseDto = UserResponseDto.builder().id(user.getId()).mobile(user.getMobile()).name(user.getName()).build();
        return userResponseDto;
    }


}
