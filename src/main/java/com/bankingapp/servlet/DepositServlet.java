package com.bankingapp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bankingapp.DAO.DepositDAO;

@WebServlet("/deposit")
public class DepositServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String accountNumber = (String) session.getAttribute("accountNumber"); // Fetch account number from session
        
        if (accountNumber == null || accountNumber.isEmpty()) {
            request.setAttribute("message", "No account number found in session.");
            request.getRequestDispatcher("deposit.jsp").forward(request, response);
            return;
        }
        
        String depositAmountStr = request.getParameter("depositAmount");
        
        try {
            double depositAmount = Double.parseDouble(depositAmountStr);
            
            DepositDAO dao = new DepositDAO();
            String message = dao.depositAmount(accountNumber, depositAmount);
            
            request.setAttribute("message", message);
        } catch (NumberFormatException e) {
            request.setAttribute("message", "Invalid deposit amount.");
        } catch (Exception e) {
            request.setAttribute("message", "Error: " + e.getMessage());
            e.printStackTrace(); // Log the stack trace for debugging
        }
        
        request.getRequestDispatcher("deposit.jsp").forward(request, response);
    }
}
