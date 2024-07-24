package com.bankingapp.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bankingapp.DAO.ViewAccountDAO;
import com.bankingapp.model.Account;


@WebServlet("/viewAccount")
public class ViewAccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountNumber = request.getParameter("accountNumber");
        if (accountNumber != null && !accountNumber.trim().isEmpty()) {
            ViewAccountDAO dao = new ViewAccountDAO();
            Account account = dao.getAccountDetails(accountNumber);
            request.setAttribute("account", account);
        } else {
            request.setAttribute("error", "Account number is required.");
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("viewAccount.jsp");
        dispatcher.forward(request, response);
    }
}
