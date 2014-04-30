import java.util.Scanner;
public class Account  {
  Scanner in = new Scanner (System.in);
  private  String accountNumber = "";
  public int transAmount = 0;
  private Transaction [] transactions;
  public double accountBalance = 0;
  
  public Account(String actNum, double bal) {
    accountNumber = actNum;
    accountBalance = bal;
    transactions = new Transaction [10];
    transAmount++;    
  }// end Account
 
  public void addTransaction(String actNum, String transType, double amount){
    transactions[transAmount] = new Transaction (actNum, transType, amount);
    transAmount++;  
  }// end transactionAmount
  
  public void deposit (double amount){

  }// end deposit
  
  public void withdraw(int value, double amount){ 

  }// end withdraw
  
  public void addInterest(Customer cust, String actNum, String actType) {
    double rate = 0;
    double bal = 0;
    System.out.println("How many months do you want to compound?");
    double compound = in.nextDouble();    
    
    if (actType.compareTo("savings") == 0  || actType.compareTo("Savings") == 0) 
      rate = cust.getSavingInterest();
    
    if (actType.compareTo("checking") == 0  || actType.compareTo("Checking") == 0) 
      rate = cust.getCheckingInterest();
    double answer = Math.pow(bal * (1 + rate), compound);
    
    addTransaction( actNum, "Interest",  answer);
  }// end addInterest
  
  public String getAccountNumber(){
    return accountNumber; 
  }// end getter: getAccountNumber
  
  public double getBalance() {
    // get balance of the account
    return accountBalance;
  }// end getter: getBalance
  
  public String toString(){ 
    return "Account Number: " + accountNumber + "\n Account Balance: " + accountBalance + ".";
  }// end toString
  
  public void displayTrans(){
    for(int i = 0; i < transAmount; i ++ ) {
      System.out.println(transactions[i]);
      System.out.println();
    }// end for
  }// end displayTrans
}// end Account