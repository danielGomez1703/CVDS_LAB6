/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author daniel.gomez-su
 */
package edu.eci.cvds.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.google.gson.Gson;

import edu.eci.cvds.servlet.model.Todo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
        urlPatterns = "/services"
)

public class Service extends HttpServlet {
    private ArrayList<Todo> todosList = new ArrayList<Todo>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Writer responseWriter = resp.getWriter();
            Optional <Integer> optId = Optional.ofNullable(Integer.parseInt(req.getParameter("id")));
            Integer id = optId.isPresent() ? optId.get():1;
            todosList.add(getTodo(id));
            resp.setStatus(HttpServletResponse.SC_OK);
            responseWriter.write(todosToHTMLTable(todosList));
            responseWriter.flush();
        }catch (IOException e){
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }

    }

    public static Todo getTodo(int id) throws MalformedURLException, IOException {
        URL urldemo = new URL("https://jsonplaceholder.typicode.com/todos/" + id);
        URLConnection yc = urldemo.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
        Gson gson = new Gson();
        Todo todo = gson.fromJson(in, Todo.class);
        in.close();
        return todo;
    }

    private static String todoToHTMLRow(Todo todo) {
        return new StringBuilder("<tr>")
                .append("<td>")
                .append(todo.getUserId())
                .append("</td><td>")
                .append(todo.getId())
                .append("</td><td>")
                .append(todo.getTitle())
                .append("</td><td>")
                .append(todo.getCompleted())
                .append("</td>")
                .append("</tr>")
                .toString();
    }

    public static String todosToHTMLTable(List<Todo> todoList) {
        StringBuilder stringBuilder = new StringBuilder("<table>")
                .append("<tr>")
                .append("<th>User Id</th>")
                .append("<th>Id</th>")
                .append("<th>Title</th>")
                .append("<th>Completed</th>")
                .append("</tr>");

        for (Todo todo : todoList) {
            stringBuilder.append(todoToHTMLRow(todo));
        }

        return stringBuilder.append("</table>").toString();
    }
}