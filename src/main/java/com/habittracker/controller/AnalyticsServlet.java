package com.habittracker.controller;

import com.habittracker.dao.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.Map;

@WebServlet("/AnalyticsServlet")
public class AnalyticsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        String username = (session != null) ? (String) session.getAttribute("username") : null;

        if (username == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int completed = 0;
        int inProgress = 0;
        int notStarted = 0;

        String sql = """
            SELECT goal, progress, completed
            FROM habits
            WHERE username = ?
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int goal = rs.getInt("goal");
                int progress = rs.getInt("progress");
                boolean isCompleted = rs.getBoolean("completed");

                if (isCompleted) {
                    completed++;
                } else if (progress > 0) {
                    inProgress++;
                } else {
                    notStarted++;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Data for chart
        Map<String, Integer> analyticsData = new LinkedHashMap<>();
        analyticsData.put("Completed", completed);
        analyticsData.put("In Progress", inProgress);
        analyticsData.put("Not Started", notStarted);

        request.setAttribute("analyticsData", analyticsData);
        request.getRequestDispatcher("analytics.jsp").forward(request, response);
    }
}
