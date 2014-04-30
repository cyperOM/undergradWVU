//Gcd.java
//CS 111 sample program to illustrate calculating the greatest common divisor

/**public static int Gcd (int a, int b)
 * pre condition b<=a and a!=0 and b!=0
 * post condition Gcd = the largest number which divides both a&b evenly
 * @param a the larger value 
 * @param b the smaller value
 * @returns Gcd= largest i such that a%i=0 && b%i =0
 * responses erroneous results if b>a
 *           error terminate if a or b = 0
 */

/* test plan
   goal                input                       expected output
   valid               26, 8                        2    something > 1
   boundary            125, 25                      25   b
   valid               1024, 36                     4    something > 2
   boundary            36, 36                       36   a=b
   boundary            735, 16                      1    no common factors other than 1
   valid               24, -20                      4or -4   a or b < 0    
   invalid             8, 26                       erroneous   a<b
   invalid             4, 0                        b=0
   invalid             0,4                         a=0
*/

import java.util.Scanner;

public class Gcd
{

    public static int gcdr(int a, int b)
    { //precondition b<=a b!= 0

	if (a%b ==0)
	    return b;
	else
	    return gcdr(b, a%b);
    }

    public static int gcdit2 (int a, int b)
    { // precondition b <=a and b!=0
	int temp=b;
	while (a%b !=0)
	    {temp = a;
	    a = b;
	    b = temp%b;
	    }
	return b;
    }

    public static int gcdit (int a, int b)
    { //precondition b<=a and b!=0
	int i, ans=1;
	for (i=b;ans ==1 &&  i>=1; i--)
	    { if (a%i == 0 && b%i == 0)
		ans= i;
	    }
	return ans;
    }

    public static int gcdit3 (int a, int b)
    {//pre condition b<=a and b!=0
	int i,ans =1;
	for (i=2; i<=b; i++)
	    if (a%i ==0 && b%i == 0)
		ans = i;
	return ans;
    }

    public static void main (String []args)
    {
	int a,b;

	Scanner sc = new Scanner(System.in);
	System.out.print ("please enter the larger of the two number to the gcd");
	a = sc.nextInt();
	System.out.print("please enter the second (and smaller) number to find the gcd");
	b = sc.nextInt();
	//recursive call
	System.out.println("recursive module");
	System.out.println("the greatest Common divisor of " + a + "," + b + "is: " + gcdr(a,b));
	//iterative decreasing for loop call
	System.out.println("decreasing for loop module");
	System.out.println("the greatest Common divisor of " + a + "," + b + "is: " + gcdit(a,b));
	//iterative while loop call
	System.out.println("while loop module");
	System.out.println("the greatest Common divisor of " + a + "," + b + "is: " + gcdit2(a,b));
	//iterative increasing for loop call
	System.out.println("increasing for loop module");
	System.out.println("the greatest Common divisor of " + a + "," + b + "is: " + gcdit3(a,b));
			   
    }
}
