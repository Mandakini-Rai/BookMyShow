package com.example.BookMyShow.Models;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "theater")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class TheaterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String city;

    private String address;

//we will write bidirectional relation with its child theater seat for List of theater seats
    @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL)
     List<TheaterSeatEntity> theaterSeatEntityList;

//    List of shows
    @OneToMany(mappedBy = "theater",cascade = CascadeType.ALL)
    List<ShowEntity> listOfShows;


}
