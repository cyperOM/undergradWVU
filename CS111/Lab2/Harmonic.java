//Harmonic.java
//solution to page 190 #7

import java.util.Scanner;

public class Harmonic
{
    /** displays the nth harmonic number
        Pre-condidtions: n valid integer >0
	post-conditions: 1 + 1/2 + 1/3 + 1/4 +...1/n
	@param n -- the harmonic value to be calculated
	@returns 1+ 1/2+1/3+1/4+...+1/n
	Responses to abnormal behavior: error terminate
    
	test cases
	goal           input  expected output
	valid          2         1.5
        invalid        -1        ?
        invalid        0         ?
	boundry        1         1
        valid          5
    */
    public static double harmonic (int n)
    {
	//System.out.println("entering harmonic, n = " + n);
	if (n==1)
	    return 1;
	else return 1.0/n + harmonic(n-1);
    }

    public static void main(String[] args)
    {
	int value;
	Scanner valueIn = new Scanner(System.in);

	System.out.print("Find the ? harmonic number : ");
	value = valueIn.nextInt();

	System.out.println
	    ("the " + value + " harmonic number is : " + harmonic(value));
    }
}
