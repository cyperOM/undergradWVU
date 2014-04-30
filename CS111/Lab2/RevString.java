//RevString.java
//CS 111 sample program to recursively display a string reveresed 

import java.util.Scanner;
public class RevString{
    public static void reverse (String s)
    {
	/**String reversal done recursively
	 * pre: s is a valid string, size >= 0
	 * post: s is displayed in reverse order
	 * responses error termination
	 * @param s the string to be reversed
	 */
	System.out.println("in reverse, string is: " + s + " size is: " + s.length());
	if (s.length() == 0)
	    return;
	else {
	    reverse (s.substring(1));
	    System.out.print(s.substring(0,1));
	}
    }

    public static void main(String[] args){
	String testIt;
	Scanner stringIn = new Scanner(System.in);
   
	System.out.println("enter a string:");
	testIt = stringIn.next();
	System.out.println("the string in reverse is: ");
	reverse(testIt);
	System.out.println();
	stringIn.close();
    }
}
   
