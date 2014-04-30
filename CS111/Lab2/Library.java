import java.util.Scanner;
import java.util.Calendar;


public class Library
{
  static LibraryItem [] library = new LibraryItem[100];
  
  static Scanner sc = new Scanner(System.in);
  static enum displayType {all, books, reference, cds, dvds};
  
  public static int menu() 
  {
    int choice;
    
    System.out.println ("Add items to the Library:                1");
    System.out.println ("Display # of Items in the Library        2");
    System.out.println ("Display all Items in the Library         3");
    System.out.println ("Dispaly the reference Books              4");
    System.out.println ("Search for an Item                       5");
    System.out.println ("Borrow an Item                           6");
    System.out.println ("Return an Item                           7");
    System.out.println ("Renew an Item                            8");
    System.out.println ("Display all checked out items            9");
    System.out.println ("Display all overdue items               10");
    System.out.println ("Exit                                    11");
    System.out.println ("\nEnter your choice: ");
    choice = sc.nextInt();
    sc.nextLine();
    return choice;
  }
  public static void addBook()
  { 
    String title, author;
    System.out.println ("Enter the Book's title: ");
    title = sc.nextLine();
    System.out.println("Enter the Book's Author: ");
    author = sc.nextLine();
    library[library[0].getNumItems()] = new Book (title, author);
  }
  
  public static void populate()
  {  int i;
    System.out.println ("Add a Book                          1");
    System.out.println ("Add a Reference Book                2");
    System.out.println ("Add a CD                            3");
    System.out.println ("Add a DVD                           4");
    System.out.println ("Enter Choice: ");
    i = sc.nextInt();
    sc.nextLine();
    switch (i)
    {
      case 1: addBook();
              break;
    }
 }
  
  public static void displayCollection (displayType type) 
  {
    int i;
    switch (type)
    { case all: for (i=0;i<library[0].getNumItems();i++)
                                   System.out.println(library[i]);
                                 break;
    }   
  }
  
  public static void search() {}
  public static void takeBack() {}
  public static void renew() {}
  public static void borrow() {}
  public static void overdue() {}
  public static void checkedOut() {}
  
  public static void main(String [] args)
  {

    int i;
    int choice = 0;
    
    choice = menu();      
    while (choice !=11) 
    { switch (choice)
      {
      case 1: populate();
              break;
      case 2: System.out.println ("There are " + library[0].getNumItems() + " items in the library");
              break;
      case 3: displayCollection(displayType.all);
              break;
      case 4: displayCollection(displayType.reference);
              break;
      case 5: search();
              break;
      case 6: borrow();
              break;
      case 7: takeBack();
              break;
      case 8: renew();
              break;
      case 9: checkedOut();
              break;
      case 10: overdue();
               break;
      case 11: break;
      default: System.out.println ("Invalid Option, please retry");
     }
    choice = menu();
    }
  }
}