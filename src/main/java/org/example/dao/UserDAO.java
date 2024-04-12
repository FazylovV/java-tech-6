package org.example.dao;

import org.example.Executor.Executor;
import org.example.model.User;

import java.sql.Connection;

public class UserDAO {
    private final Executor executor;
    public UserDAO(Connection connection){
        executor = new Executor(connection);
    }

    public User getUserByLogin(String login) {
        return executor.executeQuery("select * from users u where u.login = '" + login + "'",
                set -> new User(
                        set.getString("login"),
                        set.getString("password"),
                        set.getString("email")
                ));
    }

    public void addUser(User user) {
        executor.executeUpdate("insert into users(login, password, email) values ('" + user.getLogin()
                + "', '" + user.getPassword() + "', '" + user.getEmail() + "')");
    }
}
