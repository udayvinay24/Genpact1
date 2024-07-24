import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DeleteAccountServlet")
public class DeleteAccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DeleteAccountDAO deleteAccountDAO;

    public void init() {
        deleteAccountDAO = new DeleteAccountDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountNumber = request.getParameter("accountNumber");

        try {
            // Delete customer from the database
            boolean isDeleted = deleteAccountDAO.deleteCustomer(accountNumber);

            if (isDeleted) {
                // Redirect to admin dashboard on successful deletion
                response.sendRedirect("adminDashboard.jsp");
            } else {
                // Set error message and forward to delete page if deletion fails
                request.setAttribute("error", "Unable to delete customer account.");
                request.getRequestDispatcher("deleteAccount.jsp").forward(request, response);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            request.setAttribute("error", "Database connection error.");
            request.getRequestDispatcher("deleteAccount.jsp").forward(request, response);
        }
    }
}
