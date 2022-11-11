package bank;

public class Accounts
{
  private int Id;
  private String type;
  private double balance;

public Accounts(int Id,String type,double balance)
{
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
}
