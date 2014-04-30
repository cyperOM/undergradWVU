//QueueDyn.java
//cs111 sample implementation of a queue using dynamic memory

public class QueueDyn<T>
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

    
    private Node<T> head,tail;
    private int numElements;


    public QueueDyn()
    {
	head = null;
	tail = null;
	numElements=0;
    }


    public QueueDyn(QueueDyn<T> q)
    { Node<T> ptr, ptr2;

    if (q.head == null)
   { head = null;
     tail = null;
     numElements=0;
   }
    else
	{head = new Node<T>(q.head.data);
	ptr=head;
	for (ptr2=q.head.next; ptr2 != null; ptr2 = ptr2.next)
	    {ptr.next = new Node<T>(ptr2.data);
	     ptr = ptr.next;
	    }
	tail = ptr;
	numElements = q.numElements;
	}
    }


    public T front() throws QueueEmptyException
    {
	if (empty())
	    throw new QueueEmptyException ("queue empty on call to front");
  
	return head.data;
    }


    public void enqueue(T it)
    { Node<T> ptr;
 
    ptr = new Node<T>(it);
    
    if (tail == null)
	head = ptr;
    else
	tail.next = ptr;
    tail = ptr;
    numElements++;
    }

    public T dequeue() throws QueueEmptyException
    {
	Node<T> ptr;

	if (empty())
	    throw new QueueEmptyException ("queue empty on dequeue");

	if (head == tail)
	    tail = null;
	ptr = head;
	head = head.next;
	numElements--;
	return ptr.data;
    }


    public boolean empty()
    {
	return (head == null);
    }


    public int numElements()
    {
	return numElements;
    }

    public void clear()
    {
	head = null;
	tail=null;
	numElements =0;
    }
}

