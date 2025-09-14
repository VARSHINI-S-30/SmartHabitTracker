package com.habittracker.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.habittracker.model.User;

public class UserDAO {
  public static boolean registerUser(User user) {
    try (Connection conn = DBConnection.getConnection()) {
      String sql = "INSERT INTO users (name, email, username, password) VALUES (?, ?, ?, ?)";
      PreparedStatement ps = conn.prepareStatement(sql);
      ps.setString(1, user.getName());
      ps.setString(2, user.getEmail());
      ps.setString(3, user.getUsername());
      ps.setString(4, user.getPassword());
      int rows = ps.executeUpdate();
      return rows > 0;
    } 
    catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }

  public static User validateUser(String username, String password) {
    try (Connection conn = DBConnection.getConnection()) {
      String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
      PreparedStatement ps = conn.prepareStatement(sql);
      ps.setString(1, username);
      ps.setString(2, password);
      ResultSet rs = ps.executeQuery();
      if (rs.next()) {
        return new User(rs.getString("name"), rs.getString("email"), username, password);
      }
    } 
    catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }
}
