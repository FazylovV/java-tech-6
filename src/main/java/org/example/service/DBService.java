package org.example.service;

import org.example.dao.UserDAO;
import org.example.model.User;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBService {
    private final Connection connection;
    public DBService() {
        this.connection = connectPG();
    }

    public static Connection connectPG() {
        try {
            Driver pgDriver = (Driver) Class.forName("org.postgresql.Driver").newInstance();
            DriverManager.registerDriver(pgDriver);

            Properties properties = new Properties();
            properties.put("user", "root");
            properties.put("password", "123");
            String url = "jdbc:postgresql://localhost:5432/java_tech";

            Connection connection = DriverManager.getConnection(url, properties);
            return connection;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e ) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public User getUserByLogin(String login) {
        return (new UserDAO(connection).getUserByLogin(login));
    }

    public void addUser(User user) {
        try {
            connection.setAutoCommit(false);
            UserDAO dao = new UserDAO(connection);
            dao.addUser(user);
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ignore) {
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ignore) {
            }
        }
    }
}
