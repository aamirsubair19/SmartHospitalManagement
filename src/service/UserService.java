package service;

import dao.UserDAO;
import model.User;

public class UserService {

    private UserDAO userDAO;


    public UserService() {

        userDAO = new UserDAO();
    }


    public User authenticate(
            String username,
            String password
    ) {

        return userDAO.login(
                username,
                password
        );
    }


    public void registerUser(
            User user
    ) {

        userDAO.addUser(
                user
        );
    }
}