package com.habittracker.dao;

import com.habittracker.model.Notification;
import java.sql.*;
import java.util.*;

public class NotificationDAO {

    public static void add(String username, String message) {
        String sql = """
            INSERT INTO notifications(username, message, sent_time, status)
            VALUES (?, ?, CURRENT_TIMESTAMP, 'UNREAD')
        """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, message);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Notification> getUnread(String username) {

        List<Notification> list = new ArrayList<>();
        String sql = """
            SELECT * FROM notifications
            WHERE username=? AND status='UNREAD'
            ORDER BY sent_time DESC
        """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Notification(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("message"),
                    rs.getTimestamp("sent_time"),
                    rs.getString("status")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    
    public static void markRead(int id) {
        String sql = "UPDATE notifications SET status='READ' WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
