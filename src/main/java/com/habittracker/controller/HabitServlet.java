package com.habittracker.controller;

import com.habittracker.dao.HabitDAO;
import com.habittracker.model.Habit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/HabitServlet")
public class HabitServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public HabitServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String username = (session != null) ? (String) session.getAttribute("username") : null;

        if (username == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        List<Habit> habits = HabitDAO.getHabitsByUsername(username);
        request.setAttribute("habits", habits);
        request.getRequestDispatcher("habit.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession(false);
        String username = (session != null) ? (String) session.getAttribute("username") : null;

        if (username == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        if ("add".equals(action)) {
            String habitName = request.getParameter("habitName");
            int goal = Integer.parseInt(request.getParameter("goal"));
            Habit habit = new Habit(username, habitName, goal);
            HabitDAO.addHabit(habit);
        } else {
            int id = Integer.parseInt(request.getParameter("id"));

            if ("progress".equals(action)) {
                HabitDAO.incrementProgress(id);
            } else if ("edit".equals(action)) {
                String habitName = request.getParameter("habitName");
                int goal = Integer.parseInt(request.getParameter("goal"));
                HabitDAO.updateHabit(id, habitName, goal);
            } else if ("delete".equals(action)) {
                HabitDAO.deleteHabit(id);
            }
        }

        response.sendRedirect("HabitServlet");
    }
}
