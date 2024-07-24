<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Deposit Amount</title>
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
    <h2>Deposit Amount</h2>
    <form method="post" action="deposit">
        <label for="depositAmount">Amount to Deposit:</label>
        <input type="number" id="depositAmount" name="depositAmount" required min="1" step="0.01"><br><br>
        
        <input type="submit" value="Deposit">
    </form>
    
    <%
        String message = (String) request.getAttribute("message");
        if (message != null) {
            out.println("<p>" + message + "</p>");
        }
    %>
</body>
</html>
