public abstract class Customer{
  
  private int age;
  private  String firstName;
  private  String lastName;
  private String address;
  private String telephoneNumber;
  private  String customerNumber;
  public String accountType; 
  public Account [] accounts;
  private int numAccounts;
  private double balance;
  private String accountNumber;
  
  public Customer() { 
    firstName = "Bob";
    lastName= "Tucker";
    address = "Lincoln Drive";
    telephoneNumber = " 1-800-999-3333";
    customerNumber = "0001";
    age = 0;
    accounts = new Account[10];
    numAccounts=0;
    accountNumber = "0";
    accountType = "";
    balance = 0;
    
    
  } // end Null Customer
  
  public Customer(String fname, String lname, String ad, String tn, String custNum, int ag, String aType, String actNum, double bal){
    firstName = fname;
    lastName = lname;
    address = ad;
    telephoneNumber = tn;
    customerNumber = custNum;
    age = ag;
    accountType = aType;
    accountNumber = actNum;
    balance = bal;
    accounts = new Account [10];
    numAccounts = 0;
  } // end Customer
  
  //Getters
  public  String getName() {
    return firstName + " " + lastName;
  }// end getter: getName
  
  public  String getAddress() {
    return address;
  }// end getter: getAdress
  
  public String getTelephoneNumber() {
    return telephoneNumber; 
  }// end getter: getTelephoneNumber
  
  public  String getCustomerNumber() {
    return customerNumber;
  }// end getter: getCustomerNumber
  
  public int getAge() {
    return age;
  }// end getter: getAge
  
  
  public  Account getAccount(String actNum){
    for (int i = 0; i < numAccounts ; i++) {
      if (accounts[i].getAccountNumber().equals(actNum) == true){
        return accounts[i];  
      }// end if
    }// end for
    return null;  
  }// end getter: getAccount
  
  
  public void addAccount(String aType, String actNum, Double bal) {
    if (aType.charAt(0) == 'c' || aType.charAt(0) == 'C') {
      accounts[numAccounts] = new Checking(actNum, bal);
      setAccountType("Checking"); 
    }// end if    
    if (aType.charAt(0) == 's' || aType.charAt(0) == 'S') {
      accounts[numAccounts] = new Savings(actNum, bal);
      setAccountType("Savings");    
    }// end if   
    numAccounts++; 
  }// end addAccount
  
  // Setters
  
  public  void setFirst ( String fn ) {
    firstName = fn;
  }// end setter: setFirst
  
  public  void setLast ( String ln ) {
    lastName = ln;
  }// end setter: setLast
  
  public  void setAddress ( String ad ) {
    address =ad;
  }// end setter: setAddress
  
  public  void setTelephoneNumber ( String tn ){
    telephoneNumber = tn;
  }// end setter: setTelephoneNumber
  
  public void setCustomerNumber ( String cn ) {
    customerNumber = cn;
  }// end setter: setCustomerNumber
  
  public void setAge ( int a )  {
    age = a;
  }// end setter: setAge
  
  public void displayAccounts(){
    for(int i = 0; i < numAccounts; i++) {
      System.out.println(accounts[i]);
    }// end for
  }// end displayAccounts
  
  public void setAccountType (String aType){
    accountType = aType; 
  }// end setter: setAccountType
  
  public String toString() { 
    return " First Name: " + firstName  + "\n Last Name: " + lastName +"\n Age: " + age + "\n Address: " + address + 
      "\n Telephone Number: " + telephoneNumber + "\n Customer Number: " + customerNumber + "\n Account Type: " + 
      accountType + "\n Balance: " + balance + "\n Account Number: " + accountNumber; 
  }// end toString
  
  abstract double getSavingInterest();
  abstract double getCheckingInterest();
  abstract double getCheckCharge();
  abstract double getOverdraftPenality();
  
}// end customer



