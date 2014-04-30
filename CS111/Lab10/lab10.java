// Samantha Brooks
// Corey Hinkle and Cindy Tanner
// Lab Tuesday at 3:00 - 4:50
// Lab 10

import java.util.*;
public class lab10 {

    public static void main (String [] args){
	String word; 
	Stack<Character> letter = new Stack <Character>();
	Scanner scan = new Scanner (System.in);
	int i = 0;
	System.out.println("Please enter the word you would like to know is a palindrome or not: ");
	word = scan.next();
	System.out.println("The word is a Palindrome: " + isPalindrome(letter,word));

    }// end main


    public static boolean isPalindrome(Stack <Character> letter, String word)
    {
	char z;

	System.out.println ("The originial word entered is " + word + ".");

	for(int j = 0 ; j < word.length(); j++)
	    {
		z = letter.push(word.charAt(j));
	    }
	for(int i = 0; i < word.length(); i++)
	    { z = letter.pop();

		if (word.charAt(i) != z )
		    { return false;
		    }
	    }
	return true;
    }// end isPalindrome

}// end lab10