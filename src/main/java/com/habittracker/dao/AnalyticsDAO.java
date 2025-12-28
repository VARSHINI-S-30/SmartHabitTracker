package com.habittracker.dao;

import java.sql.*;
import java.util.*;

public class AnalyticsDAO {

    public static Map<String, Integer> getHabitProgress(String username) {
        Map<String, Integer> data = new LinkedHashMap<>();

        String sql = "SELECT habit_name, progress, goal FROM habits WHERE username = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int progress = rs.getInt("progress");
                int goal = rs.getInt("goal");

                int percent = (goal == 0) ? 0 : (int) ((progress * 100.0) / goal);
                data.put(rs.getString("habit_name"), Math.min(percent, 100));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }
}
