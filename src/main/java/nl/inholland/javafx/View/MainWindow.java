package nl.inholland.javafx.View;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import nl.inholland.javafx.Controller.DataBase;
import nl.inholland.javafx.Model.*;

public class MainWindow {

    private boolean loggedIn = true;
    private String title;
    private User user;
    private Scene scene;
    private Stage window;
    private boolean isAdmin;
    private DataBase db;
    private Utils utils;
    private VBox container;

    public MainWindow(Stage window, User user, DataBase db) {
        this.db = db;
        this.window = window;
        this.user = user;
        this.isAdmin = user.getUserRole().equals(Role.ADMIN);
        this.utils = new Utils(db, user, window);
        startWindow(ButtonClicked.BUY_TICKETS);
    }

    public Scene getScene() {
        return scene;
    }


    public Scene buildMainScene(ButtonClicked option) {
        container = new VBox();
        MenuBar menuBar = createMenuBar();
        container.getChildren().add(menuBar);

        if (option.equals(ButtonClicked.MANAGE_SHOWINGS)) {
            this.title = "Cinema MS ---- Manage Showings ---- Welcome " + user.getFirstName() + " " + user.getLastName();
            utils.buildShowingsPane(container, isAdmin);
            GridPane manageShowingForm = utils.buildAddShowingForm();
            container.getChildren().add(manageShowingForm);

        } else if (option.equals(ButtonClicked.MANAGE_MOVIES)) {
            this.title = "Cinema MS ---- Manage Movies ---- Welcome " + user.getFirstName() + " " + user.getLastName();
            utils.buildMoviesPane(container);
            GridPane manageMoviesForm = utils.buildAddMovieForm();
            container.getChildren().add(manageMoviesForm);

        } else {
            this.title = "Cinema MS ---- Showings Available ---- Welcome " + user.getFirstName() + " " + user.getLastName();
            utils.buildShowingsPane(container, isAdmin);
        }


        Scene view = new Scene(container, 1200, 800);
        return view;
    }

    private MenuBar createMenuBar() {
        MenuBar menuBar = new MenuBar();

        Menu admin = new Menu("Admin");
        MenuItem manageShowing = new MenuItem("Manage Showings");
        manageShowing.setOnAction(actionEvent -> startWindow(ButtonClicked.MANAGE_SHOWINGS));

        MenuItem manageMovies = new MenuItem("Manage Movies");
        manageMovies.setOnAction(actionEvent -> startWindow(ButtonClicked.MANAGE_MOVIES));

        admin.getItems().addAll(manageMovies, manageShowing);


        Menu help = new Menu("Help");

        Menu logout = new Menu("Logout");
        MenuItem miLogout = new MenuItem("Logout");
        miLogout.setOnAction(actionEvent -> logoutAction());
        logout.getItems().add(miLogout);

        if (isAdmin) {
            menuBar.getMenus().addAll(admin);
        }
        menuBar.getMenus().addAll(help, logout);


        return menuBar;
    }

    private void logoutAction() {
        new LoginWindow(this.window, this.db);
    }


    private void startWindow(ButtonClicked option) {
        this.scene = buildMainScene(option);
        this.window.setScene(getScene());
        this.window.setTitle(title);
        this.window.show();
    }
}
