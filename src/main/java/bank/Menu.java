package bank;

import java.util.Scanner;

import javax.security.auth.login.LoginException;

import bank.exceptions.AmountException;

public class Menu {
  private Scanner scanner;

  public static void main(String[] args) {
    System.out.println("Welcome to VPAS Bank");
    Menu menu = new Menu();
    menu.scanner = new Scanner(System.in);
    Customer customer = menu.authenticateUser();

    if (customer != null) {
      Accounts account = DataSource.getAccount(customer.getAccountId());
      menu.showMenu(customer, account);

    }

    menu.scanner.close();
  }

  private Customer authenticateUser() {
    System.out.println("Please enter your username: ");
    String username = scanner.next();
    System.out.println("Please enter your password: ");
    String password = scanner.next();
    Customer customer = null;
    try {
      customer = Authenticator.login(username, password);
    } catch (LoginException e) {
      System.out.println("There was an error: " + e.getMessage());
    }
    return customer;
  }

  private void showMenu(Customer customer, Accounts account) {
    int selection = 0;

    while (selection != 4 && customer.isAuthenticated()) {
      System.out.println("===================================================");
      System.out.println("Please select one of the following options: ");
      System.out.println("1 : Deposite");
      System.out.println("2 : Withdraw");
      System.out.println("3 : Check Balance");
      System.out.println("4 : Exit");
      System.out.println("===================================================");

      selection = scanner.nextInt();
      double amount;
      switch (selection) {
        case 1:
          System.out.println("How much would you like to deposite: ");
          amount = scanner.nextDouble();
          try
          {account.deposite(amount);
          }
          catch(AmountException e)
          {
            System.out.println(e.getMessage());
            System.out.println("Please try again");
          }
          break;

        case 2:
          System.out.println("How much would you like to withdraw: ");
          amount = scanner.nextDouble();
          account.withdraw(amount);
          break;

        case 3:
          System.out.println("Current Balance.... " + account.getBalance());
          break;

        case 4:
          Authenticator.logout(customer);
          System.out.println("ByeBye..Thanks for reaching VPASS Bank");
          break;

        default:
          System.out.println("Invalid option...Please try again");
          break;
      }
    }
  }
}
