package nl.inholland.javafx.View;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Login extends Application {

    private boolean loggedIn = false;
    private String TITLE = "InHolland JavaFX Project 'Retake'";

    final Label lUsername = new Label("Username:");
    final Label lPassword = new Label("Password:");
    final TextField tfUsername = new TextField();
    final PasswordField pfPassword = new PasswordField();
    final Button btAddUser = new Button("Login");
    private Label lError = new Label("");

    @Override
    public void start(Stage window) throws Exception {
        window.setTitle(TITLE);

        GridPane pane = new GridPane();
        pane.add(lUsername,0,0);
        pane.add(tfUsername,1,0);
        pane.add(lPassword,0,1);
        pane.add(pfPassword,1,1);
        pane.add(btAddUser,1,3);
        pane.add(lError,0,5);
        pane.setAlignment(Pos.CENTER);
        pane.setVgap(5);
        pane.setHgap(5);
        lError.setTextFill(Color.web("#FF0000"));
        //set the error message here
        lError.setText("hello");

        Scene login = new Scene(pane,400,400);
        window.setScene(login);
        window.show();
    }
}
