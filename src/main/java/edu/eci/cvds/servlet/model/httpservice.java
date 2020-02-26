package edu.eci.cvds.servlet.model;

import edu.eci.cvds.servlet.Service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Optional;

@WebServlet(
        urlPatterns = "/services"
)

public class httpservice extends HttpServlet {
    private ArrayList<Todo> todosList = new ArrayList<Todo>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Writer responseWriter = resp.getWriter();
            Optional<Integer> optId = Optional.ofNullable(Integer.parseInt(req.getParameter("id")));
            Integer id = optId.isPresent() ? optId.get():1;
            todosList.add(Service.getTodo(id));
            resp.setStatus(HttpServletResponse.SC_OK);
            responseWriter.write(Service.todosToHTMLTable(todosList));
            responseWriter.flush();
        }catch (IOException e){
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }

    }

    @Override
    protected void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Writer responseWriter = resp.getWriter();
            Optional<Integer> optId = Optional.ofNullable(Integer.parseInt(req.getParameter("id")));
            Integer id = optId.isPresent() ? optId.get():1;
            todosList.add(Service.getTodo(id));
            resp.setStatus(HttpServletResponse.SC_OK);
            responseWriter.write(Service.todosToHTMLTable(todosList));
            responseWriter.flush();
        }catch (IOException e){
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }

    }
}
