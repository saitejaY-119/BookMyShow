package com.June.BookMyShow.Services;

import com.June.BookMyShow.DTOs.RequestDTOs.TheaterRequestDTO;
import com.June.BookMyShow.DTOs.RequestDTOs.TheaterSeatsRequestDTO;
import com.June.BookMyShow.Enums.SeatType;
import com.June.BookMyShow.Models.Theater;
import com.June.BookMyShow.Models.TheaterSeat;
import com.June.BookMyShow.Repository.TheaterRepository;
import com.June.BookMyShow.Transformers.TheaterTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TheaterService {
    @Autowired
    private TheaterRepository theaterRepository;
    public String addTheater(TheaterRequestDTO theaterRequestDTO) {
        Theater theater= TheaterTransformer.convertDtoToEntity(theaterRequestDTO);
        theaterRepository.save(theater);
        return "Theater is added successfully";
    }

    public String addTheaterSeats(TheaterSeatsRequestDTO theaterSeatsRequestDTO) {
        int columns = theaterSeatsRequestDTO.getNoOfSeatsIn1Row();
        int noOfClassicSeats = theaterSeatsRequestDTO.getNoOfClassicSeats();
        int noOfPremiumSeats = theaterSeatsRequestDTO.getNoOfPremiumSeats();
        String location = theaterSeatsRequestDTO.getLocation();
        Theater theater = theaterRepository.findByLocation(location);
        List<TheaterSeat> theaterSeatList = theater.getTheaterSeatList();
        int counter = 1;
        char ch = 'A';
        for(int count = 1;count<=noOfClassicSeats;count++){
            String seatNo = counter+"";
            seatNo = seatNo + ch;
            ch++;
            if((ch-'A')==columns){
                ch = 'A';
                counter++;
            }
            TheaterSeat theaterSeat = new TheaterSeat();
            theaterSeat.setSeatNo(seatNo);
            theaterSeat.setTheater(theater);
            theaterSeat.setSeatType(SeatType.CLASSIC);
            theaterSeatList.add(theaterSeat);
        }
        for(int count=1;count<=noOfPremiumSeats;count++){
            String seatNo = counter+"";
            seatNo = seatNo + ch;
            ch++;
            if((ch-'A')==columns){
                ch = 'A';
                counter++;
            }
            TheaterSeat theaterSeat = new TheaterSeat();
            theaterSeat.setTheater(theater);
            theaterSeat.setSeatType(SeatType.PREMIUM);
            theaterSeat.setSeatNo(seatNo);
            theaterSeatList.add(theaterSeat);
        }
        theaterRepository.save(theater);

        return "Theater Seats have been successfully added";
    }
}
