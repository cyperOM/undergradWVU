import java.util.Scanner;

//uses warnsdorff's algorithm
public class KnightsTour 
{
	private static int[][] board;
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int bSize;
		
		//setup board
		System.out.println("Please enter grid size: ");
		bSize = sc.nextInt();
		board = new int[bSize][bSize];
		
		//get starting location and call solve
		System.out.println("Please enter a starting location. Two numbers, seperated by a space.");
		
		if(solve(sc.nextInt(), sc.nextInt(), 1))
		{
			//display the solution
			System.out.println("Solution: ");
			for(int row=0; row<bSize; row++)
			{
				for(int col=0; col<bSize; col++) System.out.printf("%4d", board[row][col]);
				System.out.println();
			}
		}
		else
		{
			//display the solution
			System.out.println("Solution: Not Found!");
		}
	}
	
	private static boolean solve(int row, int col, int num)
	{
		boolean solved = false;
		board[row][col] = num;
		if(num>board.length*board.length-1)
		{
			solved = true;
		}
		else
		{
			int[][] moves = new int[8][2];
			int movesCount = getPossibleMoves(moves, row, col);

			sortMoves(moves, movesCount);
	
			for(int i=0; i<movesCount && !solved; i++)
			{
				solved = solve(moves[i][0], moves[i][1], num+1);
			}
			if(!solved)	board[row][col] = 0;
		}
		
		return solved;
	}
	
	private static void sortMoves(int[][] moves, int movesCount)
	{
		int[] tempMove = new int[2];
		boolean changed = true;

		while(changed)
		{
			changed = false;
			for(int i=0; i<movesCount-1; i++)
			{
				if(getMovesCount(moves[i][0],moves[i][1])>getMovesCount(moves[i+1][0],moves[i+1][1]))
				{
					tempMove[0] = moves[i][0];
					tempMove[1] = moves[i][1];
					
					moves[i][0] = moves[i+1][0];
					moves[i][1] = moves[i+1][1];
					
					moves[i+1][0] = tempMove[0];
					moves[i+1][1] = tempMove[1];
				}
			}
		}
	}
	
	private static int getPossibleMoves(int[][] moves, int row, int col)
	{
		int possibleMovesCount = 0;
		int pRow, pCol;
		
		//go through the first four possible moves
		for(int i=-1; i<2; i+=2)
		{
			for(int j=-2; j<5; j+=4)
			{
				pRow = row+i;
				pCol = col+j;
				//make sure we haven't gone off the board
				if((pRow>-1 && pRow<board.length) && (pCol>-1 && pCol<board.length))
				{
					//check if the possible spot is unfilled
					if(board[pRow][pCol]==0)
					{
						moves[possibleMovesCount][0] = pRow;
						moves[possibleMovesCount][1] = pCol;
						possibleMovesCount++;
					}
				}
			}
		}
		//go through the second four possible moves
		for(int i=-2; i<5; i+=4)
		{
			for(int j=-1; j<2; j+=2)
			{
				pRow = row+i;
				pCol = col+j;
				//make sure we haven't gone off the board
				if((pRow>-1 && pRow<board.length) && (pCol>-1 && pCol<board.length))
				{
					//check if the possible spot is unfilled
					if(board[pRow][pCol]==0)
					{
						moves[possibleMovesCount][0] = pRow;
						moves[possibleMovesCount][1] = pCol;
						possibleMovesCount++;
					}
				}
			}
		}
		return possibleMovesCount;
	}
	
	private static int getMovesCount(int row, int col)
	{
		int possibleMovesCount = 0;
		int pRow, pCol;
		
		//go through the first four possible moves
		for(int i=-1; i<2; i+=2)
		{
			for(int j=-2; j<5; j+=4)
			{
				pRow = row+i;
				pCol = col+j;
				//make sure we haven't gone off the board
				if((pRow>-1 && pRow<board.length) && (pCol>-1 && pCol<board.length))
				{
					//check if the possible spot is unfilled
					if(board[pRow][pCol]==0){
						possibleMovesCount++;
					}
				}
			}
		}
		//go through the second four possible moves
		for(int i=-2; i<5; i+=4)
		{
			for(int j=-1; j<2; j+=2)
			{
				pRow = row+i;
				pCol = col+j;
				//make sure we haven't gone off the board
				if((pRow>-1 && pRow<board.length) && (pCol>-1 && pCol<board.length))
				{
					//check if the possible spot is unfilled
					if(board[pRow][pCol]==0)
					{
						possibleMovesCount++;
					}
				}
			}
		}
		return possibleMovesCount;
	}
}
