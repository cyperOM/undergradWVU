public class DoublyLinkedList<Comparable>
{
 

 private static class Node<Comparable>
 {
  private Comparable data;
  private Node<Comparable> next;
  private Node<Comparable> prev;

  private Node (Comparable d)
  {  data = d;
     next = null;
     prev=null;
  }
  private Node (Comparable d, Node<Comparable> n, Node<Comparable> p)
  {
     data =d;
     next =n;
     prev =p;
  }   
 }

 private Node<Comparable> head;
 private Node<Comparable> current;
 private int size;

   
 public DoublyLinkedList()
 {    head = new Node<Comparable> (null);
      size= 0;
 }
 
 public DoublyLinkedList(DoublyLinkedList<Comparable> l)
 {   Node<Comparable> p,p1;
     head = new Node<Comparable>(l.head.data);
     head.next = head;
     head.prev=head;
     p = head;
     for (p1 = l.head.next; p1.next != l.head; p1 = p1.next)
     { p.next = new Node<Comparable>(p1.data,p.next,p);
       p = p.next;
     }
     size = l.size;
 }
 
 public void insert (java.lang.Comparable d) 
 //insert's the new value into its proper position in the list
 {
   Node<Comparable> p, trav;
   
   trav= head.next;
   while (trav != head && d.compareTo(trav.data) <1)
   { trav = trav.next;}
   
   p = trav.prev;
   p.next = new Node<Comparable>((Comparable)d,trav,p);
   trav.prev= p.next;
   size++;
 }

 public void remove (java.lang.Comparable d) throws ListEmptyException, NotInListException
 //removes an existing value from the list
 {
   Node<Comparable> p, q, trav;
   
   if (size == 0) throw new ListEmptyException(" ");
   
   trav=head.next;
   while (trav != head && !(trav.data).equals(d))
     trav = trav.next;
   
   if (trav==head) throw new NotInListException(" ");
   
   p = trav.prev;
   q = trav.next;
   p.next = q;
   q.prev= p;
   size--;
 }
   
 public void begin() {current= head.next;}
 //positions the list at the beginning of the list
  public void advance() { current = current.next;}  
 //advances to the next element in the list
 
 public void retreat() { current = current.prev;} 
 //retreats to the previous item in the list
 Comparable current() throws ListEmptyException  
 //returns the value at the current point of the list
 {
   if (empty()) throw new ListEmptyException(" ");
   return current.data;
 }
 public boolean end() { return (current == head);}  
//determines if we are at the end of the list
 public boolean empty() { return (size == 0);}
 //determines if the list is empty
 public int size(){return size;}
 //returns the number of elements in the list 
}
