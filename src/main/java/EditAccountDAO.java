import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EditAccountDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/new";
    private String jdbcUsername = "root";
    private String jdbcPassword = "Uday@242424";

    // SQL Queries
    private static final String SELECT_CUSTOMER_BY_ACCOUNT_NUMBER = "SELECT * FROM customerdetails WHERE account_number = ?";
    private static final String UPDATE_CUSTOMER_SQL = "UPDATE customerdetails SET full_name = ?, address = ?, mobile_no = ?, email = ?, account_type = ?, initial_balance = ?, dob = ?, id_proof = ? WHERE id = ?";

    public Customer getCustomerByAccountNumber(String accountNumber) throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CUSTOMER_BY_ACCOUNT_NUMBER)) {

            preparedStatement.setString(1, accountNumber);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new Customer(
                    resultSet.getInt("id"),
                    resultSet.getString("full_name"),
                    resultSet.getString("address"),
                    resultSet.getString("mobile_no"),
                    resultSet.getString("email"),
                    resultSet.getString("account_type"),
                    resultSet.getDouble("initial_balance"),
                    resultSet.getString("dob"),
                    resultSet.getString("id_proof")
                );
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return null;
    }

    public boolean updateCustomer(int customerId, String fullName, String address, String mobileNo, String email, String accountType, double initialBalance, String dob, String idProof) throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CUSTOMER_SQL)) {

            preparedStatement.setString(1, fullName);
            preparedStatement.setString(2, address);
            preparedStatement.setString(3, mobileNo);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, accountType);
            preparedStatement.setDouble(6, initialBalance);
            preparedStatement.setString(7, dob);
            preparedStatement.setString(8, idProof);
            preparedStatement.setInt(9, customerId);

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
