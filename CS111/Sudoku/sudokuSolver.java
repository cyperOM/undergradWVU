// Samantha Brooks
// Large Scale Programming Assignment I
// Lecture Section 001 - MWF 11:00 - 11:50 - Cindy Tanner
// Lab Section 007 - T 3:00 - 4:50 - Corey Hinkle
// used wwww.colloquial.com for help (http://www.colloquial.com/games/sudoku/java_sudoku.html)

import java.util.Scanner;
import java.io.FileReader;

public class sudokuSolver{
static   int [][] puzzle = new int [6][6];

public static void main(String [] Args) throws Exception{  
Scanner in = new Scanner(new FileReader("testcase3.txt"));
   
    for (int iRows = 0; iRows < puzzle.length; iRows++){   
      for(int jColumns = 0; jColumns < puzzle.length; jColumns++) {
 puzzle [iRows][jColumns]= in.nextInt();
 //System.out.println("the value of element [" +iRows + "] [" + jColumns +"] is  " + puzzle[iRows][jColumns]);    
      }// end inner for
    }// end outter for
    
System.out.println("The original puzzle is: ");
print2DArray();
int rows = 0;
int columns = 0;

solver(0,0,puzzle);
System.out.println();
System.out.println("The solution can be found: " + solver(0,0,puzzle) + ".");
System.out.println("The solution is (if possible): ");
                    
printSolution(puzzle);

  }// end main
  

/* Solves the Sudoku puzzle
 * Pre-Conditions: rows and columns must be valid positive integers >= 6
 * puzzle must be a valid 2D integer array
 * Post-Conditions: prints out the 2D array with solution values
 * Response to abnormal behavior : error terminate 
 * @ Param : a valid 2D Array
 * @ Param : a valid positive row int value
 * @ Param : a valid positive column in value
 * @ Return: returns true if solution is found, else it returns false
 * */
  public static boolean solver(int rows, int columns, int[][] puzzle) {
    if (rows == puzzle.length) {
      rows = 0;
      if ( ++ columns   == puzzle.length )  
        return true;
    }// end outer if 
    if (puzzle[rows][columns] != 0 ) {
    
      return solver(rows +1, columns, puzzle);
    }// end if         
    for (int value = 1;  value <= puzzle.length; ++ value) {
      if (rowChecker(rows, value, puzzle) && columnChecker(columns, value, puzzle) && boxChecker(rows , columns, value, puzzle) ) {
      puzzle[rows][columns] = value;      
    if (solver(rows+1,columns,puzzle)) 
      return true;  
      }
    }
    puzzle[rows][columns] = 0; 
    return false; 
  } // end solver


/* Prints out a 2D Array
 * Pre-Conditions: the array must be the statitc 2D array
 * Post-Conditions: prints out the 2D array with values
 * Response to abnormal behavior : error terminate 
 * @ Param : a valid 2D Array
 * @ Return: the printed Array
 * */
  public static void print2DArray(){
    for(int iRows = 0; iRows < 6; iRows++){ 
      for (int jColumns = 0; jColumns < 6; jColumns++)         
        System.out.print(puzzle[iRows][jColumns] + " " );
      System.out.println();
    }// end for
  }// end print
  
/* Prints out a 2D Array with Solution
 * Pre-Conditions: the array must be a 2D iint array
 * Post-Conditions: prints out the 2D array with values
 * Response to abnormal behavior : error terminate 
 * @ Param : a valid 2D Array
 * @ Return: the printed Solution Array
 * */
  public static void printSolution(int [] [] puzzle){
    for(int rows= 0; rows < 6; rows++){ 
      for (int columns = 0; columns < 6; columns++)         
        System.out.print(puzzle[rows][columns] + " " );
      System.out.println();
    }// end for
  }// end print
  
  
  /* Checks rows for a specified value
 * Pre-Conditions: row value must be a valid integer between 0 - 6
 * Post-Conditions: if value is found in the specified row
 * Response to abnormal behavior : error terminate 
 * @ Param : row is the number of the row to search
 * @ Param: value is the searched value
 * @ Param : puzzle is the puzzle to be used
 * @ Return: true if value is found in row, else return false
 * */
  public static boolean rowChecker (int row, int value, int [][]puzzle ){
     
    for(int jColumns= 0;  jColumns < puzzle.length ; ++ jColumns){
      if (puzzle[row][jColumns] == value) return false;  
    }// end for
    return true;
  }// end rowChecker
  
 
  /* Checks columns for a specified value
 * Pre-Conditions: column and value must be a valid integer between 0  - 6  
 * Post-Conditions: if value is found in the specified column
 * Response to abnormal behavior : error terminate 
 * @ Param :  value is the searched value
 * @ Param : column is the column to be checked 
 * @ Param : puzzle is the puzzle to be used
 * @ Return: true if value is found in column, else return false
 * */
  public static boolean columnChecker (int column, int value, int [][] puzzle){

    for(int iRow = 0; iRow < puzzle.length ; ++iRow) {
      if (puzzle[iRow][column] == value) 
        return false;
    }// end for
    return true;   
  }// end columnChecker
  
  
  /* Checks for a specified value in a certain box
   * Pre-Conditions: boxNumber must be a valid integer between 1 - 6
   * and value must be a valid method between 0 - 6 
   * Post-Conditions:
   * Response to abnormal behavior : error terminate 
   * @ Param : value is the searched value
   * @ Param : row is the row to be searched
   * @ Param : column is the column that will be searched
   * @ Param : puzzle is the puzzle to be used
   * @ Return: true if value is found in box, else return false
   * */
  public static boolean boxChecker (int row, int column, int value, int [][] puzzle){
    int rBox = (row/2)*2;
    int cBox = (column / 3)*3; 
    for (int iRow = 0 ; iRow < 2 ; ++iRow) 
      for(int jColumn = 0; jColumn < 3 ; ++ jColumn)
      if(puzzle[rBox + iRow] [cBox + jColumn] == value)   return false;
 
  return true;  
}// and boxChecker
} // end class
  

