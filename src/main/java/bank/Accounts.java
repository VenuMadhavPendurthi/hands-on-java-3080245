package bank;

import bank.*;
import bank.exceptions.AmountException;

public class Accounts {
  private int Id;
  private String type;
  private double balance;

  public Accounts(int Id, String type, double balance) {
    setId(Id);
    setType(type);
    setBalance(balance);

  }

  public int getId() {
    return this.Id;
  }

  public void setId(int Id) {
    this.Id = Id;
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public double getBalance() {
    return this.balance;
  }

  public void setBalance(double balance) {
    this.balance = balance;

  }

  public void deposite(double amount) throws AmountException {
    if (amount < 1) {
      throw new AmountException("The minimum deposit is 1.00");
    } else {
      double newBalance = balance + amount;
      setBalance(newBalance);
    }

  }

  public void withdraw(double amount) {
    double newBalance = balance - amount;
    setBalance(newBalance);


  }
}
