import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Random;

@WebServlet("/RegisterCustomerServlet")
public class RegisterCustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CustomerDAO customerDAO;

    public void init() {
        customerDAO = new CustomerDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fullName = request.getParameter("fullName");
        String address = request.getParameter("address");
        String mobileNo = request.getParameter("mobileNo");
        String email = request.getParameter("email");
        String accountType = request.getParameter("accountType");
        double initialBalance = Double.parseDouble(request.getParameter("initialBalance"));
        String dob = request.getParameter("dob");
        String idProof = request.getParameter("idProof");

        // Generate account number and password
        String accountNumber = generateAccountNumber();
        String password = generatePassword();

        try {
            if (customerDAO.registerCustomer(fullName, address, mobileNo, email, accountType, initialBalance, dob, idProof, accountNumber, password)) {
                request.setAttribute("accountNumber", accountNumber);
                request.setAttribute("password", password);
                request.getRequestDispatcher("registrationSuccess.jsp").forward(request, response);
            } else {
                response.sendRedirect("registerCustomer.jsp?error=Could not register customer");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private String generateAccountNumber() {
        Random random = new Random();
        return String.format("AC%06d", random.nextInt(1000000));
    }

    private String generatePassword() {
        // Simple password generation logic (could be improved)
        Random random = new Random();
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            int choice = random.nextInt(3);
            if (choice == 0) {
                password.append((char) (random.nextInt(26) + 'a')); // lowercase letter
            } else if (choice == 1) {
                password.append((char) (random.nextInt(26) + 'A')); // uppercase letter
            } else {
                password.append(random.nextInt(10)); // digit
            }
        }
        return password.toString();
    }
}
