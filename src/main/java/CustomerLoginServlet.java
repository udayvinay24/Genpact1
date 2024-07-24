import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/CustomerLoginServlet")
public class CustomerLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	doPost(req, resp);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountNumber = request.getParameter("accountNumber");
        String password = request.getParameter("password");
        
        CustomerLoginDAO customerLoginDAO = new CustomerLoginDAO();
        boolean isValid = customerLoginDAO.validateCustomer(accountNumber, password);
        
        if (isValid) {
            HttpSession session = request.getSession();
            session.setAttribute("accountNumber", accountNumber);
            response.sendRedirect("customerDashboard.jsp"); // Redirect to home page after successful login
        } else {
            request.setAttribute("errorMessage", "Invalid account number or password");
            request.getRequestDispatcher("customerLogin.jsp").forward(request, response);
        }
    }
}
