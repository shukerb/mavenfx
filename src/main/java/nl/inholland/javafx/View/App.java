package nl.inholland.javafx.View;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class App extends Application {

    private boolean loggedIn = false;
    private String TITLE = "InHolland JavaFX Project 'Retake'";
    private boolean userRole = false;

    final Menu miTickets = new Menu("Tickets");
    final Menu miShowings = new Menu("Manage Showings");
    final Menu miMovies = new Menu("Manage Movies");
    final Menu miHelp = new Menu("Help");
    final Menu miLogout = new Menu ("Logout");

    @Override
    public void start(Stage window) throws Exception {
        GridPane pane = new GridPane();

        MenuBar menuBar = new MenuBar();
        if (userRole){
            menuBar.getMenus().addAll(miShowings, miMovies);
        }
        menuBar.getMenus().addAll(miTickets,miHelp,miLogout);
        pane.add(menuBar,0,0);

        ListView lvRoom1 = new ListView();

        lvRoom1.getItems().add("Item 1");
        lvRoom1.getItems().add("Item 2");
        lvRoom1.getItems().add("Item 3");

        pane.add(lvRoom1,1,1);
        Scene view = new Scene(pane);

        window.setScene(view);
        window.setScene(view);
        window.setHeight(800);
        window.setWidth(800);
        window.show();
    }
}
