//StackDyn.java
//cs111 sample implementation of a stack implemented using dynamic memory


public class StackDyn<T> 
{
      

    private static class Node<T>
    {
	private T data;
	private Node<T> next;
	
	private Node (T d)
	{
	    data =d;
	    next= null;
	}
	
	private Node (T d, Node<T> ref)
	{
	    data =d;
	    next = ref;
	}
    }

    
    private Node<T> top;
    private int numElements;


  public StackDyn()
    {
	top = null;
	numElements = 0;
    }



    public StackDyn (StackDyn<T> s) throws StackEmptyException
    {  Node<T> ptr, ptr2;
	if (s.top == null)
	    top = null;
	else
	    { top = new Node<T>(s.top.data);
	      ptr = top;
	      for (ptr2=s.top.next;ptr2 !=null; ptr2=ptr2.next)
		  {ptr.next = new Node<T>(ptr2.data);
		  ptr = ptr.next;
		  }
	    }
	numElements = s.numElements;
    }

    public boolean empty()
    {
	return (top == null);
    }


    public void push (T it) 
    {

	top = new Node<T> (it, top);
	numElements++;
    }


    public T pop() throws StackEmptyException
    { Node<T> ptr;
 
      if (empty())
	  throw  new StackEmptyException ("stack empty on pop");
      ptr = top;
      top = top.next;
      numElements--;
      return ptr.data;
    }


    public T top() throws StackEmptyException
    {
	if (empty())
	    throw new StackEmptyException ("stack empty on top");
  

	return top.data;
    }

    
    public int numElements()
    {
	return numElements;
    }

    public void clear()
    {
	top = null;
	numElements =0;
    }
}
