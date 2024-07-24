package com.bankingapp.DAO;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DepositDAO {

    public String depositAmount(String accountNumber, double depositAmount) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String message = "Deposit successful.";
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/new", "root", "Uday@242424");
            
            // Check if the account exists and get the current balance
            String checkQuery = "SELECT initial_balance FROM customerdetails WHERE account_number = ?";
            ps = conn.prepareStatement(checkQuery);
            ps.setString(1, accountNumber);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                // Update the balance
                String updateQuery = "UPDATE customerdetails SET initial_balance = initial_balance + ? WHERE account_number = ?";
                ps = conn.prepareStatement(updateQuery);
                ps.setDouble(1, depositAmount);
                ps.setString(2, accountNumber);
                ps.executeUpdate();
            } else {
                message = "Account not found.";
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "Error: " + e.getMessage();
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (ps != null) try { ps.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (conn != null) try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
        
        return message;
    }
}
