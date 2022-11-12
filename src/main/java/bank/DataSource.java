package bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataSource {
  /**
   * @return
   */
  public static Connection connect() {
    String db_file = "jdbc:sqlite:resources/bank.db";
    Connection connection = null;
    try {
      connection = DriverManager.getConnection(db_file);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return connection;
  }

  public static Customer getCustomer(String username) {
    String sql = "select * from customers where username = ?";
    Customer customer = null;

    try (Connection connection = connect();
        PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setString(1, username);
      try (ResultSet resultSet = statement.executeQuery()) {
        customer = new Customer(
            resultSet.getInt("id"),
            resultSet.getString("name"),
            resultSet.getString("username"),
            resultSet.getString("password"),
            resultSet.getInt("account_Id"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return customer;
  }

  public static Accounts getAccount(int accountId) {
    String sql = "Select * from Accounts where ID = ?";
    Accounts accounts = null;
    try (Connection connection = connect();
        PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setInt(1, accountId);
      try (ResultSet resultSet = statement.executeQuery()) {
        accounts = new Accounts(
            resultSet.getInt("ID"),
            resultSet.getString("TYPE"),
            resultSet.getInt("BALANCE"));
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return accounts;
  }

  public static void updateAccountBalance(int accountId,double balance) throws SQLException
  {
    String sql = "update accounts set balance = ? where id = ?";
    try(
      Connection connection = connect();
      PreparedStatement statement = connection.prepareStatement(sql);)
      {
        statement.setDouble(1, balance);
        statement.setInt(2,accountId);

        statement.executeUpdate();
      }

    
  }
}
