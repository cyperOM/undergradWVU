// Samantha Brooks
// Lab 7
// Corey Hinkle 
// Lab Section 007 T 3:00 - 4:50



import java.util.Scanner;

public class lab7 {

    public static void  main (String[]  Args){

	Scanner in = new Scanner(System.in);
 
	String  first , last;
	int  age;
	char f, l;

	System.out.println("Please enter the first name: ");
	first = in.next();

	System.out.println("Please enter the last name: ");
	last= in.next();

	System.out.println("Now, please, enter the age: ");
	age = in.nextInt();
	Person temp = new Person();

	Person temp =  Person(  first ,   last ,  age);

	first.charAt(0) = f;
	last.charAt(0) = l;
  
	while(age <= 123 || f.isLowerCase || l.isLowerCase){

	    try{
		if (f.isUpperCase || l.isUpperCase || age <= 123 )
		    { 
		age = 	setAge();
		first =	setFirst();
		last =	setLast();
		    }
	    }// end try


	    catch (Person.CapErrorException e){
		System.out.println("Cap error");
	    }// end catch
	    catch (Person.TooOldException e){
		System.out.println("Too old error");
	    }// end catch
	}// end try 

    } // end main
}// end class
 



