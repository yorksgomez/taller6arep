package edu.eci.arep;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import spark.Response;

public class UserController {
    // Servicio para iniciar sesión de usuario
    public static String loginUser(spark.Request request, Response response) {
        Connection dbConnection = Persistence.getConnection();
        String username = request.queryParams("username");
        String password = request.queryParams("password");

        try {
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement statement = dbConnection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return TokenManager.generateToken(username);
            } else {
                response.status(401);
                return "Credenciales incorrectas";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.status(500);
            return "Error en el servidor";
        }
    }

    // Servicio para registrar un nuevo usuario
    public static String registerUser(spark.Request request, Response response) {
        Connection dbConnection = Persistence.getConnection();
        String username = request.queryParams("username");
        String password = request.queryParams("password");

        try {
            String query = "INSERT INTO users (username, password) VALUES (?, ?)";
            PreparedStatement statement = dbConnection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);

            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                // Usuario registrado con éxito
                return "Registro exitoso";
            } else {
                response.status(500);
                return "Error al registrar el usuario";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.status(500);
            return "Error en el servidor";
        }
    }
}
