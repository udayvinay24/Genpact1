<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration Success</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .success-container {
            background-color: #ffffff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 400px;
            text-align: center;
        }
        h2 {
            color: #333333;
        }
    </style>
</head>
<body>
    <div class="success-container">
        <h2>Customer Registered Successfully!</h2>
        <p>Account Number: ${accountNumber}</p>
        <p>Password: ${password}</p>
        <a href="adminDashboard.jsp">Go Back to Dashboard</a>
    </div>
</body>
</html>
