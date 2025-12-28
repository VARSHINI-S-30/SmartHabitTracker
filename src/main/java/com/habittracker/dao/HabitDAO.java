package com.habittracker.dao;

import com.habittracker.model.Habit;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HabitDAO {

	public static void addHabit(Habit habit) {
	    String sql = "INSERT INTO habits (username, habit_name, goal, progress, completed) VALUES (?, ?, ?, 0, false)";
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
	                rs.getInt("progress"),
	                rs.getBoolean("completed")
	            );
	            habits.add(habit);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return habits;
	}


	public static void incrementProgress(int id) {

	    String fetch = "SELECT username, habit_name, goal, progress FROM habits WHERE id=?";
	    String update = "UPDATE habits SET progress = progress + 1 WHERE id=? AND progress < goal";
	    String complete = "UPDATE habits SET completed=true WHERE id=?";

	    try (Connection con = DBConnection.getConnection()) {

	        PreparedStatement f = con.prepareStatement(fetch);
	        f.setInt(1, id);
	        ResultSet rs = f.executeQuery();

	        if (!rs.next()) return;

	        String username = rs.getString("username");
	        String habit = rs.getString("habit_name");
	        int goal = rs.getInt("goal");
	        int progress = rs.getInt("progress");

	        if (progress < goal) {
	            PreparedStatement u = con.prepareStatement(update);
	            u.setInt(1, id);
	            u.executeUpdate();
	            progress++;
	        }

	        if (progress == goal) {
	            PreparedStatement c = con.prepareStatement(complete);
	            c.setInt(1, id);
	            c.executeUpdate();

	            NotificationDAO.add(username,
	                " Habit completed: " + habit);
	        }
	        else if (progress == goal - 1) {
	            NotificationDAO.add(username,
	                " Almost there! One more step for: " + habit);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}


    public static void deleteHabit(int id) {
        String sql = "DELETE FROM habits WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
