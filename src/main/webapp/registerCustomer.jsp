<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register Customer</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .form-container {
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
        input[type="text"], input[type="email"], input[type="tel"], input[type="number"], input[type="date"], select {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        input[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <div class="form-container">
        <h2>Register Customer</h2>
        <form action="RegisterCustomerServlet" method="post">
            <input type="text" name="fullName" placeholder="Full Name" required>
            <input type="text" name="address" placeholder="Address" required>
            <input type="tel" name="mobileNo" placeholder="Mobile No" required>
            <input type="email" name="email" placeholder="Email ID" required>
            <select name="accountType" required>
                <option value="" disabled selected>Select Account Type</option>
                <option value="Saving">Saving Account</option>
                <option value="Current">Current Account</option>
            </select>
            <input type="number" name="initialBalance" placeholder="Initial Balance" min="1000" required>
            <input type="date" name="dob" placeholder="Date of Birth" required>
            <input type="text" name="idProof" placeholder="ID Proof" required>
            <input type="submit" value="Register Customer">
        </form>
    </div>
</body>
</html>
