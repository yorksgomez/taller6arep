package edu.eci.arep;

public class Todo {
    private int id;
    private String userId;
    private String value;

    public Todo(int id, String userId, String value) {
        this.id = id;
        this.userId = userId;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getValue() {
        return value;
    }

}