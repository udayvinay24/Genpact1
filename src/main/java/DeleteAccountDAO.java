import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteAccountDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/new";
    private String jdbcUsername = "root";
    private String jdbcPassword = "Uday@242424";
    private static final String DELETE_CUSTOMER_SQL = "DELETE FROM customerdetails WHERE account_number = ?";

    public boolean deleteCustomer(String accountNumber) throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CUSTOMER_SQL)) {

            // Set parameters
            preparedStatement.setString(1, accountNumber);

            // Execute deletion
            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            printSQLException(e);
            return false;
        }
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
