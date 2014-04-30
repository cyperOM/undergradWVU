//UnOrderedList.java
//cs111 sample API  usage of list for an
//unordered list, using an iterator



import java.util.LinkedList;
import java.util.Iterator;
import java.util.Scanner;

public class UnOrderedList
{

    public static void displayList(LinkedList<String> l)
    {
	//declare an iterator for the list
	Iterator<String> itr;
	
	//declare a new list using the copy constructor
	//LinkedList<String> l2= new LinkedList<String>(l);

	//initialize iterator to first element in list
	itr = l.listIterator();
	//loop until reach end
	while (itr.hasNext())
	    { //get the next item of the list
		String item = itr.next();
		System.out.println(item);
	    }
    }

    public static void main(String [] args)
    {
	LinkedList <String> l = new LinkedList<String>();
	Scanner sc = new Scanner(System.in);
	String name;

	System.out.println ("enter a name, CRTL D to end ");
	while (sc.hasNext())
	    { name = sc.next();
	    //insert at the end of the list
		l.addLast(name);
		System.out.println("enter a name, CTRL D to end ");
	    }

	System.out.println("the list contains: ");
	displayList(l);

    }
}
