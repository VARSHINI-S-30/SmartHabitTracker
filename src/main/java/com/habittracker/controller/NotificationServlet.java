package com.habittracker.controller;
import com.habittracker.dao.HabitDAO;
import com.habittracker.dao.NotificationDAO;
import com.habittracker.model.Habit;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;
@WebServlet("/readNotification")
public class NotificationServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        NotificationDAO.markRead(id);
        res.sendRedirect("HabitServlet");
    }
}
