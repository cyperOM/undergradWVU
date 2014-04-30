import java.util.Date;
public class Transaction {
  
  private String accountNumber;
  private String transactionType;
  private double amount;
  Date date;
  
  public Transaction ( String actNum, String transType, double amt) { 
    accountNumber = actNum;
    transactionType = transType;
    amount = amt;
    date = new Date();
  }// end Transaction
  
  public String toString() {  
    return "Account Number: " + accountNumber + "\nTransaction Type: " + transactionType + 
      "\nAmount: " + amount + "\nDate: " + date;
  }// end toString  
}//end Transactions