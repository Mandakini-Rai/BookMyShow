package com.example.BookMyShow.Models;

import com.example.BookMyShow.Enums.SeatType;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="theaterSeats")
@AllArgsConstructor
public class TheaterSeatEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

//    @Column(columnDefinition = "seat_no", nullable = false)
    private  String seatNo;

    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;

    private int rate;

//     it has a relation with theater , where theater will be parent(One) to theaterSeat child(many)
    @ManyToOne
    @JoinColumn
    private TheaterEntity theater;

    public TheaterSeatEntity(String seatNo, SeatType seatType,int rate){
        this.seatNo = seatNo;
        this.seatType = seatType;
        this.rate= rate;
    }
}
