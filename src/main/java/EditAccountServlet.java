import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/EditAccountServlet")
public class EditAccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private EditAccountDAO editAccountDAO;

    public void init() {
        editAccountDAO = new EditAccountDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int customerId = Integer.parseInt(request.getParameter("customerId"));
        String fullName = request.getParameter("fullName");
        String address = request.getParameter("address");
        String mobileNo = request.getParameter("mobileNo");
        String email = request.getParameter("email");
        String accountType = request.getParameter("accountType");
        double initialBalance = Double.parseDouble(request.getParameter("initialBalance"));
        String dob = request.getParameter("dob");
        String idProof = request.getParameter("idProof");

        try {
            boolean isUpdated = editAccountDAO.updateCustomer(customerId, fullName, address, mobileNo, email, accountType, initialBalance, dob, idProof);
            if (isUpdated) {
                response.sendRedirect("editAccount.jsp?message=Account updated successfully");
            } else {
                request.setAttribute("error", "Failed to update customer details.");
                request.getRequestDispatcher("editAccount.jsp").forward(request, response);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            request.setAttribute("error", "Database error occurred.");
            request.getRequestDispatcher("editAccount.jsp").forward(request, response);
        }
    }
}
