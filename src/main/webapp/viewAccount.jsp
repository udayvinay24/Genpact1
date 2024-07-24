<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>View Account Details</title>
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
    <h2>View Account Details</h2>
    <form method="post" action="viewAccount">
        <label for="accountNumber">Enter Account Number:</label>
        <input type="text" id="accountNumber" name="accountNumber" required>
        <input type="submit" value="View Details">
    </form>
    <%
        String error = (String) request.getAttribute("error");
        if (error != null) {
            out.println("<p style='color:red'>" + error + "</p>");
        } else {
            Account account = (Account) request.getAttribute("account");
            if (account != null) {
                out.println("<table>");
                out.println("<tr><th>Field</th><th>Value</th></tr>");
                out.println("<tr><td>Full Name</td><td>" + account.getFullName() + "</td></tr>");
                out.println("<tr><td>Address</td><td>" + account.getAddress() + "</td></tr>");
                out.println("<tr><td>Mobile No</td><td>" + account.getMobileNo() + "</td></tr>");
                out.println("<tr><td>Email ID</td><td>" + account.getEmailId() + "</td></tr>");
                out.println("<tr><td>Account Type</td><td>" + account.getAccountType() + "</td></tr>");
                out.println("<tr><td>Initial Balance</td><td>" + account.getInitialBalance() + "</td></tr>");
                out.println("<tr><td>Date of Birth</td><td>" + account.getDateOfBirth() + "</td></tr>");
                out.println("<tr><td>ID Proof</td><td>" + account.getIdProof() + "</td></tr>");
                out.println("</table>");
            }
        }
    %>
</body>
</html>
