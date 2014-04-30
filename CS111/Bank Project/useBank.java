// Samantha Brooks
// Cindy Tanner and Corey Hinkle
// MWF Lecture 11:00 - 11:50
// Thursday 3:00 - 4:50 Lab

import java.util.Scanner;

public class useBank {
  
  static  Scanner scan = new Scanner (System.in);
  public static int menu ()
  { int choice;
    System.out.println("Open an Account (new customers)                    1");
    System.out.println("Make an Additional Account (existing customers)    2");
    System.out.println("Make a Withdrawl                                   3");
    System.out.println("Make a Deposit                                     4");
    System.out.println("Get Account Information                            5");
    System.out.println("Get Transactions                                   6");
    System.out.println("Exit                                               7");
    choice = scan.nextInt();
    scan.nextLine();
    return choice;
  }// end menu
  
  public  static void main (String[] args){  
    Bank theBank = new Bank();  
    
    //  Transaction theTrans = new Transaction();  
    int choice;
    choice = menu();
    while(choice != 7 ) {
      switch (choice) { 
        
        case 1: 
          theBank.addAccount();
          System.out.println("");
          break;
          
        case 2: 
          System.out.println("To open another account we need your customer number");
          String custNum = scan.next();
          theBank.addAccount(custNum);
          theBank.displayAccounts(custNum);
          System.out.println("");
          break;
          
        case 3: 
          System.out.println("What is your customer number");
          custNum = scan.next();
          System.out.println("What is your account number");
          String actNum = scan.next();
          System.out.println("How much would you like to withdraw");
          double bal = scan.nextDouble();
          theBank.makeWithdraw( bal, actNum, custNum); 
  
          System.out.println("Your current balance is : " + theBank.getBalance(actNum,custNum));
          System.out.println("");
          break;
          
        case 4: 
          System.out.println("What is your customer number");
          custNum = scan.next();
          System.out.println("What is your account number");
          actNum = scan.next();
          System.out.println("How much would you like to deposit");
          double amount = scan.nextDouble();
          theBank.makeDeposit(actNum, custNum, amount); 
          System.out.println("Your current balance is : " + theBank.getBalance(actNum,custNum));
          System.out.println("");
          
          break; 
          
        case 5: 
          System.out.println("Please enter your customer to see all your accounts.");
          custNum = scan.next();
          theBank.displayAccounts(custNum);
          break;  
          
        case 6:
          System.out.println("What is your customer number");
          custNum = scan.next();
          System.out.println("What is your account number");
          actNum = scan.next();
          theBank.displayTrans(actNum, custNum);
          break;
          
        case 7: 
          break;
        default: System.out.println("Sorry that is not a valid option.");
      } // end case 
      choice = menu();
    } // end while
  } // end main  
} // end useBank




