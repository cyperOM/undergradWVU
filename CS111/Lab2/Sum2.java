//Sum.java
//solution to page 165 #8

import java.util.Scanner;

public class Sum2{
    public static int sum (int n)
    {
	int n2;

	System.out.println("On this call n = " + n);
	if (n==1)
	    return 1;
	else 
	    {n2= (n + sum(n-1));
	     System.out.println("On return n = " + n + " n2 = " + n2);
	     return n2;
	    }
    }

    public static void main(String[] args){
	int value;
	Scanner valueIn = new Scanner(System.in);

	System.out.print("enter the value to sum to: ");
	value = valueIn.nextInt();

	System.out.println("the sum of the number 1-" + value + " is: " + sum(value));
    }
}
