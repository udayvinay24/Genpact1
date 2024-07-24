import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/FetchCustomerServlet")
public class FetchCustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private EditAccountDAO editAccountDAO;

    public void init() {
        editAccountDAO = new EditAccountDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountNumber = request.getParameter("accountNumber");

        try {
            Customer customer = editAccountDAO.getCustomerByAccountNumber(accountNumber);

            if (customer != null) {
                request.setAttribute("customer", customer);
                request.getRequestDispatcher("editAccount.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "Customer not found.");
                request.getRequestDispatcher("editAccount.jsp").forward(request, response);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            request.setAttribute("error", "Database connection error.");
            request.getRequestDispatcher("editAccount.jsp").forward(request, response);
        }
    }
}
