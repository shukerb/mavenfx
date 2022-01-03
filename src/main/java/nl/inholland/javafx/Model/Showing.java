package nl.inholland.javafx.Model;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

public class Showing {
    private UUID id;
    private Movie movie;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int availableTickets;
    private Room room;

    public Showing(Movie movie, int tickets, LocalDateTime startTime, Room room) {
        this.movie = movie;
        this.availableTickets = tickets;
        this.startTime = startTime;
        this.room = room;
        this.endTime = calculateEndTime(startTime, this.movie.getDuration());
        this.id = UUID.randomUUID();
    }

    public int getAvailableTickets() {
        return availableTickets;
    }

    public void setAvailableTickets(int availableTickets) {
        this.availableTickets = availableTickets;
    }

    public UUID getId() {
        return id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public LocalDateTime calculateEndTime(LocalDateTime startTime, LocalTime duration) {

        int hours = duration.getHour();
        int minutes = duration.getMinute();
        LocalDateTime endOfMovie = startTime.plusHours(hours).plusMinutes(minutes);

        return endOfMovie;
    }

    public String getRoomToString() {
        if (this.room == Room.ROOM_ONE){
            return "Room One";
        }
        return "Room Two";
    }
}
