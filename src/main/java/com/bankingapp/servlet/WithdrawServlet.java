package com.bankingapp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bankingapp.DAO.WithdrawDAO;

@WebServlet("/withdraw")
public class WithdrawServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String accountNumber = (String) session.getAttribute("accountNumber"); // Fetch account number from session
        String withdrawAmountStr = request.getParameter("withdrawAmount");
        double withdrawAmount = Double.parseDouble(withdrawAmountStr);
        
        WithdrawDAO dao = new WithdrawDAO();
        String message = dao.withdrawAmount(accountNumber, withdrawAmount);
        
        request.setAttribute("message", message);
        request.getRequestDispatcher("withdraw.jsp").forward(request, response);
    }
}
