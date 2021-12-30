package nl.inholland.javafx.Controller;

import nl.inholland.javafx.Model.Role;
import nl.inholland.javafx.Model.User;

import java.util.ArrayList;
import java.util.List;

public class LoginController {

    private List<User> users;

    public LoginController() {
        users = initiateUserDB();
    }

    public User login(String username, String password){
        User user = null;
        for (User u: users) {
            if (u.getUserName().equals(username)&&u.getPassword().equals(password))
                user = u;
        }
        return user;
    }

    private List<User> initiateUserDB(){
        List<User> userList = new ArrayList<>();
        User user1 = new User("Shuker","Barbour","shuker.b","password");
        User user2 = new User("user","user","user","user");
        User user3 = new User("admin","admin","admin","admin");
        user3.setUserRole(Role.ADMIN);
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);

        return userList;
    }


}
