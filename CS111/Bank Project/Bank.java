import java.util.Scanner;

public class Bank {
  
  public Customer [] customers;
  private int numCustomers;
  Scanner scan = new Scanner(System.in);
  double bal = 0;
  String actNum = "";
  int personType;
  
  public Bank(){
    customers = new Customer[10];
    numCustomers=0;
  } // end bank
  
  public void addAccount() { 
    
    System.out.println("Please enter a number that you can remember.");
    System.out.println("This will be your customer number.");
    String custNum = scan.next();
    
    System.out.println("What account number would you like?");
    String actNum = scan.next();
    
    System.out.println("What is your first name?");
    String fname = scan.next();
    
    System.out.println("Last name:");
    String lname = scan.next();
    
    System.out.println("Age:");
    int ag = scan.nextInt();
    
    System.out.println("Address:");
    String ad = scan.next();
    
    System.out.println("Telephone number:");
    String tn = scan.next();
    
    System.out.println("What option best describes you: student, senior, or adult?");
    String choice = scan.next();  
    String aType = "";
    int pType;
    double bal = 0;
    char c = choice.charAt(0);
    char h = choice.charAt(1);   
    if (c == 'a' || c == 'A'){   
      pType = 1;
      setPersonType(pType);
      customers[numCustomers]= new Adult(fname, lname, ad, tn, custNum, ag, aType,  actNum,  bal);
      System.out.println("You have created an adult account.");
    }//end if  
    
    else {
      pType = 2;
      setPersonType(pType);
      if (c == 's' || c == 'S'){
        if (h == 'e' || h == 'E') { 
          customers[numCustomers] = new Senior(fname, lname, ad, tn, custNum, ag, aType,  actNum,  bal);
          System.out.println("You have created a senior account.");
        }// end inner if
        
        else {
      pType = 3;
      setPersonType(pType);
          customers[numCustomers] = new Student(fname, lname, ad, tn, custNum, ag, aType,  actNum,  bal);
          System.out.println("You have created a student account.");
        }// end else
      }// end outter if
    }// end else  
    
    System.out.println("What type of account would you like to open: Savings or Checking");
    String accountChoice = scan.next();
    char a = accountChoice.charAt(0);  
    if ( a == 'c' || a == 'C') { 
      aType = "Checking";
      System.out.println("You have opened a checking account."); 
      System.out.println("You must make a deposit: Please enter amount.");
      bal = scan.nextDouble();
      customers[numCustomers].addAccount(aType, actNum, bal);      
    }// end if    
    
    else {
      aType = "Savings";
      System.out.println("You have opened a savings account.");
      System.out.println("You must make a deposit: Please enter amount.");
      bal = scan.nextDouble();
      customers[numCustomers].addAccount(aType, actNum, bal);       
    }// end else
    numCustomers++;  
  }// end addAcount
  
  public  void addAccount(String custNum){
    System.out.println("What type of account would you like to add?"); 
    System.out.print( "Savings or checking?");
    String aType = scan.next();
    System.out.println("What account number would you like?");
    actNum = scan.next(); 
    char f = aType.charAt(0);
    
    if ( f == 's' || f == 'S'){
      aType = "Savings";  
      for (int i = 0; i < numCustomers; i++){
        if ( customers[i].getCustomerNumber().equals(custNum) == true){
          int j = i;
          System.out.println("You have added a savings account.");
          System.out.println("You must make a deposit: Please enter amount.");
          bal = scan.nextDouble();
          customers[j].addAccount(aType, actNum, bal);        
        }// end if
      }// end for    
    }// end if
    
    else if (f == 'c' || f == 'C'){
      aType = "Checking";
      for (int i = 0; i < numCustomers; i++){
        if ( customers[i].getCustomerNumber().equals(custNum) == true){
          int j = i;
          System.out.println("You have added a checking account.");
          System.out.println("You must make a deposit: Please enter amount.");
          double bal = scan.nextDouble();
          customers[j].addAccount(aType, actNum, bal);
        }// end if
      }// end for      
    }// end else if 
  }// end addAccount
  
  public void addInterest(String actNum, String custNum, String actType){
    for(int i = 0; i < numCustomers; i++){
      if(customers[i].getCustomerNumber().equals(custNum) == true){
        Customer cust1 = customers[i];
        cust1.getAccount(actNum).addInterest(cust1, actNum, actType);
      }// end if
    }// end for
  }// end addInterest
     
  public void setPersonType(int pType){
    personType = pType;
  }// end setPersonType
  
  public int getPersonType(){
    return personType;
  }// end getPersonType
  
  public void displayAccounts() {
    for (int i = 0; i < numCustomers; i++) {
      System.out.println(customers[i]);
      System.out.println();
    }//end for
  }// end displayAccounts
  
  public void displayAccounts(String custNum) {
    for (int i = 0; i < numCustomers; i++) { 
      if(customers[i].getCustomerNumber().equals(custNum) == true){
        int j = i;
        customers[i].displayAccounts();
      }// end if
    } // end for 
  } // end displayAccount
  
  public void displayTrans(String actNum, String custNum) {
    for(int i = 0; i < numCustomers; i++){
      if(customers[i].getCustomerNumber().equals(custNum) == true){
        int j = i;
        customers[j].getAccount(actNum).displayTrans();       
      }// end if
    }// end for
  }// end displayTrans
  
  public Account getAccount(String actNum, String custNum) {
    Account temp;
    int j;
    for (int i = 0; i < numCustomers ; i++) {
      if (customers[i].getCustomerNumber().equals(custNum) == true){
        j=i;
        temp = customers[j].getAccount(actNum);
        return temp;
      }// end if
    }// end for 
    return null;
  }// end getAccount
  
  public double getBalance(String actNum, String custNum){
    for ( int i = 0; i < numCustomers; i++){
      if (customers[i].getCustomerNumber().equals(custNum) == true){
        int j = i;
        bal = customers[j].getAccount(actNum).getBalance();
        return bal;
      }// end for
    }// end if
    return bal;
  }// end getBalance
  
  public void makeWithdraw(double amount, String actNum, String custNum){
    Account tempW = getAccount(actNum, custNum);
    int value = getPersonType();
    tempW.withdraw(value, amount);
    tempW.getBalance();
  }// end makeWithdraw
  
  public void makeDeposit( String actNum, String custNum, double amount){
    Account temp = getAccount(actNum, custNum);
    temp.deposit(amount);
    temp.getBalance();   
  }// end make deposit
} // end bank class


  