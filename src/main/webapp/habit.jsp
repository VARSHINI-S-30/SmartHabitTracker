<%@ page import="java.util.*, com.habittracker.model.Habit" %>
<%@ page session="true" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Habit Tracker</title>
  <style>
    body {
      font-family: Arial;
      background-color: #f2f2f2;
      padding: 20px;
    }
    h2 {
      color: #333;
    }
    form, table {
      background-color: #fff;
      border-radius: 10px;
      padding: 20px;
      margin-bottom: 30px;
      box-shadow: 0 2px 8px rgba(0,0,0,0.1);
    }
    input, button {
      padding: 8px;
      margin: 5px;
      border-radius: 5px;
      border: 1px solid #ccc;
    }
    button {
      cursor: pointer;
      background-color: #4CAF50;
      color: white;
    }
    table {
      width: 100%;
      border-collapse: collapse;
    }
    th, td {
      padding: 12px;
      text-align: center;
      border-bottom: 1px solid #ccc;
    }
    .progress-bar {
      height: 20px;
      border-radius: 10px;
      background-color: #ddd;
      width: 100%;
      position: relative;
    }
    .progress-fill {
      height: 100%;
      background-color: #28a745;
      border-radius: 10px;
      text-align: center;
      color: white;
      font-size: 12px;
      line-height: 20px;
    }
    .badge {
      padding: 5px 10px;
      border-radius: 12px;
      color: white;
      font-weight: bold;
    }
    .badge-success { background-color: green; }
    .badge-warning { background-color: orange; }
    .badge-danger  { background-color: red; }
  </style>
</head>
<body>

  <h2>Add New Habit</h2>
  <form method="post" action="HabitServlet">
    <input type="hidden" name="action" value="add"/>
    <input type="text" name="habitName" placeholder="Habit name (e.g., Drink Water)" required />
    <input type="number" name="goal" placeholder="Goal (e.g., 8)" required />
    <button type="submit">Add Habit</button>
  </form>

  <h2>Your Habits</h2>
  <table>
    <tr><th>Habit</th><th>Goal</th><th>Progress</th><th>Status</th><th>Actions</th></tr>
    <%
      List<Habit> habits = (List<Habit>) request.getAttribute("habits");
      if (habits != null && !habits.isEmpty()) {
        for (Habit h : habits) {
          int percent = (int) ((h.getProgress() * 100.0) / h.getGoal());
          String badgeClass = percent >= 100 ? "badge-success" :
                              percent >= 50 ? "badge-warning" : "badge-danger";
    %>
    <tr>
      <td><%= h.getHabitName() %></td>
      <td><%= h.getGoal() %></td>
      <td>
        <div class="progress-bar">
          <div class="progress-fill" style="width:<%= Math.min(percent, 100) %>%;"><%= percent %>%</div>
        </div>
      </td>
      <td><span class="badge <%= badgeClass %>">
        <%= percent >= 100 ? "Completed" : "In Progress" %>
      </span></td>
      <td>
        <form method="post" action="HabitServlet" style="display:inline;">
          <input type="hidden" name="id" value="<%= h.getId() %>" />
          <button type="submit" name="action" value="progress">+1</button>
          <button type="submit" name="action" value="edit">Edit</button>
          <button type="submit" name="action" value="delete" onclick="return confirm('Delete this habit?')">Delete</button>
        </form>
      </td>
    </tr>
    <% } } else { %>
    <tr><td colspan="5">No habits found. Add one above!</td></tr>
    <% } %>
  </table>

</body>
</html>
