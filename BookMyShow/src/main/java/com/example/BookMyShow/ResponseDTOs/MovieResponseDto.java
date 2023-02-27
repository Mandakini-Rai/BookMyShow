package com.example.BookMyShow.ResponseDTOs;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class MovieResponseDto {

    private String movieName;

    private int duration;

    private Date releaseDate;

}
