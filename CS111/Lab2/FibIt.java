//FibIt.java
//CS111 sample program 
//iterative fibonacci series

import java.util.Scanner;

public class FibIt {
  
  
    public static void main(String [] args){
	int fibTerm, fibNum = 0, fibNum1,fibNum2, nextTerm;
	Scanner numIn = new Scanner(System.in);
    
	System.out.print ("enter the fibonacci term you wish to find: ");
	fibTerm = numIn.nextInt();
	nextTerm = fibTerm;
	fibNum1=1;
	fibNum2 =1;
	while (nextTerm-2 > 0){
	    fibNum = fibNum1 + fibNum2;
	    fibNum1 = fibNum2;
	    fibNum2 = fibNum;
	    nextTerm--;
	}
	System.out.println(fibNum + " is the " + fibTerm + "th term in the series");
    }
}
