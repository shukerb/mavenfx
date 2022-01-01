package nl.inholland.javafx.Controller;

import nl.inholland.javafx.Model.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DataBase {

    private List<Movie> movies;
    private List<Showing> showings;
    private List<User> users;

    public List<Movie> getMovies() {
        return movies;
    }

    public List<Showing> getShowings() {
        return showings;
    }

    public List<User> getUsers() {
        return users;
    }

    public DataBase() {
        initiateDB();
    }

    private void initiateDB() {
        this.movies = new ArrayList<>();
        DateTimeFormatter tFormatter = DateTimeFormatter.ofPattern("H:m");
        LocalTime duration = LocalTime.parse("1:30", tFormatter);

        Movie movie1 = new Movie("No Time To Lie", duration, 12.00, 200);
        Movie movie2 = new Movie("The Addams Family 19", duration, 9.00, 200);
        Movie movie3 = new Movie("Pulp Fiction", duration, 9.00, 200);
        Movie movie4 = new Movie("Fight Club", duration, 12.00, 200);
        movies.add(movie1);
        movies.add(movie2);
        movies.add(movie3);
        movies.add(movie4);

        showings = new ArrayList<>();
        DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime startTimeOne = LocalDateTime.parse("09-10-2021 20:00", dtFormatter);
        LocalDateTime startTimeTwo = LocalDateTime.parse("09-10-2021 22:30", dtFormatter);
        LocalDateTime startTimeThree = LocalDateTime.parse("09-10-2021 18:00", dtFormatter);

        Showing showing1 = new Showing(movie1,startTimeOne, Room.ROOM_ONE);
        Showing showing2 = new Showing(movie3,startTimeOne,Room.ROOM_TWO);
        Showing showing3 = new Showing(movie2,startTimeTwo,Room.ROOM_TWO);
        Showing showing4 = new Showing(movie4,startTimeTwo,Room.ROOM_ONE);
        Showing showing5 = new Showing(movie2,startTimeThree,Room.ROOM_ONE);
        Showing showing6 = new Showing(movie4,startTimeThree,Room.ROOM_TWO);
        showings.add(showing1);
        showings.add(showing2);
        showings.add(showing3);
        showings.add(showing4);
        showings.add(showing5);
        showings.add(showing6);

        this.users = new ArrayList<>();

        User user1 = new User("Shuker", "Barbour", "shuker.b", "password");
        User user2 = new User("user", "user", "user", "user");
        User user3 = new User("admin", "admin", "admin", "admin");
        user3.setUserRole(Role.ADMIN);
        users.add(user1);
        users.add(user2);
        users.add(user3);


    }
}
