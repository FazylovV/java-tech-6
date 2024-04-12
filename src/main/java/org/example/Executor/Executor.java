package org.example.Executor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Executor {
    private final Connection connection;

    public Executor(Connection connection) {
        this.connection = connection;
    }


    public <T> T executeQuery(String query, ResultSetHandler<T> handler) {
        try (Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery(query);
            if (!result.next()) {
                return null;
            }

            return handler.handle(result);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void executeUpdate(String query) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
