// Samantha Brooks
// Cindy Tanner and Corey Hinkle
// MWF Lecture 11:00 - 11:50
// Thursday 3:00 - 4:50 Lab

import java.util.Scanner;

public class DoublyLinkedListTestClient {
  static Scanner in = new Scanner (System.in);
  public static void main (String [] args) throws ListEmptyException, NotInListException{ 
    
    DoublyLinkedList<Integer> myList = new DoublyLinkedList <Integer> ();

    int variable; 
    int option = menu();
    while(option != 14 ) {
      switch (option) { 
        
        case 1:     
          System.out.println("This specific client class has set the DoublyLinkedList to take in INTEGERS.");
          System.out.println("What would you like to insert?");
          variable = in.nextInt();
          myList.insert(variable);
          break;      
          
        case 2:  
          System.out.println("What would you like to remove?");
          variable = in.nextInt();
          myList.remove(variable);
          break;  
          
        case 3:     
          myList.begin();
          System.out.println("You have been moved to position: " + myList.current() + ".");
          break;  
          
        case 4:     
          myList.advance();
          break; 
          
        case 5:     
          myList.retreat();
          break;  
          
        case 6:     
          System.out.println("The current position contains the item: " + myList.current() + ".");
          break;
          
        case 7:     
          System.out.println("The end of the list has been reached: " + myList.end() + ".");
          break;
          
        case 8:     
          System.out.println("The list is empty: " + myList.empty() + ".");    
          break; 
          
        case 9:     
          System.out.println("The size of the list is: " + myList.size() + " items.");
          break;  
          
        case 10:    
          // Display Forwards
          myList.begin();
          if (myList.empty() == false){ 
            System.out.println("The list in the forwarded order entered is: ");       
            for (int i = 0; i < myList.size(); i ++){            
              System.out.println(myList.current());
              myList.advance(); 
            } // end for   
          } // end if
          else {
            System.out.println("The list is empty. ");
          } // end else
          break; 
          
        case 11:     
          // Display Reverse
          myList.begin();
          if (myList.empty() == false){ 
            System.out.println("The list in the reversed order entered forwards is: ");
            for (int i = 0; i < myList.size(); i ++){      
              myList.retreat();   
              System.out.println(myList.current());           
            } // end for   
          } // end if
          else {
            System.out.println("The list is empty. ");
          } // end else
          break;  
          
        case 12:
          DoublyLinkedList<Integer> newList = new DoublyLinkedList<Integer>(myList) ;
           newList.begin();
          if (myList.empty() == false){ 
            System.out.println("The copied list in the forwarded order is: ");     
            for (int i = 0; i < newList.size(); i ++){            
              System.out.println(newList.current());
              newList.advance(); 
            } // end for   
          } // end if
          else {
            System.out.println("The list is empty. ");
          } // end else
          break;
          
        case 13:
          System.out.println("Insert: inserts the new value into its proper ordered position.");
          System.out.println("Remove: removes an existing value from the list.");
          System.out.println("Begin: positions the list at the beginning of the list.");
          System.out.println("Advance: advances to the next element in the list.");
          System.out.println("Retreat: retreats to the previous item in the list.");
          System.out.println("Current: returns the value at the current point of the list.");
          System.out.println("End: deteremines if we are at the end of the list.");
          System.out.println("Empty: determines if the list is empty.");
          System.out.println("Size: returns the number of elements in the list."); 
          System.out.println("Forwards: prints the list in the sorted order.");
          System.out.println("Reverse: prints the list in the reversed order.");
          System.out.println("Exit: closes the program");
          break;
          
        default: System.out.println("That is not a valid choice, please try again.");
      } // end case 
      option = menu();
    } // end while
  } // end main  
  
  public static int menu () { 
    int option;    
    System.out.println("-----------------------------------");
    System.out.println("|              MENU:              |");
    System.out.println("| Insert                        1 |");
    System.out.println("| Remove                        2 |");
    System.out.println("|----------- Position ------------|");
    System.out.println("| Begin                         3 |");
    System.out.println("| Advance                       4 |");
    System.out.println("| Retreat                       5 |");
    System.out.println("| Current                       6 |");
    System.out.println("| End                           7 |");
    System.out.println("| Empty                         8 |");
    System.out.println("| Size                          9 |");
    System.out.println("|------------ Display ------------|");
    System.out.println("| Forwards                     10 |");
    System.out.println("| Reverse                      11 |");
    System.out.println("| Copy and Print               12 |");
    System.out.println("|------------- Other -------------|");
    System.out.println("| Menu Explanations            13 |");  
    System.out.println("| Exit                         14 |");
    System.out.println("-----------------------------------");
    option = in.nextInt();
    return option;
  } // end menu
} // end DoublyLinkedListTestClient
