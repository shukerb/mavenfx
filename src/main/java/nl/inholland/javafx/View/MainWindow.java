package nl.inholland.javafx.View;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import nl.inholland.javafx.Controller.DataBase;
import nl.inholland.javafx.Model.Role;
import nl.inholland.javafx.Model.User;

public class MainWindow {

    private boolean loggedIn = true;
    private String TITLE;
    private User user;
    private Scene scene;
    private Stage window;
    private DataBase db;

    public MainWindow(Stage window, User user, DataBase db) {
        this.window = window;
        this.user = user;
        this.db= db;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    final Menu miTickets = new Menu("Tickets");
    final Menu miShowings = new Menu("Manage Showings");
    final Menu miMovies = new Menu("Manage Movies");
    final Menu miHelp = new Menu("Help");
    final Menu miLogout = new Menu("Logout");

    public Scene buildMainScene() {
        GridPane pane = new GridPane();

        MenuBar menuBar = new MenuBar();
        if (user.getUserRole().equals(Role.ADMIN)) {
            menuBar.getMenus().addAll(miShowings, miMovies);
        }
        menuBar.getMenus().addAll(miTickets, miHelp, miLogout);
        pane.add(menuBar, 0, 0);

        ListView lvRoom1 = new ListView();

        lvRoom1.getItems().add("Item 1");
        lvRoom1.getItems().add("Item 2");
        lvRoom1.getItems().add("Item 3");

        pane.add(lvRoom1, 1, 1);
        Scene view = new Scene(pane, 800, 800);

        return view;
    }
}
