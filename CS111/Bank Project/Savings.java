public class Savings extends Account {
  
  public Savings (String actNum, double bal){
    super(actNum, bal);
  } // end Customer
  
  public void deposit( double amount){
    String actNum = getAccountNumber();
    accountBalance = accountBalance + amount;  
    addTransaction(actNum, "Deposit (Savings)",  amount);
  }// end deposit
  
  public void withdraw(int value, double amount) {
    String actNum = getAccountNumber();
    accountBalance = accountBalance - amount;
    
    if(accountBalance < 0){ 
        { if (value == 3){ 
          System.out.println("You're a student and you've overdrawn your account.");
        }// end if
          else if (value == 2){
            System.out.println("You're senior and you've overdrawn your account.");
        }// end else if
          else{
            System.out.println("You're an adult and you've ovedrawn your account.");
          }// end else
        }// end if
    }// end outter if
   
    addTransaction(actNum, "Withdraw (Savings)",  amount);
  }// end withdraw
}// end Savings