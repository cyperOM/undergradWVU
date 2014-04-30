//DisplayQueue.java
//CS 111 sample program to display the contents of a queue
//using the API QUeue interface

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class DisplayQueue
{

    public static void main(String [] args) throws NoSuchElementException
    {
	int a;
	LinkedList<Integer> aqueue = new LinkedList<Integer>();
	LinkedList<Integer> queue2 = new LinkedList<Integer>();
	Scanner sc = new Scanner(System.in);
	char yesno;
  
	
	do
	    { System.out.println("enter a value to be placed in the queue: ");
	      a = sc.nextInt();
	      aqueue.add(a);    //LinkedList method
	      System.out.println ("enter another value: (y/n)");
	      yesno = (char) sc.next().charAt(0);
	    } while (yesno == 'y' || yesno == 'Y');
	queue2 = (LinkedList<Integer>)aqueue.clone();
	System.out.println ("the elements in the queue are: ");
	while (!aqueue.isEmpty())   //collections method
	    { a=aqueue.remove();
	    System.out.println (a);
	    //queue2.add(a);
	    }
	//aqueue =(LinkedList<Integer>) queue2.clone();
	System.out.println("displaying queue 2");
	while (!queue2.isEmpty())   //collections method
	    { a=queue2.remove();
	    System.out.println (a);
	    //queue2.add(a);
	    }
	System.out.println("displaying aqueue ");
	while (!aqueue.isEmpty())   //collections method
	    { a=aqueue.remove();
	    System.out.println (a);
	    //    queue2.add(a);
	    }
	
	
	/*while (!queue2.isEmpty())
	  aqueue.add(queue2.remove());*/
    }
}
