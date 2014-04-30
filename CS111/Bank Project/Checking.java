public class Checking extends Account {
  
  public Checking (String actNum, double bal){
    super(actNum, bal);
  } // end Customer
  
  public void deposit(double amount){
    accountBalance = accountBalance + amount;  
    String actNum = getAccountNumber(); 
    addTransaction(actNum, "Deposit (Checking)",  amount);
  }// end deposit
  
  public void withdraw( int value, double amount) {
    accountBalance = accountBalance - amount;
    String actNum = getAccountNumber(); 
    addTransaction(actNum, "Withdraw (Checking)",  amount);
      if(accountBalance < 0) { 
        if (value == 3){ 
          System.out.println("You're a student and you've overdrawn your account.");
        }// end if
          else if (value == 2){
            System.out.println("You're senior and you've overdrawn your account.");
        }// end else if
          else{
            System.out.println("You're an adult and you've ovedrawn your account.");
          }// end else
        }// end if
  }// end withdraw
}// end Checking
