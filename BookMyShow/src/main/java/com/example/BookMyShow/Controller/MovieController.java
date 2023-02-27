package com.example.BookMyShow.Controller;
import com.example.BookMyShow.Models.MovieEntity;
import com.example.BookMyShow.RequestDTOs.MovieRequestDto;
import com.example.BookMyShow.ResponseDTOs.MovieResponseDto;
import com.example.BookMyShow.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movie")
public class MovieController {
    @Autowired
    MovieService movieService;

    //add movie
    @PostMapping("/create")
    public ResponseEntity<String> createMovie(@RequestBody MovieRequestDto movieRequestDto){
        return new ResponseEntity<>(movieService.createMovie(movieRequestDto),HttpStatus.ACCEPTED);
    }

    @GetMapping("/get_movie")
    public ResponseEntity<MovieResponseDto> getMovie(@RequestParam("name")String name){
        try{
            MovieResponseDto movie=movieService.getMovie(name);
            return new ResponseEntity<>(movie,HttpStatus.FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }

    }
    @GetMapping("/get_movieById/{id}")
    public ResponseEntity<MovieResponseDto> getMovieById(@PathVariable() int id){
        try {
            return new ResponseEntity<>(movieService.getMovieById(id),HttpStatus.FOUND);
        }catch(Exception e){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
//        return movieService.getMovieById(id);
    }
}
