//Backwards.java
//solution to page 190 #9

import java.util.Scanner;

public class Backwards
{
    /** displays an integer in reverse order
        Pre-condidtions: n valid integer >0
	post-conditions: n is dispalyed in reverse order
	@param n -- integer to be reversed
	Responses to abnormal behavior:
    
	test cases
	goal           input  expected output
	valid          12345   54321
        invalid          -12345  ?
        invalid        0         ?
	boundry       12340     04321
        boundry        1         1
        valid         10100     00101
    */
    public static void back (int n)
    {
       	//System.out.println("entering back, n = " + n);
	if (n==0)
	    return;
	else {
	    System.out.print(n%10);
	    back(n/10);
	}
    }

    public static void main(String[] args)
    {
	int value;
	Scanner valueIn = new Scanner(System.in);

	System.out.print("enter the positive value to reverse: ");
	value = valueIn.nextInt();

	back(value);
	System.out.println();
    }
}
