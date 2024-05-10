package com.June.BookMyShow.Services;

import com.June.BookMyShow.DTOs.RequestDTOs.TicketRequestDTO;
import com.June.BookMyShow.DTOs.ResponseDTOs.TicketResponseDTO;
import com.June.BookMyShow.Exceptions.ShowNotFoundException;
import com.June.BookMyShow.Exceptions.TicketIdInvalidException;
import com.June.BookMyShow.Exceptions.UserNotFoundException;
import com.June.BookMyShow.Models.*;
import com.June.BookMyShow.Repository.ShowRepository;
import com.June.BookMyShow.Repository.TicketRepository;
import com.June.BookMyShow.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private JavaMailSender emailSender;
    public TicketResponseDTO bookTicket(TicketRequestDTO ticketRequestDTO) throws Exception {
        int userId=ticketRequestDTO.getUserId();
        Optional<User> userOpt=userRepository.findById(userId);
        if(userOpt.isEmpty())
            throw new UserNotFoundException("User id is invalid");

        int showId=ticketRequestDTO.getShowId();
        Optional<Show> showOpt=showRepository.findById(showId);
        if(showOpt.isEmpty())
            throw new ShowNotFoundException("Show is not available");

        Show show=showOpt.get();
        boolean isValid=validateShowAvailability(show,ticketRequestDTO.getRequestedSeats(),showId);
        if (isValid==false)
            throw new Exception("Requested seats are not available, they are already booked!");

        Ticket ticket=new Ticket();
        int totalPrice=calculateTotalPrice(show,ticketRequestDTO.getRequestedSeats());
        ticket.setTotalTicketsPrice(totalPrice);
        if (ticketRequestDTO.getIsFood()==1){
            totalPrice+=100;
        }
        String bookedSeats=convertListToString(ticketRequestDTO.getRequestedSeats());
        ticket.setBookedSeats(bookedSeats);

        User user=userOpt.get();
        ticket.setUser(user);
        ticket.setShow(show);
        ticket=ticketRepository.save(ticket);

        user.getTicketList().add(ticket);
        userRepository.save(user);

        show.getTicketList().add(ticket);
        showRepository.save(show);

        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();

        String body="Hii! "+user.getName()+"\n"+
                "You have successfully booked a ticket. Please find the following details of your ticket \n"+
                "Your ticket id is"+ticket.getId()+"\n"+
                "Booked seats are:- "+bookedSeats+"\n"+
                "Movie name:- "+show.getMovie().getMovieName()+"\n"+
                "Show date is "+show.getDate()+"\n"+
                "Show time is "+show.getTime()+"\n"+
                "Enjoy the show!!"+"\n"+
                "Thank You";
        simpleMailMessage.setSubject("Ticket Confirmation Mail");
        simpleMailMessage.setFrom("dummy8597362@gmail.com");
        simpleMailMessage.setText(body);
        simpleMailMessage.setTo(user.getEmailId());
        emailSender.send(simpleMailMessage);

        return createTicketResponseDTO(show,ticket);
    }

    private boolean validateShowAvailability(Show show, List<String> requestedSeats, int showId) {
        List<ShowSeat> showSeatList=show.getShowSeatList();
        for (ShowSeat showSeat:showSeatList){
            String seatNo=showSeat.getSeatNo();
            if(requestedSeats.contains(seatNo)){
                if (showSeat.isAvailable()==false)
                    return false;
            }
        }
        return true;
    }

    private int calculateTotalPrice(Show show, List<String> requestedSeats) {
        int totalPrice=0;
        List<ShowSeat> showSeatList=show.getShowSeatList();
        for (ShowSeat showSeat:showSeatList){
            if(requestedSeats.contains(showSeat.getSeatNo())){
                totalPrice+=showSeat.getPrice();
                showSeat.setAvailable(false);
            }
        }
        return totalPrice;
    }

    private String convertListToString(List<String> requestedSeats) {
        String result="";
        for (String seatNo:requestedSeats)
            result+=seatNo+",";
        return result;
    }

    private TicketResponseDTO createTicketResponseDTO(Show show, Ticket ticket) {
        TicketResponseDTO ticketResponseDTO=TicketResponseDTO.builder()
                .bookedSeats(ticket.getBookedSeats())
                .theaterName(show.getTheater().getName())
                .location(show.getTheater().getLocation())
                .movieName(show.getMovie().getMovieName())
                .showDate(show.getDate())
                .showTime(show.getTime())
                .totalPrice(ticket.getTotalTicketsPrice())
                .build();
        return ticketResponseDTO;
    }

    public String cancelTicket(int ticketId) throws TicketIdInvalidException {
        Optional<Ticket> ticketOpt = ticketRepository.findById(ticketId);
        if (ticketOpt.isEmpty())
            throw new TicketIdInvalidException("Invalid ticket id, Please enter correct ticket id");
        Ticket ticket = ticketOpt.get();
        Show show = ticket.getShow();
        User user = ticket.getUser();
        user.getTicketList().remove(ticket);
        String bookedSeats=ticket.getBookedSeats();
        cancelShowSeats(show,bookedSeats);
        List<Ticket> ticketList=ticketRepository.findAll();
        for(Ticket ticket1:ticketList){
            if (ticket1.getId()==ticketId){
                ticketRepository.delete(ticket);
                break;
            }
        }
        user.getTicketList().remove(ticket);
        show.getTicketList().remove(ticket);
        ticketRepository.delete(ticket);
        return "Ticket cancelled successfully!!";
    }

    private void cancelShowSeats(Show show, String bookedSeats) {
        List<ShowSeat> showSeatList=show.getShowSeatList();
        List<String> seats=Arrays.asList(bookedSeats.split(","));
        for(ShowSeat showSeat:showSeatList){
            if(seats.contains(showSeat.getSeatNo())) {
                showSeat.setAvailable(true);
            }
        }
    }
}
