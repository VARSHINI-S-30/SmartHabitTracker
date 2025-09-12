<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Welcome - Smart Habit Tracker</title>
  <link rel="stylesheet" href="css/style.css">
  <style>
    body {
      background: linear-gradient(to right, #6dd5fa, #2980b9);
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
      margin: 0;
      font-family: 'Segoe UI', sans-serif;
    }

    .welcome-container {
      background-color: #ffffff;
      padding: 40px;
      border-radius: 16px;
      box-shadow: 0 10px 30px rgba(0, 0, 0, 0.2);
      text-align: center;
      width: 400px;
    }

    .welcome-container h1 {
      font-size: 32px;
      margin-bottom: 10px;
      color: #333;
    }

    .welcome-container p {
      font-size: 16px;
      color: #555;
    }

    .welcome-container button {
      padding: 12px 25px;
      margin: 15px 10px;
      border: none;
      border-radius: 10px;
      background-color: #007bff;
      color: white;
      font-size: 16px;
      cursor: pointer;
      transition: background-color 0.3s ease;
    }

    .welcome-container button:hover {
      background-color: #0056b3;
    }
  </style>
</head>
<body>
  <div class="welcome-container">
    <h1>Smart Habit Tracker</h1>
    <p>Build positive habits. Track your goals. Stay motivated.</p>
    <br>
    <a href="login.jsp"><button>Login</button></a>
    <a href="register.jsp"><button>Register</button></a>
  </div>
</body>
</html>
