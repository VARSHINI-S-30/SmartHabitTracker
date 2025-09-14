package com.habittracker.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;      
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.habittracker.dao.UserDAO;
import com.habittracker.model.User;

@WebServlet("/register")

public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User newUser = new User(name, email, username, password);

        boolean isRegistered = UserDAO.registerUser(newUser);
        if (isRegistered) {
            response.sendRedirect("login.jsp");
        } 
        else {
            request.setAttribute("error", "Registration failed.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}
