package nl.inholland.javafx.View;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import nl.inholland.javafx.Controller.DataBase;
import nl.inholland.javafx.Controller.MovieController;
import nl.inholland.javafx.Controller.ShowingController;
import nl.inholland.javafx.Controller.UserController;
import nl.inholland.javafx.Model.Movie;
import nl.inholland.javafx.Model.Room;
import nl.inholland.javafx.Model.Showing;
import nl.inholland.javafx.Model.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Utils {
    private UserController userController;
    private MovieController movieController;
    private ShowingController showingController;
    private boolean removeOldForm = false;
    private User user;
    private Stage window;
    private DataBase db;


    public Utils(DataBase db, User user, Stage window) {
        this.db = db;
        this.user = user;
        this.window = window;
        this.showingController = new ShowingController(db);
        this.movieController = new MovieController(db);
        this.userController = new UserController(db);
    }

    private TableView<Showing> createTableViewForShowing(Room room, VBox container, boolean isAdmin) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        TableView<Showing> tvShowings = new TableView<Showing>();

        TableColumn<Showing, String> startTime = new TableColumn<>("Start Time");
        startTime.setCellValueFactory(
                row -> new SimpleStringProperty(row.getValue().getStartTime().format(formatter))
        );
        startTime.setMinWidth(120);

        TableColumn<Showing, String> endTime = new TableColumn<>("End Time");
        endTime.setCellValueFactory(
                row -> new SimpleStringProperty(row.getValue().getEndTime().format(formatter))
        );
        endTime.setMinWidth(120);

        TableColumn<Showing, String> title = new TableColumn<>("Title");
        title.setCellValueFactory(
                row -> new SimpleStringProperty(row.getValue().getMovie().getMovieName())
        );
        title.setMinWidth(120);

        TableColumn<Showing, Integer> seats = new TableColumn<>("Seats");
        seats.setCellValueFactory(new PropertyValueFactory<>("availableTickets"));
        seats.setMinWidth(100);

        TableColumn<Showing, Number> price = new TableColumn<>("Price");
        price.setCellValueFactory(
                row -> new SimpleDoubleProperty(row.getValue().getMovie().getTicketPrice())
        );
        price.setMinWidth(100);

        tvShowings.getColumns().addAll(startTime, endTime, title, seats, price);

        tvShowings.setItems(FXCollections.observableArrayList(showingController.showingsByRoom(room)));

        tvShowings.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) ->
        {
            if (newValue != null) {
                if (!isAdmin) {
                    buildPurchaseTicketsForm(container, newValue);
                    removeOldForm = true;
                }
            }
        }));
        return tvShowings;
    }

    private TableView<Movie> createTableViewForMovies() {
        DateTimeFormatter tFormatter = DateTimeFormatter.ofPattern("H:m");
        TableView<Movie> tvMovies = new TableView<Movie>();

        TableColumn<Movie, String> duration = new TableColumn<>("Duration");
        duration.setCellValueFactory(
                row -> new SimpleStringProperty(row.getValue().getDuration().format(tFormatter))
        );
        duration.setMinWidth(100);

        TableColumn<Movie, String> title = new TableColumn<>("Title");
        title.setCellValueFactory(new PropertyValueFactory<>("movieName"));
        title.setMinWidth(120);

        TableColumn<Movie, Integer> price = new TableColumn<>("Price");
        price.setCellValueFactory(new PropertyValueFactory<>("ticketPrice"));
        price.setMinWidth(100);

        tvMovies.getColumns().addAll(title, duration, price);

        tvMovies.setItems(FXCollections.observableArrayList(movieController.getMovies()));

        return tvMovies;
    }

    public void buildMoviesPane(VBox container) {

        GridPane pane = new GridPane();
        Text text = new Text("Available Movies");
        text.setFont(Font.font("verdana", FontWeight.BOLD, 30));
        pane.add(text, 0, 0);

        TableView<Movie> movieTableView = createTableViewForMovies();
        pane.add(movieTableView, 0, 1);

        pane.setVgap(10);
        pane.setHgap(10);
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(20, 10, 10, 10));

        container.getChildren().add(pane);
    }

    public void buildShowingsPane(VBox container, boolean isAdmin) {
        if (this.removeOldForm) {
            container.getChildren().remove(container.getChildren().get(2));
            this.removeOldForm = false;
        }
        GridPane pane = new GridPane();
        Text text = new Text("Available Showings");
        text.setFont(Font.font("verdana", FontWeight.BOLD, 30));
        TableView<Showing> room1Showings = createTableViewForShowing(Room.ROOM_ONE, container, isAdmin);
        TableView<Showing> room2Showings = createTableViewForShowing(Room.ROOM_TWO, container, isAdmin);

        pane.add(text, 0, 0);
        pane.add(new Label("Room One"), 0, 1);
        pane.add(room1Showings, 0, 2);
        pane.add(new Label("Room Two"), 1, 1);
        pane.add(room2Showings, 1, 2);

        pane.setVgap(10);
        pane.setHgap(10);
        pane.setAlignment(Pos.CENTER);

        container.getChildren().add(pane);
    }

    public GridPane buildAddShowingForm() {
        GridPane pane = new GridPane();
        Label moviePrice = new Label("");
        Label lEndTime = new Label("");
        Text text = new Text("Add Showing");
        text.setFont(Font.font("verdana", FontWeight.BOLD, 15));

        ComboBox cbMovie = new ComboBox();

        List<Movie> movieList = movieController.getMovies();
        for (Movie movie : movieList) {
            cbMovie.getItems().add(movie.getMovieName());
        }


        ComboBox cbRoom = new ComboBox();
        for (Room room : Room.values()) {
            cbRoom.getItems().add(room);
        }
        pane.add(text, 0, 0);
        pane.add(new Label("Movie: "), 0, 1);
        pane.add(cbMovie, 1, 1);
        pane.add(new Label("Room: "), 0, 2);
        pane.add(cbRoom, 1, 2);
        pane.add(new Label("Available Tickets: "), 0, 3);
        TextField ticketsAvailable = new TextField("200");
        forceNumeric(ticketsAvailable);
        pane.add(ticketsAvailable, 1, 3);
        pane.add(new Label("Start: "), 3, 1);
        DatePicker startDate = new DatePicker(LocalDate.now());
        pane.add(startDate, 4, 1);
        TextField startTime = new TextField("16:00");
        pane.add(startTime, 5, 1);
        pane.add(new Label("End Time: "), 3, 2);
        pane.add(lEndTime, 4, 2);
        pane.add(new Label("Price: "), 3, 3);
        pane.add(moviePrice, 4, 3);

        startDate.setOnAction(actionEvent -> {
            try {
                getEndingTimeForEndTimeLabel(cbMovie, startDate, startTime, lEndTime);
            } catch (Exception e) {
                new PopUpWindow("Make sure to select a Movie first");
            }

        });

        startTime.setOnAction(actionEvent -> {
            try {
                getEndingTimeForEndTimeLabel(cbMovie, startDate, startTime, lEndTime);
            } catch (Exception e) {
                new PopUpWindow("Please enter only Time in this Format HH:mm and select Movie first");
            }
        });

        cbMovie.setOnAction(actionEvent -> {
            try {
                getEndingTimeAndPriceForLabels(cbMovie, startDate, startTime, lEndTime, moviePrice);
            } catch (Exception e) {
                new PopUpWindow("Make sure to select a Movie");
            }
        });

        Button btnAddShowing = new Button("Add Showing");
        pane.add(btnAddShowing, 0, 4);
        btnAddShowing.setOnAction(actionEvent -> {
            try {
                Movie selectedMovie = (movieController.getMovieByName((cbMovie.getSelectionModel().getSelectedItem()).toString()));
                int numberOfTickets = Integer.parseInt(ticketsAvailable.getText());
                LocalDateTime starts = startOfShowing(startDate.getValue().toString(), startTime.getText());
                Room selectedRoom = Room.valueOf(cbRoom.getSelectionModel().getSelectedItem().toString());
                Showing showing = new Showing(selectedMovie, numberOfTickets, starts, selectedRoom);
                if (showingController.checkIfShowingsOverlap(showing)){
                    new PopUpWindow("Time selected overlaps with other Showing");
                }else {
                    showingController.addShowing(showing);
                    new PopUpWindow(this.window, this.db, this.user, "Successfully added a showing");
                }

            } catch (Exception e) {
                new PopUpWindow("Please make sure to fill all the fields");
            }


        });

        pane.setVgap(10);
        pane.setHgap(10);
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(20, 10, 10, 10));

        return pane;
    }

    private void buildPurchaseTicketsForm(VBox container, Showing selectedShowing) {

        if (this.removeOldForm) {
            container.getChildren().remove(container.getChildren().get(2));
        }

        GridPane pane = new GridPane();

        Text text = new Text("Purchase Tickets");
        text.setFont(Font.font("verdana", FontWeight.BOLD, 15));
        pane.add(text, 0, 0);

        pane.add(new Label(selectedShowing.getRoomToString()), 1, 1);
        pane.add(new Label(selectedShowing.getStartTime().toString()), 1, 2);
        pane.add(new Label(selectedShowing.getEndTime().toString()), 1, 3);
        pane.add(new Label(selectedShowing.getMovie().getMovieName()), 4, 1);

        pane.add(new Label("Room: "), 0, 1);
        pane.add(new Label("Starting Time: "), 0, 2);
        pane.add(new Label("Ending Time: "), 0, 3);
        pane.add(new Label("Movie Title: "), 3, 1);
        pane.add(new Label("Number of Tickets: "), 3, 2);
        Spinner<Integer> numberOfTickets = new Spinner<>(0, 10, 0, 1);
        pane.add(numberOfTickets, 4, 2);
        pane.add(new Label("Name: "), 3, 3);
        pane.add(new Label("----------"), 2, 1);
        pane.add(new Label("----------"), 2, 2);
        pane.add(new Label("----------"), 2, 3);
        TextField name = new TextField();
        pane.add(name, 4, 3);
        Button purchase = new Button("Purchase");
        pane.add(purchase, 0, 4);

        pane.setVgap(10);
        pane.setHgap(10);
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(20, 10, 10, 10));

        container.getChildren().add(pane);

    }

    private void forceNumeric(TextField field) {
        // force the field to be numeric only
        field.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    field.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

    private LocalDateTime startOfShowing(String date, String time) {
        DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd H:m");
        LocalDateTime startTime = LocalDateTime.parse(date + " " + time, dtFormatter);
        return startTime;
    }

    public LocalDateTime calculateEndTime(LocalDateTime startTime, LocalTime duration) {

        int hours = duration.getHour();
        int minutes = duration.getMinute();
        LocalDateTime endOfMovie = startTime.plusHours(hours).plusMinutes(minutes);

        return endOfMovie;
    }

    private void getEndingTimeForEndTimeLabel(ComboBox cbMovie, DatePicker startDate, TextField startTime, Label lEndTime) {
        Movie selectedMovie = (movieController.getMovieByName((cbMovie.getSelectionModel().getSelectedItem()).toString()));
        LocalDateTime endTime = calculateEndTime(startOfShowing(startDate.getValue().toString(), startTime.getText()), selectedMovie.getDuration());
        lEndTime.setText(endTime.toString());
    }

    private void getEndingTimeAndPriceForLabels(ComboBox cbMovie, DatePicker startDate, TextField startTime, Label lEndTime, Label moviePrice) {
        Movie selectedMovie = (movieController.getMovieByName((cbMovie.getSelectionModel().getSelectedItem()).toString()));
        LocalDateTime endTime = calculateEndTime(startOfShowing(startDate.getValue().toString(), startTime.getText()), selectedMovie.getDuration());
        lEndTime.setText(endTime.toString());
        double selectedMoviePrice = selectedMovie.getTicketPrice();
        moviePrice.setText(Double.toString(selectedMoviePrice));
    }

    public GridPane buildAddMovieForm() {
        GridPane pane = new GridPane();

        Text text = new Text("Add Movie");
        text.setFont(Font.font("verdana", FontWeight.BOLD, 15));
        pane.add(text, 0, 0);


        pane.add(new Label("Movie Name : "), 0, 1);
        TextField movieName = new TextField();
        pane.add(movieName, 1, 1);
        pane.add(new Label("Duration: "), 0, 2);
        TextField movieDuration = new TextField("02:30");
        pane.add(movieDuration, 1, 2);
        pane.add(new Label("Ticket Price: "), 0, 3);
        TextField ticketPrice = new TextField();
        pane.add(ticketPrice, 1, 3);

        Button btnAddMovie = new Button("Add Movie");
        pane.add(btnAddMovie, 0, 4);
        btnAddMovie.setOnAction(actionEvent -> {
            try {
                String name = movieName.getText();
                LocalTime duration = getMovieDuration(movieDuration.getText());
                double price = Double.parseDouble(ticketPrice.getText());
                Movie movie = new Movie(name,duration,price);
                movieController.addMovie(movie);
                new PopUpWindow(this.window, this.db, this.user, "Successfully added a Movie");

            } catch (Exception e) {
                new PopUpWindow("Please make sure to fill all the fields with the right Values");
            }

        });

        pane.setVgap(10);
        pane.setHgap(10);
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(20, 10, 10, 10));

        return pane;
    }

    private LocalTime getMovieDuration(String duration) {
        DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("H:m");
        LocalTime startTime = LocalTime.parse(duration, dtFormatter);
        return startTime;
    }
}
