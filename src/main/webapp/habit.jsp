<%@ page import="java.util.*, com.habittracker.model.Habit" %>
<%@ page import="java.util.*, com.habittracker.dao.NotificationDAO, com.habittracker.model.Notification" %>

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
    
    .badge-danger  { background-color: red; }
    .actions {
  display: flex;
  gap: 10px;
  justify-content: center;
}

.actions {
  display: flex;
  gap: 10px;
  justify-content: center;
}
.action-form {
  display: inline;
}

.btn {
  padding: 6px 12px;
  border-radius: 6px;
  border: none;
  font-size: 14px;
  cursor: pointer;
}

.btn-progress {
  background-color: #4CAF50;
  color: white;
}

.btn-progress:disabled {
  background-color: #9e9e9e;
  cursor: not-allowed;
}

.btn-delete {
  background-color: #f44336;
  color: white;
}


/* Badge */
.badge {
  padding: 6px 12px;
  border-radius: 20px;
  font-weight: bold;
  color: white;
}

.badge-success {
  background-color: #28a745;
}

.badge-warning {
  background-color: orange;
}


@keyframes pulse {
  0% { transform: scale(1); }
  50% { transform: scale(1.08); }
  100% { transform: scale(1); }
}

.pulse {
  animation: pulse 1.2s infinite;
}
    
  </style>
</head>
<body>

  <h2>Add New Habit</h2>
<a href="<%= request.getContextPath() %>/analytics" style="
  padding:10px 15px;
  background:#4CAF50;
  color:white;
  border-radius:8px;
  text-decoration:none;">
View Analytics
</a>


   <form method="post" action="<%= request.getContextPath() %>/HabitServlet">
    <input type="hidden" name="action" value="add"/>
    <input type="text" name="habitName" placeholder="Habit name" required />
    <input type="number" name="goal" placeholder="Goal" required />
    <button type="submit">Add Habit</button>
</form>

<%
String user = (String) session.getAttribute("username");
List<Notification> notes = NotificationDAO.getUnread(user);
%>

<h3>Notifications</h3>

<% if (!notes.isEmpty()) { %>
  <ul>
  <% for (Notification n : notes) { %>
    <li>
      <%= n.getMessage() %>
      <form method="post" action="readNotification" style="display:inline;">
        <input type="hidden" name="id" value="<%= n.getId() %>">
        <button>Mark read</button>
      </form>
    </li>
  <% } %>
  </ul>
<% } else { %>
  <p>No new notifications </p>
<% } %>

  <h2>Your Habits</h2>
  <table>
  <tr>
    <th>Habit</th>
    <th>Goal</th>
    <th>Progress</th>
    <th>Status</th>
    <th>Actions</th>
  </tr>

<%
List<Habit> habits = (List<Habit>) request.getAttribute("habits");
if (habits != null && !habits.isEmpty()) {
  for (Habit h : habits) {

    int progress = h.getProgress();
    int goal = h.getGoal();
    int percent = (int)((progress * 100.0) / goal);

    String status;
    String badgeClass;

    if (progress == 0) {
        status = "Not Started";
        badgeClass = "badge-danger";
    } else if (progress < goal) {
        status = "In Progress";
        badgeClass = "badge-warning";
    } else {
        status = "Completed";
        badgeClass = "badge-success";
    }
%>

<tr>
  <td><%= h.getHabitName() %></td>
  <td><%= goal %></td>

  <!-- Progress bar -->
  <td>
    <div class="progress-bar">
      <div class="progress-fill"
           style="width:<%= Math.min(percent,100) %>%;">
        <%= progress %> / <%= goal %>
      </div>
    </div>
  </td>

  <!-- Status -->
  <td>
    <span class="badge <%= badgeClass %>">
      <%= status %>
    </span>
  </td>

  <!-- Actions -->
  <td class="actions">
    <!-- +1 button -->
    <form method="post" action="HabitServlet">
      <input type="hidden" name="id" value="<%= h.getId() %>">
      <button type="submit"
        name="action"
        value="progress"
        class="btn btn-progress"
        <%= progress >= goal ? "disabled" : "" %>>
  +1
</button>

    </form>

    <!-- Delete button -->
    <form method="post" action="HabitServlet">
      <input type="hidden" name="id" value="<%= h.getId() %>">
      <button type="submit"
              name="action"
              value="delete"
              class="btn btn-delete">
      </button>
    </form>
  </td>
</tr>

<%
  }
} else {
%>
<tr>
  <td colspan="5">No habits found. Add one above!</td>
</tr>
<% } %>
</table>


</body>
</html>
