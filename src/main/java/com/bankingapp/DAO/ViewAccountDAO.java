package com.bankingapp.DAO;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bankingapp.model.Account;

public class ViewAccountDAO {

    public Account getAccountDetails(String accountNumber) {
        Account account = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/new", "root", "Uday@242424");
            
            String query = "SELECT * FROM customerdetails WHERE account_number = ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, accountNumber);
            
            rs = ps.executeQuery();
            
            if (rs.next()) {
                account = new Account();
                account.setFullName(rs.getString("full_name"));
                account.setAddress(rs.getString("address"));
                account.setMobileNo(rs.getString("mobile_no"));
                account.setEmailId(rs.getString("email_id"));
                account.setAccountType(rs.getString("account_type"));
                account.setInitialBalance(rs.getDouble("initial_balance"));
                account.setDateOfBirth(rs.getDate("date_of_birth"));
                account.setIdProof(rs.getString("id_proof"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (ps != null) try { ps.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (conn != null) try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
        
        return account;
    }
}
