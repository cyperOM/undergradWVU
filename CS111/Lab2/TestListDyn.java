//TestListDyn.java
//CS111 test rpogram for dynamic list class

public class TestListDyn 
{

    public static void main(String [] args)
    {
	ListDyn <Integer> l= new ListDyn<Integer>();

	System.out.println ("there are " + l.numElements() + " items in the list");
  
	for (int i =5; i>0; i--)
	    l.insert(i);
  
	System.out.println("the contents of the list are: ");
	l.display_list();
  
	ListDyn<Integer>l1 = new ListDyn<Integer>(l);

	System.out.println ("the contents of the second list are: ");
	l1.display_list();

	try
	    {
		System.out.println ("remove 1");
		l1.remove (1);
		System.out.println ("the list now contains");
		l1.display_list();
		System.out.println ("remove 20");
		l1.remove(20);
		System.out.println ("the list now contains");
		l1.display_list();
	    }
	catch (ListEmptyException e)
	    { System.out.println(e.getMessage() + " item not removed");}
	catch (NotInListException e)
	    { System.out.println(e.getMessage() + " item not removed");}
 
	System.out.println("there are " + l1.numElements() + " items in the second list");
    }
}

