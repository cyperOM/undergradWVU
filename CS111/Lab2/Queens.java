//Queens.java
//CS 111 recursive backtracing algorithm to solve 8 queens problem

public class Queens{

    //rows holds true if that row is available to place a queen
    static boolean [] rows= new boolean[8];
    //diag1 holds true if the diagonal is available for the queen placement
    static boolean [] diag1= new boolean[15];
    //diag2 holds true if the diagonal is available for the queen placement
    static boolean [] diag2 = new boolean[15];
    //cols contains the row value for the queen placed in that column
   static  int [] cols= new int[8];


    public static boolean place (int i){
	int j;
	boolean q;
  
	System.out.println("entering place, i= " + i);
	j=-1;
	do{
	    j++;
	    q = false;
	    //is this placement okay?
	    if (rows[j] && diag1[i+j] && diag2[(i-j) + 7]){
		//yes record the placement
		cols[i] = j;
		rows[j] = false;
		diag1[i+j]=false;
		diag2[(i-j) + 7] = false;
		//if solution is incomplete try next possibility
		if (i<7){
		    q = place (i+1);
		    System.out.println ("Returning from call: i = " + i + " j = " + j);
		    //on return did we successfully place the queen
		    if (!q){
			//no unrecord the placement
			rows[j] = true;
			diag1[i+j] = true;
			diag2[(i-j) + 7] = true;
		    }
		}
		else q=true; //yes we placed the queen
	    }
	}while (!q && j<7); //continue if all queens are not placed
	return q;
    }

    public static void main(String[] args){

	int i;
	boolean q;
  
	//initalize to unset board
	for (i=0;i<8;i++){
	    rows[i]=true;
	    cols[i]=-1;
	}
	for (i=0;i<15;i++){
	    diag1[i]=true;
	    diag2[i]=true;
	}

	//place the first queen
	q=place (0);

	if (q)
	    System.out.println("all queens placed successfully");
	else System.out.println("could not place all queens");

	//dislpay the placements
	for (i=0;i<8;i++)
	    System.out.println("the queen in column " + (i+1) +
         " is in row " + (cols[i] + 1));
    }
}


