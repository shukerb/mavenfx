package nl.inholland.javafx.Controller;

import nl.inholland.javafx.Model.Movie;
import nl.inholland.javafx.Model.Room;
import nl.inholland.javafx.Model.Showing;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MovieController {

    private List<Movie> movies;

    public List<Movie> getMovies() {
        return movies;
    }

    public MovieController(DataBase db) {
        this.movies = db.getMovies();
    }

    public void editMovie(Movie editedMovie) {
        for (Movie movie : movies) {
            if (editedMovie.getId().equals(movie.getId())) {
                deleteMovie(movie);
                addMovie(editedMovie);
            }
        }
    }

    public Movie getMovieById(String id) {
        UUID movieId = UUID.fromString(id);
        for (Movie movie : movies) {
            if (movie.getId().equals(movieId))
                return movie;
        }
        return null;
    }


    public void addMovie(Movie movie) {
        this.movies.add(movie);
    }

    public void deleteMovie(Movie movie) {
        this.movies.remove(movie);

    }


}
