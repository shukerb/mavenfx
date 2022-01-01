package nl.inholland.javafx;

import javafx.application.Application;
import javafx.stage.Stage;
import nl.inholland.javafx.Controller.DataBase;
import nl.inholland.javafx.View.LoginWindow;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class App extends Application {

    @Override
    public void start(Stage window) throws Exception {
        DataBase db = new DataBase();
        LoginWindow loginScene = new LoginWindow(window,db);
    }
}
