import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/new";
    private String jdbcUsername = "root";
    private String jdbcPassword = "Uday@242424";
    private static final String INSERT_CUSTOMER_SQL = "INSERT INTO customerdetails (full_name, address, mobile_no, email, account_type, initial_balance, dob, id_proof, account_number, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public boolean registerCustomer(String fullName, String address, String mobileNo, String email, String accountType, double initialBalance, String dob, String idProof, String accountNumber, String password) throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CUSTOMER_SQL)) {
            preparedStatement.setString(1, fullName);
            preparedStatement.setString(2, address);
            preparedStatement.setString(3, mobileNo);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, accountType);
            preparedStatement.setDouble(6, initialBalance);
            preparedStatement.setString(7, dob);
            preparedStatement.setString(8, idProof);
            preparedStatement.setString(9, accountNumber);
            preparedStatement.setString(10, password);

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
