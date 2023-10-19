package edu.eci.arep;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TodoController {
    
    public static List<Todo> getTodosForUser(String userId) {
        List<Todo> todos = new ArrayList<>();
        Connection dbConnection = Persistence.getConnection();

        try {
            String query = "SELECT * FROM todos WHERE user_id = ?";
            PreparedStatement statement = dbConnection.prepareStatement(query);
            statement.setString(1, userId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String value = resultSet.getString("value");
                todos.add(new Todo(id, userId, value));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return todos;
    }

    public static boolean createTodoForUser(String userId, String value) {
        Connection dbConnection = Persistence.getConnection();

        try {
            String query = "INSERT INTO todos (user_id, value) VALUES (?, ?)";
            PreparedStatement statement = dbConnection.prepareStatement(query);
            statement.setString(1, userId);
            statement.setString(2, value);

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
