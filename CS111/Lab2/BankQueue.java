//BankQueue.java
//CS 111 sample program to run a simulation of waiting lines using
//API class LinkedLIst which implements the Queue interface of the API 

import java.util.LinkedList;
import java.io.IOException;
import java.util.Scanner;

public class BankQueue
{
    public static void main(String [] args) throws IOException
    {
	int i, length, entryTime, waitTime;
	int serviceTime =5,
	customersServered =0;
	LinkedList<Integer> bankQueue = new LinkedList<Integer>();
	Scanner sc = new Scanner(System.in);

	System.out.println("enter the simulation time in minutes: ");
	length = sc.nextInt();
	System.out.println("Simulation time: " + length + " minutes");

	//during the simulation
	for (i=0; i<=length; i++)
	    {//does a new person enter the bank?
		//a new person enters every four minutes
		if (i%4 == 0)
		    bankQueue.add(i);

		//is the teller ready for a new customer 
		if (serviceTime == 1 && bankQueue.size()!=0)
		    { entryTime = bankQueue.remove();
		    waitTime = i - entryTime-4; //first person doesn't wait at all
		    System.out.println("the " + (customersServered + 1) + "th Customer waited " +
				       waitTime + " minutes ");
		    customersServered++;
		    }

		//either reset service time or count down to 0
		if (serviceTime > 1)
		    serviceTime--;
		else serviceTime = 5;
	    }
    }
}

