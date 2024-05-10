package com.June.BookMyShow.Services;

import com.June.BookMyShow.DTOs.RequestDTOs.ShowRequestDTO;
import com.June.BookMyShow.DTOs.RequestDTOs.ShowSeatsRequestDTO;
import com.June.BookMyShow.Enums.SeatType;
import com.June.BookMyShow.Exceptions.MovieNotFoundException;
import com.June.BookMyShow.Exceptions.ShowNotFoundException;
import com.June.BookMyShow.Exceptions.TheaterNotFoundException;
import com.June.BookMyShow.Models.*;
import com.June.BookMyShow.Repository.MovieRepository;
import com.June.BookMyShow.Repository.ShowRepository;
import com.June.BookMyShow.Repository.TheaterRepository;
import com.June.BookMyShow.Transformers.ShowTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShowService {
    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private TheaterRepository theaterRepository;
    public String addShow(ShowRequestDTO showRequestDTO) throws MovieNotFoundException,TheaterNotFoundException{
        Show show= ShowTransformer.convertDtoToEntity(showRequestDTO);
        Optional<Movie> movieOpt=movieRepository.findById(showRequestDTO.getMovieId());
        if(movieOpt.isEmpty())
            throw new MovieNotFoundException("Movie is not found");

        Optional<Theater> theaterOpt=theaterRepository.findById(showRequestDTO.getTheaterId());
        if(theaterOpt.isEmpty())
            throw new TheaterNotFoundException("Theater is not found");

        Movie movie=movieOpt.get();
        Theater theater=theaterOpt.get();

        show.setMovie(movie);
        show.setTheater(theater);

        show=showRepository.save(show);

        movie.getShowList().add(show);
        movieRepository.save(movie);

        theater.getShowList().add(show);
        theaterRepository.save(theater);

        return "Show is added successfully and showId is "+show.getId();
    }

    public String associateSeats(ShowSeatsRequestDTO showSeatsRequestDTO) throws ShowNotFoundException{
        Optional<Show> showOpt=showRepository.findById(showSeatsRequestDTO.getShowId());
        if(showOpt.isEmpty())
            throw new ShowNotFoundException("Show id is invalid!");
        Show show=showOpt.get();
        Theater theater=show.getTheater();
        List<TheaterSeat> theaterSeatList=theater.getTheaterSeatList();
        List<ShowSeat> showSeatList=show.getShowSeatList();
        for (TheaterSeat theaterSeat:theaterSeatList){
            ShowSeat showSeat=new ShowSeat();
            showSeat.setSeatNo(theaterSeat.getSeatNo());
            showSeat.setSeatType(theaterSeat.getSeatType());
            if(showSeat.getSeatType().equals(SeatType.CLASSIC)){
                showSeat.setPrice(showSeatsRequestDTO.getPriceForClassicSeats());
                showSeat.setFoodAttached(false);
            }
            else{
                showSeat.setPrice(showSeatsRequestDTO.getPriceForPremiumSeats());
                showSeat.setFoodAttached(true);
            }
            showSeat.setShow(show);
            showSeat.setAvailable(true);
            showSeatList.add(showSeat);
        }
        showRepository.save(show);
        return "Show seats are added successfully";
    }

    public String getMovieName(ShowRequestDTO showRequestDTO) {
        int movieId=showRepository.getMostShowedMovie(showRequestDTO.getShowDate());
        Movie movie=movieRepository.findById(movieId).get();
        return movie.getMovieName();
    }
}
