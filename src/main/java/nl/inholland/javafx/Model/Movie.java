package nl.inholland.javafx.Model;

import java.time.LocalDateTime;

public class Movie {
    private String movieName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private double ticketPrice;
    private int availableTickets;
    private Room room;

    public Movie(String movieName, LocalDateTime startTime, LocalDateTime endTime, double ticketPrice,
                 int availableTickets, Room room) {
        this.movieName = movieName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.ticketPrice = ticketPrice;
        this.availableTickets = availableTickets;
        this.room = room;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public int getAvailableTickets() {
        return availableTickets;
    }

    public void setAvailableTickets(int availableTickets) {
        this.availableTickets = availableTickets;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
