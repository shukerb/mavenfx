package nl.inholland.javafx.View;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import nl.inholland.javafx.Controller.DataBase;
import nl.inholland.javafx.Model.User;


public class PopUpWindow {
    private Stage mainWindow;
    private DataBase db;
    private User user;
    private final String message;

    public PopUpWindow(Stage window, DataBase db, User user, String message) {
        this.mainWindow = window;
        this.db = db;
        this.user = user;
        this.message = message;
        start();
    }

    public PopUpWindow(String message) {
        this.message = message;
        start();
    }

    private void start() {
        Stage stage = new Stage();
        GridPane pane = new GridPane();

        Text text = new Text(this.message);
        text.setFont(Font.font("verdana", FontWeight.BOLD, 15));
        Button okButton = new Button("OK");

        okButton.setOnAction(actionEvent -> {
            if (this.user != null && this.mainWindow != null && this.db != null) {
                stage.close();
                MainWindow mainWindow = new MainWindow(this.mainWindow, this.user, this.db);
            } else {
                stage.close();
            }
        });

        pane.add(text, 0, 0);
        pane.add(okButton, 0, 1);
        pane.setAlignment(Pos.CENTER);
        pane.setVgap(15);
        pane.setHgap(15);

        Scene scene = new Scene(pane, 600, 200);
        stage.setScene(scene);
        stage.show();

    }

}
