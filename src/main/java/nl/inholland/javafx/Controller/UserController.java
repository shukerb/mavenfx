package nl.inholland.javafx.Controller;

import nl.inholland.javafx.Model.Role;
import nl.inholland.javafx.Model.User;

import java.util.ArrayList;
import java.util.List;

public class UserController {

    private List<User> users;

    public UserController(DataBase db) {
        this.users = db.getUsers();
    }

    public User login(String username, String password) {
        User user = null;
        for (User u : users) {
            if (u.getUserName().toLowerCase().equals(username.toLowerCase()) && u.getPassword().toLowerCase().equals(password.toLowerCase()))
                user = u;
        }
        return user;
    }

    public void addUserToDB(User user) {
        this.users.add(user);
    }

    public void editUser(User editedUser) {

        for (User user : users) {
            if (user.getUserId().equals(editedUser.getUserId())){
                removeUserFromDB(user);
                addUserToDB(editedUser);
            }
        }
    }

    public void removeUserFromDB(User user){
        this.users.remove(user);
    }



}
