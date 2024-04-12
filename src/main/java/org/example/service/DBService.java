package org.example.service;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBService {
    public DBService() {
    }

    public static Connection connect() {
        try {
            Driver pgDriver = (Driver) Class.forName("org.postgresql.Driver").newInstance();
            DriverManager.registerDriver(pgDriver);

            Properties properties = new Properties();
            properties.put("login", "root");
            properties.put("password", "123");
            String url = "jdbc:postgresql://localhost:5432/postgres";

            Connection connection = DriverManager.getConnection(url, properties);
            return connection;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e ) {
            System.out.println(e.getMessage());
        }

        return null;
    }
}
