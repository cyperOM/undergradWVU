//Balance.java
//CS 111 sample program using collections class stack
//to check for  balanced parens

import java.util.Stack;
import java.util.Scanner;

public class Balance
{

    public static void main(String [] args)
    {
	int i;
	String s;
	Stack<Character> paren = new Stack<Character>();
	Scanner sc = new Scanner(System.in);

	System.out.println ("enter an expression: (CRTL D to end)");
	while (sc.hasNext())
	    {   
		s = sc.next();
		System.out.println("the expression entered: " +  s);
		for (i=0;i<s.length();i++)
		    {if (s.charAt(i)=='(')
			paren.push(s.charAt(i));
		    else
			{if (s.charAt(i)==')')
				// if (!paren.empty())
				paren.pop();
			    // else
			    //	System.out.println("parens out of balance");
			}
		    }
		//anything left on the stack?
		if (!paren.empty())
		    System.out.println("parens out of balance");
		//get ready for next string
		//empty out the stack
		//either pop until empty
		//while (!paren.empty())
		//  paren.pop();
		//or use inherited method clear()
		paren.clear();
		System.out.println ("enter an expression:(Ctrl D to end)");
	    }
	System.out.println("that's all folks");

    }
}
