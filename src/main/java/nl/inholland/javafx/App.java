package nl.inholland.javafx;

import javafx.application.Application;
import javafx.stage.Stage;
import nl.inholland.javafx.Controller.DataBase;
import nl.inholland.javafx.View.LoginWindow;


public class App extends Application {

    @Override
    public void start(Stage window) throws Exception {
        DataBase db = new DataBase();
        new LoginWindow(window, db);

    }
}
