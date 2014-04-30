// Samantha Brooks
// Corey Hinkle and Cindy Tanner
// MWF Lecture 11:00 - 11:50      Lab Tuesday 3:00 - 4:50
// Section 7

import java.util.LinkedList;
import java.util.Scanner;
import java.util.Queue;

public class lab11 {
  
    static  Scanner in = new Scanner (System.in);
    static Queue <Person> personQue1 = new LinkedList<Person>();
    static Queue <Person> personQue2 = new LinkedList<Person>();
 

    public static int menu(){

	int choice;

	System.out.println("Create a person          1");
	System.out.println("Display Queue            2");
	System.out.println("Remove a Person          3");
	System.out.println("Display next person      4");
	System.out.println("Exit                     5");
	System.out.println("Enter your choice.");
	choice = in.nextInt();
	return choice;
    }// end menu

    public static void main (String [] args){

	int choice = 0;
	choice = menu();{
	    while(choice !=5){
		switch (choice) {
		case 1: addPerson();
		    break;
		case 2:displayQue();
		    break;
		case 3:removePerson();
		    break;
		case 4: printPerson();
		    break;
		case 5:
		    break;
		default: System.out.println("That's not valid");
		}// end switch
		choice = menu();
	    }// end while
	}// end menu
    }// end main



    public static void   addPerson(){
	System.out.println("What is the first name?");
	String firstname = in.next();
	System.out.println("What is the last name?");
	String lastname = in.next();
	System.out.println("what is the age?");
	int age = in.nextInt();
	Person temp = new Person(firstname, lastname, age);
	personQue1.offer(temp);
    }// end addPerson



    public static  void  displayQue(){
	Person temp;
	while(!personQue1.isEmpty()) {
	    temp = personQue1.remove();
	    temp.printPerson();
	    personQue2.offer(temp);
	}// end while

	while(!personQue2.isEmpty()){
	    temp = personQue2.remove();	 
	    personQue1.offer(temp);
	}// end second while
    }// end displayQue



    public static void removePerson(){
	System.out.println(personQue1.remove());
    }// end removePerson



    public static void printPerson()  {
	Person temp;
	temp = personQue1.remove();
	temp.printPerson();
    }// printPerson
}// end lab11
