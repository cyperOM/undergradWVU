// Samantha Brooks
// Lab 4 (Tuesday 3:00-450)
// Corey Hinkle and Cindy Tanner

import java.util.Scanner;

class lab4 {

public static void main (String args [])
{
    int value, firstIndex, lastIndex;

    Scanner in  = new Scanner(System.in);

    System.out.println("Please input an integer value.");
    value = in.nextInt();

    int [] myArray;
    myArray = new int [5];
    myArray [0] = 1;
myArray[1]= 2;
myArray[2]= 3;
myArray[3] = 4;
myArray[4] = 5;

   
    System.out.println(evenFactorial(value));
    
    System.out.println(multiplyArray(myArray,0 ,4));

}// end main

    public static int  evenFactorial (int value)

   { 
	if (!( value % 2 == 0))
	    { value = value -1; }
	    
	if (value <= 1 )
	    {
		return 1;
	    
	    }// end if 

	else {

		return value*evenFactorial(value-2);
	    

	}// end else 
    }// end factorial


    public static int multiplyArray (int[]myArray, int firstIndex, int lastIndex)
 { 
 
    if (firstIndex == lastIndex) {
 
	return myArray[lastIndex]; 

    }

    else { 
	return	myArray[lastIndex]*multiplyArray(myArray,firstIndex,lastIndex - 1);
    }


       
    }// end multiplyArray

}// end class 



