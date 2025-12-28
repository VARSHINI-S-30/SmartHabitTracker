<%@ page import="java.util.Map" %>
<!DOCTYPE html>
<html>
<head>
    <title>Habit Analytics</title>
</head>
<body>

<h2>Habit Analytics</h2>

<%
Map<String, Integer> data =
        (Map<String, Integer>) request.getAttribute("analyticsData");
%>

<ul>
<% for (Map.Entry<String, Integer> entry : data.entrySet()) { %>
    <li><%= entry.getKey() %>: <%= entry.getValue() %></li>
<% } %>
</ul>

<a href="HabitServlet"> Back</a>

</body>
</html>
