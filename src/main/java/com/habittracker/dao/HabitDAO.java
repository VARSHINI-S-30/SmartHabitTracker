package com.habittracker.dao;

import com.habittracker.model.Habit;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HabitDAO {

    public static void addHabit(Habit habit) {
        String sql = "INSERT INTO habits (username, habit_name, goal, progress) VALUES (?, ?, ?, 0)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, habit.getUsername());
            ps.setString(2, habit.getHabitName());
            ps.setInt(3, habit.getGoal());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Habit> getHabitsByUsername(String username) {
        List<Habit> habits = new ArrayList<>();
        String sql = "SELECT * FROM habits WHERE username = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Habit habit = new Habit(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("habit_name"),
                        rs.getInt("goal"),
                        rs.getInt("progress")
                );
                habits.add(habit);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return habits;
    }

    public static void incrementProgress(int id) {
        String sql = "UPDATE habits SET progress = progress + 1 WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateHabit(int id, String habitName, int goal) {
        String sql = "UPDATE habits SET habit_name = ?, goal = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, habitName);
            ps.setInt(2, goal);
            ps.setInt(3, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteHabit(int id) {
        String sql = "DELETE FROM habits WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
