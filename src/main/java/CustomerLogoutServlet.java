import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/CustomerLogoutServlet")
public class CustomerLogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Invalidate the session to log out the customer
        HttpSession session = request.getSession(false); // Use false to avoid creating a new session if it does not exist
        if (session != null) {
            session.invalidate();
        }
        
        // Redirect to the customer login page with a logout success message
        response.sendRedirect("customerLogin.jsp?logout=success");
    }
}

