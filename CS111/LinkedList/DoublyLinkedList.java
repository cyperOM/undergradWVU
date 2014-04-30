// Samantha Brooks
// Cindy Tanner and Corey Hinkle
// MWF Lecture 11:00 - 11:50
// Thursday 3:00 - 4:50 Lab

public class DoublyLinkedList<T extends Comparable> {
  private class Node<T> {
    private T data;
    private Node<T> next;
    private Node<T> prev;
    
    private Node (T d){
      data = d;
      next = null;
      prev = null;      
    } // end
    
    private Node (T d, Node<T> pref, Node<T> nref) {
      data = d;
      prev = pref;
      next = nref;
    } // end 
  } // end class Node
  
  private Node<T> head;
  private Node<T> current;
  private int size;
  
  public DoublyLinkedList() {
    head = current;
    size = 0;   
  } // end null constructor
  
  public DoublyLinkedList (DoublyLinkedList<T> l) {
    Node <T> pointer1, pointer2;
    if (l.empty() == false) {
      head = new Node <T> (l.head.data);
      head.next = head;
      head.prev = head;
      pointer1 = head; 
      for (pointer2 = l.head.next; pointer2.next != l.head.next; pointer2 = pointer2.next) {
        pointer1.next = new Node<T>(pointer2.data,pointer1.next,pointer1); 
        pointer1 = pointer1.next; 
      } // end for
      size = l.size; 
    } // end if
    else{
      System.out.println("The orignial list is empty, so there is nothing to copy.");
    } // end else  
  } // end DoublyLinkedList
  
  
  public void insert (T  d) {
    // insert's the new value into its proper ordered position in the list
    Node <T> pointer, temp;
    boolean headMade = false; 
    T dataTemp;  
    if (empty() == true) {   
      head = new Node <T> (d);
      head.prev = head;
      head.next = head;  
    } // end if
    else  {
      begin();
      pointer = head.next;
      if(d.compareTo(head.data) < 0) {
        headMade = true;
        temp = new Node <T> ((T) d, pointer.prev, pointer);
        pointer.prev.next = temp;
        pointer.prev = temp;
        current = temp;  
        dataTemp = head.data;
        head.data = temp.data;
        temp.data = dataTemp;         
      } // end if
      while( pointer != head && (d.compareTo(pointer.data)>0)) {
        pointer = pointer.next; 
      } // end 
      if (headMade == false) {
        temp = new Node <T> ((T) d, pointer.prev, pointer);
        pointer.prev.next = temp;
        pointer.prev = temp;
        current = temp;    
      } // end if
    } // end else 
    size ++;      
  } // end insert method
  
  
  public void remove (T d) throws ListEmptyException, NotInListException {    
//removes an existing value from the list
    try {
      Node <T> pointer;
      if (empty() == true) {
        throw new ListEmptyException ("The list is empty.");
      } // end if   
      else {
        begin();
        for (int i = 0; i < size() ; i++) {
          if (current.data.equals(d) == false){
            advance(); 
          } // end if
        } // end for 
        if (current.data.equals(head.data)) {
          head = head.next;     
        } // end if
        else if(current.data.equals(d)) {
          pointer = current.prev;
          pointer.next = current.next;
        } // end else if     
        if (current.data.equals(d) == false) {
          throw new NotInListException("It is not in the list.");
        } // end if
      } // end else 
      size --;
    } // end try
    catch (ListEmptyException ex) {
      System.out.println(ex.getMessage());
    } // end operator catch
    catch (NotInListException ex) {
      System.out.println(ex.getMessage());
    } // end operator catch
  } // end remove method
  
  
  public void begin() {
    // positions the list at the beginning of the list
    current = head;
  } // end begin method
  
  
  public void advance() {   
    // advances to the next element in the list
    if (empty() == true) {
      System.out.println("The list is empty, so you can't advance.");   
    } // end if
    else if (current.next != null) {
      current = current.next;
    } // end else if
  } // end advance method
  
  
  public void retreat() {
    // retreats to the previous item in the list
    if (empty() == true) {
      System.out.println("The list is empty, so you can't retreat.");  
    } // end if
    else {
      current = current.prev; 
    } // end else
  } // end advance method
  
  
  public T current() throws ListEmptyException {
    // returns the value at the current point of the list
    try {
      if (empty() == true) {
        throw new ListEmptyException("The list is empty, so there isn't a current position.");
      } // end if
      else {
        return current.data;
      } // end else
    } // end try 
    catch (ListEmptyException ex) {
      System.out.println(ex.getMessage());
    } // end operator catch
    return null;
  } // end current method
  
  public boolean end() {
    // determines if we are at the end of the list 
    if (empty() == true){
      System.out.println("The list is empty."); 
      return true;
    } // end if
    System.out.println(current.data);
    if (current.next == head) {
      return true;
    } // end if
    else {
      return false;
    } // end else 
  } // end end method
  
  
  public boolean empty() {   
    // determines if empty
    if (size == 0) {
      return true;
    } // end if
    else {
      return false;
    } // end else    
  } // end empty method 
  
  
  public int size() {
    // returns the number of elements in the list
    return size;
  } // end size method
} // end class DoublyLinkedList


class ListEmptyException extends Exception {
  public ListEmptyException (String s) {
    super (s);
  } // end ListEmptyException    
} // end class ListEmptyException


class NotInListException extends Exception {
  public NotInListException (String s) {
    super (s);
  } // end NotInListException    
} // end class NotInListException