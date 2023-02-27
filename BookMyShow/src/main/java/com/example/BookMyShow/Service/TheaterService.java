package com.example.BookMyShow.Service;

import com.example.BookMyShow.RequestDTOs.TheaterRequestDto;
import com.example.BookMyShow.Enums.SeatType;
import com.example.BookMyShow.Models.ShowEntity;
import com.example.BookMyShow.Models.TheaterEntity;
import com.example.BookMyShow.Models.TheaterSeatEntity;
import com.example.BookMyShow.Repository.ShowRepository;
import com.example.BookMyShow.Repository.TheaterRepository;
import com.example.BookMyShow.Repository.TheaterSeatRepository;
import com.example.BookMyShow.ResponseDTOs.TheatreResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheaterService {
    @Autowired
    TheaterSeatRepository theaterSeatRepository;

    @Autowired
    TheaterRepository theaterRepository;

    @Autowired
    ShowRepository showRepository;


    public String createTheater(TheaterRequestDto theaterRequestDto){

        TheaterEntity theater = TheaterEntity.builder().name(theaterRequestDto.getName()).city(theaterRequestDto.getCity()).address(theaterRequestDto.getAddress()).build();

        List<TheaterSeatEntity> theaterSeats = createTheaterSeats();
        theater.setTheaterSeatEntityList(theaterSeats);

//        now we need to set theater in each theater seat also so we will iterate over the list nd set
        for(TheaterSeatEntity x : theaterSeats){
            x.setTheater(theater);
        }
       theaterRepository.save(theater);
        return  "Theater added successfully";
    }

    private List<TheaterSeatEntity> createTheaterSeats(){

        List<TheaterSeatEntity> seats = new ArrayList<>();

//        TheaterSeatEntity theaterSeat1 = new TheaterSeatEntity("1A", SeatType.CLASSIC,200);
//        TheaterSeatEntity theaterSeat2 = new TheaterSeatEntity("1B", SeatType.CLASSIC,200);
//        TheaterSeatEntity theaterSeat3 = new TheaterSeatEntity("1C", SeatType.CLASSIC,200);
//        TheaterSeatEntity theaterSeat4 = new TheaterSeatEntity("1D", SeatType.CLASSIC,200);
//        TheaterSeatEntity theaterSeat5 = new TheaterSeatEntity("1E", SeatType.CLASSIC,200);

        //optimize by using loops
        for(int i=0;i<5;i++){
            char ch = (char)('A' + i);
            String seatNo = "1"+ ch;
            TheaterSeatEntity theaterSeat = new TheaterSeatEntity(seatNo, SeatType.CLASSIC,100);
            seats.add(theaterSeat);

        }
        for(int i=0;i<5;i++){
            char ch = (char)('A' + i);
            String seatNo = "2"+ ch;
            TheaterSeatEntity theaterSeat = new TheaterSeatEntity(seatNo, SeatType.PLATINUM,200);
            seats.add(theaterSeat);

        }
        //to do : combine both loops, dont use nested loops


//        TheaterSeatEntity theaterSeat6 = new TheaterSeatEntity("2A", SeatType.PLATINUM,300);
//        TheaterSeatEntity theaterSeat7 = new TheaterSeatEntity("2B", SeatType.PLATINUM,300);
//        TheaterSeatEntity theaterSeat8 = new TheaterSeatEntity("2C", SeatType.PLATINUM,300);
//        TheaterSeatEntity theaterSeat9 = new TheaterSeatEntity("2D", SeatType.PLATINUM,300);
//        TheaterSeatEntity theaterSeat10 = new TheaterSeatEntity("2E", SeatType.PLATINUM,300);

//        seats.add(theaterSeat1);
//        seats.add(theaterSeat2);
//        seats.add(theaterSeat3);
//        seats.add(theaterSeat4);
//        seats.add(theaterSeat5);
//        seats.add(theaterSeat6);
//        seats.add(theaterSeat7);
//        seats.add(theaterSeat8);
//        seats.add(theaterSeat9);
//        seats.add(theaterSeat10);

        theaterSeatRepository.saveAll(seats);
        return seats;


    }

    public TheatreResponseDto getTheatreById(int theatreId)throws Exception{
        if(theaterRepository.findById(theatreId).isPresent()){
            TheaterEntity theatre=theaterRepository.findById(theatreId).get();
            return TheatreResponseDto.builder().id(theatre.getId()).address(theatre.getAddress())
                    .city(theatre.getCity()).name(theatre.getName()).build();

        }
        else throw new Exception("Theatre Not Found");
    }


    public List<TheatreResponseDto> getAllTheatre() throws Exception{
        List<TheaterEntity> theatreList=theaterRepository.findAll();
        List<TheatreResponseDto> theatreResponseDtoList=new ArrayList<>();
        for(TheaterEntity theatre:theatreList){
            TheatreResponseDto theatreResponseDto=TheatreResponseDto.builder().id(theatre.getId())
                    .name(theatre.getName()).city(theatre.getCity()).address(theatre.getAddress()).build();
            theatreResponseDtoList.add(theatreResponseDto);
        }
        return theatreResponseDtoList;
    }


    public List<TheatreResponseDto> getAllTheatreByMovie(String movieName){
        List<ShowEntity> showList=showRepository.findAll();
        List<TheatreResponseDto> theatreResponseDtoList=new ArrayList<>();
        for(ShowEntity show:showList){

            if(show.getMovie().getMovieName().equals(movieName)){
                TheaterEntity theatre=show.getTheater();
                TheatreResponseDto theatreResponseDto=TheatreResponseDto.builder().id(theatre.getId())
                        .name(theatre.getName()).city(theatre.getCity()).address(theatre.getAddress()).build();

                if(!theatreResponseDtoList.contains(theatreResponseDto)){
                    theatreResponseDtoList.add(theatreResponseDto);
                }
            }

        }
        return theatreResponseDtoList;
    }

}
