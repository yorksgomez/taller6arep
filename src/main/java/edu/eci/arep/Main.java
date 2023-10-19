package edu.eci.arep;



import static spark.Spark.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


/**
 * Hello world!
 *
 */
public class Main 
{
    public static void main( String[] args ) throws Exception {

        port(getPort());
//        secure("keystores/ecikeypair.p12", "abc123", null, null);

        before("/admin/*", (request, response) -> {
            String token = request.headers("Authorization");
            if (token == null || !token.startsWith("Bearer ")) {
                halt(401, "Token no proporcionado");
            }

            String userId = TokenManager.verifyToken(token.substring(7));
            if (userId == null) {
                halt(401, "Token no válido");
            }
        });

        post("/login", (request, response) -> {
           return UserController.loginUser(request, response);
        });

        post("/register", (request, response) -> {
            return UserController.registerUser(request, response);
        });

        get("/admin/todos", (request, response) -> {
            String userId = request.queryParams("userId"); // Debes obtener el ID del usuario autenticado aquí
            List<Todo> todos = TodoController.getTodosForUser(userId);
            return todos;
        });

        post("/admin/todos", (request, response) -> {
            String userId = request.queryParams("userId"); // Debes obtener el ID del usuario autenticado aquí
            String value = request.queryParams("value");
            
            if (TodoController.createTodoForUser(userId, value)) {
                return "Todo creado exitosamente";
            } else {
                response.status(500);
                return "Error al crear el todo";
            }
        });

    }

    public static int getPort() {
        return System.getenv("PORT") != null ? Integer.valueOf(System.getenv("PORT")) : 5000;
    }

}
