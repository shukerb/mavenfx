package nl.inholland.javafx.View;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import nl.inholland.javafx.Controller.DataBase;
import nl.inholland.javafx.Controller.UserController;
import nl.inholland.javafx.Model.User;

public class LoginWindow {


    private final UserController controller;
    private final Stage window;
    private final DataBase db;

    private final Label lUsername = new Label("Username:");
    private final Label lPassword = new Label("Password:");
    private final TextField tfUsername = new TextField();
    private final PasswordField pfPassword = new PasswordField();
    private final Button btLogin = new Button("Login");
    private final Button btSignUp = new Button("SignUp");
    private final Label lError = new Label("");

    private Scene scene;

    public LoginWindow(Stage window, DataBase db) {
        this.db = db;
        controller = new UserController(db);
        this.scene = buildLoginScene();
        this.window = window;
        startWindow();

    }

    private void startWindow() {
        window.setScene(scene);
        String title = "LoginWindow -- Cinema JavaFX application";
        window.setTitle(title);
        window.show();
    }

    private Scene buildLoginScene() {
        GridPane pane = new GridPane();
        pane.add(lUsername, 0, 0);
        pane.add(tfUsername, 1, 0);
        pane.add(lPassword, 0, 1);
        pane.add(pfPassword, 1, 1);
        pane.add(btLogin, 1, 3);
        pane.add(btSignUp,2,3);
        pane.add(lError, 0, 5);
        pane.setAlignment(Pos.CENTER);
        pane.setVgap(5);
        pane.setHgap(5);
        lError.setTextFill(Color.web("#FF0000"));
        btLogin.setOnAction(actionEvent -> loginAction(tfUsername.getText(), pfPassword.getText()));
        btSignUp.setOnAction(actionEvent -> new SignUpWindow(controller,window,db));

        scene = new Scene(pane, 400, 400);

        return scene;
    }

    private void loginAction(String username, String password) {
        User user = controller.login(username, password);
        tfUsername.clear();
        pfPassword.clear();
        if (user != null) {
            MainWindow mainWindow = new MainWindow(window, user, db);
            user = null;
        }
        //set the error message here
        lError.setText("Login failed");


    }


}
