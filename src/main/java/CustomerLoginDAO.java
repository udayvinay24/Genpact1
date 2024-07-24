import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CustomerLoginDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/new"; // Update with your database details
    private String jdbcUsername = "root"; // Update with your database username
    private String jdbcPassword = "Uday@242424"; // Update with your database password

    private static final String VALIDATE_QUERY = "SELECT * FROM customerdetails WHERE account_number = ? AND password = ?";

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public boolean validateCustomer(String accountNumber, String password) {
        boolean isValid = false;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(VALIDATE_QUERY)) {
            preparedStatement.setString(1, accountNumber);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                isValid = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isValid;
    }
}
