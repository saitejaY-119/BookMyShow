package com.June.BookMyShow.Models;

import com.June.BookMyShow.Enums.SeatType;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "showSeats")
@Data
public class ShowSeat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String seatNo;

    @Enumerated(EnumType.STRING)
    private SeatType seatType;

    private int price;
    private boolean isAvailable;
    private boolean isFoodAttached;

    @ManyToOne
    @JoinColumn
    private Show show;
}
