<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Settings - Habit Tracker</title>
  <link rel="stylesheet" href="css/style.css">
  <style>
    .settings-container {
      width: 400px;
      margin: 100px auto;
      padding: 30px;
      background-color: #fff;
      border-radius: 16px;
      box-shadow: 0 10px 20px rgba(0, 0, 0, 0.1);
      text-align: center;
    }

    .settings-container h2 {
      margin-bottom: 20px;
    }

    .dark-mode-toggle {
      margin-top: 20px;
    }

    .dark-mode body {
      background-color: #121212 !important;
      color: #f1f1f1;
    }

    .dark-mode .settings-container {
      background-color: #1e1e1e;
      color: #f1f1f1;
    }
  </style>
</head>
<body>
  <div class="settings-container">
    <h2>Settings</h2>

    <label>
      <input type="checkbox" id="darkToggle">
      Enable Dark Mode
    </label>

    <div class="dark-mode-toggle">
      <p>Customize habit categories (coming soon...)</p>
    </div>
  </div>

  <script>
    const toggle = document.getElementById('darkToggle');
    toggle.addEventListener('change', () => {
      document.body.classList.toggle('dark-mode', toggle.checked);
    });
  </script>
</body>
</html>
