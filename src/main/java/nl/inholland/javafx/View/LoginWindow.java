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


    private String title = "LoginWindow -- Cinema JavaFX application";
    private UserController controller;
    private Stage window;
    private User user;
    private DataBase db;

    final Label lUsername = new Label("Username:");
    final Label lPassword = new Label("Password:");
    final TextField tfUsername = new TextField();
    final PasswordField pfPassword = new PasswordField();
    final Button btLogin = new Button("Login");
    private Label lError = new Label("");

    private Scene scene;

    public LoginWindow(Stage window, DataBase db) {
        this.db = db;
        controller = new UserController(db);
        this.scene = buildLoginScene();
        this.window = window;
        startWindow();

    }

    private void startWindow() {
        window.setScene(getScene());
        window.setTitle(title);
        window.show();
    }

    public Scene getScene() {
        return scene;
    }

    private Scene buildLoginScene() {
        GridPane pane = new GridPane();
        pane.add(lUsername, 0, 0);
        pane.add(tfUsername, 1, 0);
        pane.add(lPassword, 0, 1);
        pane.add(pfPassword, 1, 1);
        pane.add(btLogin, 1, 3);
        pane.add(lError, 0, 5);
        pane.setAlignment(Pos.CENTER);
        pane.setVgap(5);
        pane.setHgap(5);
        lError.setTextFill(Color.web("#FF0000"));
        btLogin.setOnAction(actionEvent -> loginAction(tfUsername.getText(), pfPassword.getText()));

        scene = new Scene(pane, 400, 400);

        return scene;
    }

    private void loginAction(String username, String password) {
        user = controller.login(username, password);
        tfUsername.clear();
        pfPassword.clear();
        if (user != null) {
            MainWindow mainWindow = new MainWindow(window, user, db);
            this.user = null;
        }
        //set the error message here
        lError.setText("Login failed");


    }


}
