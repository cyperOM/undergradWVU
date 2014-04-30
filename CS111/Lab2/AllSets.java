//AllSets.java
//a cs111 sample program-- use the set class created

import java.util.Scanner;

public class AllSets
{
    public static void main(String [] args)
    {
	Sets<Integer> s1 = new Sets<Integer>();
	Sets <Integer>s2 = new Sets<Integer>(); 
	Sets<Integer> us = new Sets<Integer>();
	Sets<Integer> is = new Sets<Integer>();
	Sets<Integer> ds = new Sets<Integer>();
	Scanner sc = new Scanner(System.in);
	boolean success;

	int[]  x = new int [5];
	int [] y = new int[5];

	int i;

	for (i=0;i<5;i++)
	    {System.out.print("enter an element to be in the 1st set: ");
	     x[i]= sc.nextInt();
	     System.out.print("enter an element to be in the 2nd set: ");
	     y[i] = sc.nextInt();
	    }

	for (i=0;i<5;i++)
	    { s1.insert(x[i]);
	      s2.insert(y[i]);
	    }
	System.out.println("the elements in s1 are: ");
	s1.displayContents();
	System.out.println ("\nthe elements in s2 are: ");
	s2.displayContents();

	us = s1.unions(s2);
	System.out.println("the elements in the union are: ");
	us.displayContents();

	is = s1.intersection(s2);
	System.out.println("the elements in the intersection are: ");
	is.displayContents();

	ds = s1.difference(s2);
	System.out.println("the elements in the difference are: ");
	ds.displayContents();

    }
}

  
