//Postfix.java
//cs 111 sample program using the stack array implementation of the
//stack facility to evaluate postfix expressions


import java.util.Scanner;

public class PostfixA
{
    public static void main(String [] args) throws StackArray.FullStackException
    {
	StackArray<Integer> postfix = new StackArray<Integer>();
	char s;
	int right, left, result=0;
	Scanner sc = new Scanner (System.in);
	String ws;
	
	System.out.println("enter postfix expression");
	ws = sc.next();
	System.out.println("the expression is: " + ws);
	for (int i=0; i < ws.length();i++)
	    {s = ws.charAt(i);
	    if (s >= '0' && s <='9')
		postfix.push((int) (s) - 48);
	    else
		{right = postfix.pop();
		left = postfix.pop();
		switch (s)
		    {case '+':
			 result = left + right;
			 break;
		    case '-':
			result = left - right;
			break;
		    case '/':
			result = left /right;
			break;
		    case '*':
			result = left * right;
			break;
		    }

		postfix.push(result);
		}
	    }
	result = postfix.pop();
	System.out.println("the result of the expression is: " + result);
    }
}
