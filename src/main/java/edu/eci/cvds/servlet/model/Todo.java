package edu.eci.cvds.servlet.model;

public class Todo {

    private int userId;
    private int id;
    private String title;
    private boolean completed;

    public Todo (){
        userId = 1;
        id = 1;
        title = " delectus aut autem ";
        completed = false;
    }
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean getCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
