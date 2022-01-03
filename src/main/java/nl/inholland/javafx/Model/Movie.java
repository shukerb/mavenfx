package nl.inholland.javafx.Model;

import java.time.LocalTime;
import java.util.UUID;

public class Movie {
    private UUID id;
    private String movieName;
    private LocalTime duration;
    private double ticketPrice;

    public Movie(String movieName, LocalTime duration, double ticketPrice) {
        this.movieName = movieName;
        this.duration = duration;
        this.ticketPrice = ticketPrice;
        this.id = UUID.randomUUID();
    }

    public String getMovieName() {
        return movieName;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public LocalTime getDuration() {
        return duration;
    }

    public void setDuration(LocalTime duration) {
        this.duration = duration;
    }
}
