//OrderedList.java
//cs111 sample program demonstrating an ordered list

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class OrderedList
{
    public static void displayList(LinkedList<String> l)
    {
	//declare an iterator for the list
	ListIterator<String> itr;
	
	//set the iterator at the beginning of the list
	itr = l.listIterator();
	while (itr.hasNext())
	    System.out.println(itr.next());
    }

    public static void insertIt( LinkedList<String> l, String name)
    {
	ListIterator <String> itr;
	int index=0;

	//start a the begining of the list
	itr = l.listIterator();
	//loop thorugh the list until find the position for insertion
	while (itr.hasNext() && itr.next().compareTo(name)<0) 
	    {   //determine the position for instertion
		index = itr.nextIndex();
	    }
	//insert the new name at that position
	l.add(index,name);
    }

    public static void main(String [] args)
    {
	LinkedList <String> l = new LinkedList<String>();
	String name;
	Scanner sc = new Scanner(System.in);

	System.out.println("enter a name, -1 to end ");
	name=sc.next();
	while (!name.equals("-1"))
	    {	insertIt(l,name);
		System.out.println ("enter a name,-1 to end ");
		 name = sc.next();
	    }
  
	System.out.println ("\nenter a value to search the list for: ");
	name = sc.next();
  
	if (l.contains(name))
	    System.out.println (name + " is in the list");
	else insertIt(l,name);
  

	System.out.println("the list contains: ");
	displayList(l);
    }
}
