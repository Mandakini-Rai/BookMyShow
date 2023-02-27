package com.example.BookMyShow.Repository;

import com.example.BookMyShow.Models.MovieEntity;
import com.example.BookMyShow.Models.UserEntity;
import com.example.BookMyShow.ResponseDTOs.UserResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity , Integer> {

    UserEntity findByName(String name);
}
