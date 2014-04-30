//Fib.java
//CS111 sample program 
//recursive fibonacci series

import java.util.Scanner;

public class Fib {
  
    public static int fib (int n){
	//System.out.println("entering fib, n is: " + n);
	if (n <= 2)
	    return 1;
	else {
	    return (fib(n-1) + fib (n-2));
	}
    }
  
    public static void main(String [] args){
	int fibNum;
	Scanner numIn = new Scanner(System.in);
    
	System.out.print ("enter the fibonacci term you wish to find: ");
	fibNum = numIn.nextInt();
	System.out.println(fib(fibNum) + " is the " + fibNum + "th term in the series");
    }
}
      
