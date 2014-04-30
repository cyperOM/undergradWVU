
//TestStackDyn.java
//CS 111 program to test the dynamic stack class



public class TestStackDyn
{

    public static void main(String [] args) throws StackEmptyException
    {

	StackDyn<Integer> s = new StackDyn<Integer>();

	System.out.println("there are " + s.numElements()+ " items on the stack");
	try
	    {System.out.println(s.top());}
	catch (StackEmptyException e)
	    { System.out.println(e.getMessage());}

  
	for (int i =0; i<5; i++)
	    s.push(i);

	System.out.println ("the contents of the first stack are: ");
	while (!s.empty())
	    { System.out.println(s.pop());}

	for (int i =0; i<5; i++)
	    s.push(i);


	StackDyn<Integer>s1 = new StackDyn <Integer>(s);

	System.out.println ("there are " + s1.numElements() + " items on the second stack");

	System.out.println ("the contents of the first stack are: ");
	while (!s.empty())
	    { System.out.println(s.pop());}

	System.out.println("the contents of the second stack is: ");
	while (!s1.empty())
	    {System.out.println(s1.pop());}
    }
}
  
