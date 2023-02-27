package com.example.BookMyShow.Controller;

import com.example.BookMyShow.RequestDTOs.TheaterRequestDto;
import com.example.BookMyShow.ResponseDTOs.TheatreResponseDto;
import com.example.BookMyShow.Service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/theater")
public class TheaterController {
    @Autowired
    TheaterService theaterService;


    @PostMapping("/add_theater")
    public String addTheater(@RequestBody TheaterRequestDto theaterRequestDto){
        return theaterService.createTheater(theaterRequestDto);
    }

    //get theater by theater id

    @GetMapping("get_by_id")
    public ResponseEntity<TheatreResponseDto> getTheatreById(@RequestParam("id") int theatreId){
        try{
           return new ResponseEntity<>(theaterService.getTheatreById(theatreId),HttpStatus.FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }

    //get all theaters
    @GetMapping("get_all_theatre")
    public ResponseEntity<List<TheatreResponseDto>> getAllTheatre(){
        try {
            return new ResponseEntity<>(theaterService.getAllTheatre(),HttpStatus.FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);

        }
    }

    @GetMapping("/get_all_theatres_by_movie_name")
    public ResponseEntity<List<TheatreResponseDto>> getAllTheatreByMovie(@RequestParam String movieName){
        try{

            return new ResponseEntity<>(theaterService.getAllTheatreByMovie(movieName),HttpStatus.FOUND);

        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }
}
