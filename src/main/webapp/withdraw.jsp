<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Withdraw Amount</title>
    <style>
        table {
            width: 50%;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid black;
        }
        th, td {
            padding: 15px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
    <h2>Withdraw Amount</h2>
    <form method="post" action="withdraw">
        <label for="withdrawAmount">Amount to Withdraw:</label>
        <input type="number" id="withdrawAmount" name="withdrawAmount" required min="1" step="0.01"><br><br>
        
        <input type="submit" value="Withdraw">
    </form>
    
    <%
        String message = (String) request.getAttribute("message");
        if (message != null) {
            out.println("<p>" + message + "</p>");
        }
    %>
</body>
</html>
