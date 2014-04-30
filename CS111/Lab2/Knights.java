//Knights.java
//a CS 111 solution to the knights tour using the general algorithm

import java.util.Scanner;

public class Knights
{
    private static final int BOARDSIZE=5;
    private static int [] [] board = new int [BOARDSIZE][BOARDSIZE];

    private static boolean onBoard(int r, int c)
    {
	return (r>=0 && r < BOARDSIZE && c >=0 && c < BOARDSIZE);
    }

    private static boolean available(int r, int c)
    {
	return (board[r][c]== -1);
    }

    private static boolean ktour (int r, int c, int moveNum)
    {
	int i, nr=0, nc=0;

	System.out.println("movenum=" + moveNum);
	//is spot acceptable
	if (!onBoard(r,c) || !available(r,c)) return false;

	//record move
	board[r][c]=moveNum;
	
	//done the whole board?
	if (moveNum==BOARDSIZE*BOARDSIZE) return true;

	//pick next move
	for (i=0;i<8;i++)
	    {switch (i)
		{ case 0: nr = r+1; nc=c+2; break;
		case 1: nr = r+1; nc = c-2; break;
		case 2: nr = r +2; nc =c+1; break;
		case 3: nr = r+2; nc = c-1; break;
		case 4: nr = r-1; nc = c-2; break;
		case 5: nr = r-1; nc = c+2; break;
		case 6: nr = r-2; nc=c-1;break;
		case 7: nr= r-2; nc= c+1; break;
		}
	    if (ktour (nr,nc,moveNum+1)) return true;
	    }

	//all eight possible moves tried no success
	//unrecord move and backtrack
	board[r][c] = -1;
	return false;
    }

    public static void main(String [] args)
    {
	int i,j;
	char c;
	int startRow, startCol;
	Scanner sc = new Scanner(System.in);

	//initial board
	for (i=0; i < BOARDSIZE;i++)
	    for (j=0;j<BOARDSIZE;j++)
		board[i][j] = -1;

	//enter start point
	System.out.print("enter starting row: ");
	startRow = sc.nextInt();
	System.out.print("enter starting Col: ");
	startCol = sc.nextInt();
	
	//start the tour
	if (ktour (startRow, startCol,1))
	    { System.out.println ("tour successful contents are: ");
	      for (i=0; i < BOARDSIZE;i++)
		  {for (j=0;j<BOARDSIZE;j++)
		      System.out.print(board[i][j]+ "  ");
		  System.out.println();
		  }
	    }
	else System.out.println ("Tour not possible from (" + startRow + "," + startCol + ")");
    }
}
