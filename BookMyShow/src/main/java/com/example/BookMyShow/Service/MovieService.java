package com.example.BookMyShow.Service;

import com.example.BookMyShow.RequestDTOs.MovieRequestDto;
import com.example.BookMyShow.Models.MovieEntity;
import com.example.BookMyShow.Repository.MovieRepository;
import com.example.BookMyShow.ResponseDTOs.MovieResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository;

    public String createMovie(MovieRequestDto movieRequestDto){
        //conversion dto to entity for saving in db
        MovieEntity  movieEntity = MovieEntity.builder().movieName(movieRequestDto.getMovieName()).duration(movieRequestDto.getDuration()).releaseDate(movieRequestDto.getReleaseDate()).build();
        try{
            movieRepository.save(movieEntity);
        }catch(Exception e){
            return "Movie cannot be added";
        }
        return "Movie added successfully";
    }

    public MovieResponseDto getMovie(String name)throws Exception{
        MovieEntity movie=movieRepository.findByMovieName(name);
       // MovieEntity movie=movieRepository.findByMovieName();
        MovieResponseDto movieResponseDto = MovieResponseDto.builder()
                .movieName(movie.getMovieName())
                .releaseDate(movie.getReleaseDate())
                .duration(movie.getDuration())
                .build();
        return movieResponseDto;
    }
    public MovieResponseDto getMovieById(int id){
        MovieEntity movie = movieRepository.findById(id).get();
        MovieResponseDto movieResponseDto = MovieResponseDto.builder()
                .movieName(movie.getMovieName())
                .releaseDate(movie.getReleaseDate())
                .duration(movie.getDuration())
                .build();
        return movieResponseDto;
    }
}
