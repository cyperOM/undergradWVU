//ListDyn.java
//cs111 sample program
//dynamic implementation of an ordered list
//the template class used must provide equals, compareTo and toString


public class ListDyn<Comparable>
{


    private static class Node<Comparable>
    {
	private Comparable data;
	private Node<Comparable> next;
	
	private Node (Comparable d)
	{
	    data =d;
	    next= null;
	}
	
	private Node (Comparable d, Node<Comparable> ref)
	{
	    data =d;
	    next = ref;
	}
    }

    private Node<Comparable> head;
    private int numElements;

    public ListDyn()
    { head =null;
      numElements=0;
    }


    public ListDyn(ListDyn<Comparable> l)
    { Node<Comparable> ptr, ptr2;
    if (l.head==null)
	{numElements=0;
	head =null;
	}
    else
	{head = new Node<Comparable>(l.head.data);
	ptr=head;
	for (ptr2=l.head.next; ptr2!=null; ptr2= ptr2.next)
	    { ptr.next = new Node<Comparable>(ptr2.data);
	      ptr = ptr.next;
	    }
	}
    numElements=l.numElements();
    }

    public void display_list()
    {
	Node<Comparable> ptr;

	ptr=head;
	while (ptr !=null)
	    { System.out.println(ptr.data);
	      ptr= ptr.next;
	    }
    }

    public void insert (java.lang.Comparable it) 
    { Node<Comparable> ptr, trav, prev;

      prev = null;
      trav= head;
      while (trav != null && it.compareTo(trav.data)>0)
	  {  prev= trav;
	     trav= trav.next;
	  }

      ptr = new Node<Comparable>((Comparable)it,trav);
      
      if (prev == null)
	  head = ptr;
      else
	  prev.next=ptr;
      numElements++;
    }


    public void remove (Comparable it) throws ListEmptyException, NotInListException
    {
	Node<Comparable> ptr, prev, trav;

	if (empty())
	    throw new ListEmptyException("list empty on remove");
  
	prev=null;
	trav = head;
	while (trav !=null && !trav.data.equals(it))
	    {
		prev=trav;
		trav= trav.next;
	    }
	if (trav==null)
	    throw new NotInListException("item not in list on remove");
  
	if(prev==null)
	    head = trav.next;
	else
	    prev.next = trav.next;
	numElements--;
    }


    public boolean empty()
    {
	return head ==null;
    }


    public int numElements()
    {
	return numElements;
    }

    public void clear()
    {
	numElements =0;
	head=null;
    }
}




 
  


        
