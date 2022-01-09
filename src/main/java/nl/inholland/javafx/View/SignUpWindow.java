package nl.inholland.javafx.View;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import nl.inholland.javafx.Controller.DataBase;
import nl.inholland.javafx.Controller.UserController;
import nl.inholland.javafx.Model.User;

public class SignUpWindow {

    private final UserController controller;
    private final Stage window;
    private final DataBase db;
    private final Scene scene;

    private final Label lFirstname = new Label("First Name:");
    private final TextField tfFirstname = new TextField();
    private final Label lLastname = new Label("Last Name:");
    private final TextField tfLastname = new TextField();
    private final Label lUsername = new Label("Username:");
    private final TextField tfUsername = new TextField();
    private final Label lPassword = new Label("Password:");
    private final PasswordField pfPassword = new PasswordField();
    private final Label lRepeatPassword = new Label("Repeat Password:");
    private final PasswordField pfRepeatPassword = new PasswordField();

    public SignUpWindow(UserController controller, Stage window, DataBase db) {
        this.controller = controller;
        this.window = window;
        this.db = db;
        this.scene = buildSignUpScene();
        startWindow();
    }

    private Scene buildSignUpScene() {
        GridPane pane = new GridPane();
        addElementsToPane(pane);
        pane.setAlignment(Pos.CENTER);
        pane.setVgap(5);
        pane.setHgap(50);

        return new Scene(pane, 400, 400);
    }

    private void startWindow() {
        window.setScene(scene);
        String title = "SignUp Window -- Cinema JavaFX application";
        window.setTitle(title);
        window.show();
    }

    private void addElementsToPane(GridPane pane) {

        Button btnSignUp = new Button("Sign Up");

        pane.add(lFirstname, 0, 0);
        pane.add(tfFirstname, 1, 0);
        pane.add(lLastname, 0, 1);
        pane.add(tfLastname, 1, 1);
        pane.add(lUsername, 0, 2);
        pane.add(tfUsername, 1, 2);
        pane.add(lPassword, 0, 3);
        pane.add(pfPassword, 1, 3);
        pane.add(lRepeatPassword, 0, 4);
        pane.add(pfRepeatPassword, 1, 4);
        pane.add(btnSignUp, 0, 5);

        btnSignUp.setOnAction(actionEvent -> signupAction());


    }

    private void signupAction() {
        String username = tfUsername.getText();
        String firstName = tfFirstname.getText();
        String lastName = tfLastname.getText();
        String password = pfPassword.getText();
        String repeatPassword = pfRepeatPassword.getText();
        if (username.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || password.isEmpty() || repeatPassword.isEmpty()) {
            new PopUpWindow("Please Make sure to fill all the fields");
        } else if (controller.checkIfUsernameAlreadyExist(username)) {
            new PopUpWindow("Username is taken");
            tfUsername.clear();
        } else if (!password.equals(repeatPassword)) {
            new PopUpWindow("Make sure Password and Repeat Password Matches");
            pfPassword.clear();
            pfRepeatPassword.clear();
        } else {
            tfUsername.clear();
            pfPassword.clear();
            pfRepeatPassword.clear();
            tfFirstname.clear();
            tfLastname.clear();

            User newUser = new User(firstName, lastName, username, password);
            controller.addUserToDB(newUser);
            new MainWindow(window, newUser, db);
        }

    }
}
