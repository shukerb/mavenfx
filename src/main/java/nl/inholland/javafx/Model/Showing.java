package nl.inholland.javafx.Model;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Showing {
    private Movie movie;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Room room;

    public Showing(Movie movie, LocalDateTime startTime, Room room) {
        this.movie = movie;
        this.startTime = startTime;
        this.room = room;
        this.endTime = setEndTime(startTime);
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

    private LocalDateTime setEndTime(LocalDateTime startTime) {
        LocalDateTime endOfMovie = startTime;
        int hours = this.movie.getDuration().getHour();
        int minutes = this.movie.getDuration().getMinute();
        endOfMovie.plusHours(hours);
        endOfMovie.plusMinutes(minutes);
        return endOfMovie;
    }
}
