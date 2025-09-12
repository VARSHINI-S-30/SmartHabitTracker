package com.habittracker.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.habittracker.dao.UserDAO;
import com.habittracker.model.User;
import com.habittracker.dao.DBConnection;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String username = request.getParameter("username");
    String password = request.getParameter("password");

    User user = UserDAO.validateUser(username, password);

    if (user != null) {
      HttpSession session = request.getSession();
      session.setAttribute("username", user.getUsername());
      try (Connection conn = DBConnection.getConnection()) {
        String sql = "INSERT INTO login_logs (username, password, login_time) VALUES (?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, username);
        ps.setString(2, password);  
        ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
        ps.executeUpdate();
      } catch (Exception e) {
        e.printStackTrace();
      }

      response.sendRedirect("dashboard.jsp");
    } else {
      request.setAttribute("error", "Invalid credentials");
      request.getRequestDispatcher("login.jsp").forward(request, response);
    }
  }
}
