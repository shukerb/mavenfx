package nl.inholland.javafx.View;

import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import nl.inholland.javafx.Controller.DataBase;
import nl.inholland.javafx.Model.*;

public class MainWindow {

    private String title;
    private final User user;
    private Scene scene;
    private final Stage window;
    private final boolean isAdmin;
    private final DataBase db;
    private final Utils utils;

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
        VBox container = new VBox();
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


        return new Scene(container, 1200, 600);
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

        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        window.setX((primScreenBounds.getWidth() - window.getWidth()) / 2);
        window.setY((primScreenBounds.getHeight() - window.getHeight()) / 2);
    }
}
