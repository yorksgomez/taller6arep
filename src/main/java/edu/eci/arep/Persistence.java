package edu.eci.arep;

import static spark.Spark.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Persistence {
 
    private static Connection connection = null;

    public static Connection getConnection() {

        if(connection != null)
            return connection;

        // Establece la conexión a la base de datos
        try {
            connection = DriverManager.getConnection(Environment.DB_URL, Environment.DB_USERNAME, Environment.DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }

        return connection;
    }

}
