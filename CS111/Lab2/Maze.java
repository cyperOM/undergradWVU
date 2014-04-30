//Maze.java
//CS 111 solution to the maze problem of assignement 1

public class Maze
{
  static char [] [] maze = {{'H','H','H','P','H','H','H','P','H','H'},
    {'H','H','P','P','H','H','P','P','H','H'},
    {'P','H','P','H','H','P','P','H','P','E'},
    {'H','H','H','H','P','P','H','H','P','H'},
    {'P','P','P','H','P','H','H','P','P','H'},
    {'H','H','P','H','P','H','H','P','H','H'},
    {'H','H','P','P','P','H','H','P','P','H'},
    {'H','H','H','P','H','H','H','H','P','H'},
    {'H','H','H','P','P','P','P','P','P','H'},
    {'H','H','H','H','P','H','H','H','H','H'}};
  
  public static void printMaze ()
  {
    for (int i =0;i<10;i++)
    {  for (int j=0; j<10;j++)
      {if (maze[i][j] == 'P') System.out.print(" ");
           else System.out.print (maze [i][j]);
       }
     System.out.println();
    }  
  }
  
  public static boolean wayOut (int r , int c)
  {
      //intialize selection of posibilities
    boolean q=false;
    int p=0;
    
    System.out.println ("coming in r = " + r + " c = " + c);
    //determine if this placement is okay
    if (r < 0 || r > 9 || c < 0 || c > 9) return false;
    if (maze[r][c]=='E') return true;
    if (maze[r][c]=='H') return false;
    
    //record the placement
    maze [r][c] = 'H';
    //make next move
    do
    {
      p++;
      switch(p)
      { case 1: q = wayOut(r,c+1);
        break;
        case 2: q = wayOut(r+1,c);
        break;
        case 3: q= wayOut(r-1,c);
        break;
        case 4: q= wayOut(r,c-1);
        break;
      }
      //was move successful
      if (q) return true;
      System.out.println ("on return and not successful, r = " + r + " c = " + c);
      //try another move while there are still moves to make
    }while (!q && p < 4);
    //if here that move was totally unsuccessful so unrecord it
    maze[r][c]='P';      
    return false;
  }
  public static void main(String [] args)
  {
    
    System.out.println ("the maze is:");
    printMaze();
    
    if (wayOut(0,0)) System.out.println("I am Free!");
     else System.out.println("I am Trapped!");
  }
}
    
    
