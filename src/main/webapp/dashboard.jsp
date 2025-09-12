<%@ page session="true" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Smart Habit Tracker - Dashboard</title>
  <style>
    body {
      font-family: 'Segoe UI', sans-serif;
      margin: 0;
      background: #f4f4f4;
    }

    .navbar {
      background-color: #333;
      overflow: hidden;
      padding: 10px 20px;
    }

    .navbar a {
      float: left;
      display: block;
      color: #f2f2f2;
      text-align: center;
      padding: 12px 16px;
      text-decoration: none;
      font-weight: bold;
    }

    .navbar a:hover {
      background-color: #ddd;
      color: black;
    }

    .navbar-right {
      float: right;
    }

    .container {
      padding: 30px;
    }

    .card {
      background-color: white;
      border-radius: 10px;
      padding: 20px;
      margin-bottom: 20px;
      box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
    }

    h2 {
      margin-top: 0;
    }
  </style>
</head>
<body>

  <!-- Navigation Bar -->
  <div class="navbar">
    <a href="dashboard.jsp">🏠 Dashboard</a>
    <a href="register.jsp">👤 User Management</a>
    <a href="habit.jsp">📋 Habit Tracking</a>
    <a href="notify.jsp">🔔 Notifications</a>
    <a href="analytics">📊 Analytics</a>
    <a href="settings.jsp">⚙️ Settings</a>
    <div class="navbar-right">
      <a href="loginHistory">📜 Login History</a>
      <a href="logout">🚪 Logout</a>
    </div>
  </div>

  <!-- Dashboard Welcome Section -->
  <div class="container">
    <div class="card">
      <h2>Welcome, <%= session.getAttribute("username") != null ? session.getAttribute("username") : "Guest" %>!</h2>
      <p>This is your personal Smart Habit Tracker dashboard. Use the menu above to manage your habits, view insights, and customize your preferences.</p>
    </div>

    <!-- Quick Access Widgets -->
    <div class="card">
      <h3>📌 Quick Access</h3>
      <ul>
        <li><a href="habit.jsp">Add or Manage Habits</a></li>
        <li><a href="analytics">View Progress and Charts</a></li>
        <li><a href="notify.jsp">Send Notification</a></li>
        <li><a href="loginHistory">See Login History</a></li>
        <li><a href="settings.jsp">Edit Profile / Preferences</a></li>
      </ul>
    </div>
  </div>

</body>
</html>
