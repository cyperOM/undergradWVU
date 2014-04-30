/*Program to solve a maze */

#include <stdio.h>
FILE *filepointer; 
int rows, columns;
int rowCheck,columnCheck;
int numCharacter;
int i , j, check = 0;
char file, filename[10];
char maze[200][200];
int entrance, mazeExit = 0;
char entrancePoint, exitPoint;  
char movement,start;
int solved, triedLeft, triedRight, wentRight, wentDown, wentLeft, wentUp, triedUp, triedDown, specialDown = 0; 
#define false 0

void solveMaze(int w, int x, int y, int z);
int  moveRight(int x);
int  moveLeft(int x);
int  moveDown(int y);
int  moveUp(int y);

main() {
  printf("How many rows does the maze contain?\n");
  scanf("%d", &rows); 
  if (rows > 200 || rows < 5){
    printf("Sorry, that's not a valid entry. Please rerun the program and try a number between 5 and 200.");
    return(1);
  }// end if

  printf("How many columns does the maze contain?\n");
  scanf ("%d", &columns); 
  if (columns > 200 || rows < 5){
    printf("Sorry, that's not a valid entry. Please rerun the program and try a number between 5 and 200.\n");
    return (1);
  }// end if

  printf("The maze you specified contains %d rows and %d columns.\n", rows,columns);
  printf("What file can the specified maze be found in?\n");
  scanf("%s", filename);
  printf("Looking for the file:'%s' on your computer. Please wait.\n", filename);
  filepointer = fopen(filename, "r");
  
  if(filepointer == NULL){
    printf("There was an error finding the file. Please try running the program again.\n");
    return 1;
  }// end if
  
 
    printf("There were no errors finding the program.\n");
    printf("The file contained the following maze:\n");
     
    //read in the file and remove all of the whitespace
    while((file = fgetc( filepointer))!= EOF){
      numCharacter = numCharacter + 1; 
      if (file=='1' || file=='0'){	
	maze[i][j]= file;
	j++;      
      }// end if
      if (file =='\n'){	
	j=0;
	i++;      
      }// end if
    }// end while

    //print out maze
    for (i = 0; i < rows; i++){
      for (j =0; j < columns; j++){
	printf("%c",  maze[i][j]);
      }// end for 
      printf("\n");
    }// end for 
    
    if (numCharacter == (rows * columns * 2 + rows)){
      printf("The maze is correct.\n");
    }// end if
    else {
      printf("The maze does not match the diminsions you've specified.\n");
      printf("The program will now terminate.\n");
      return 1; 
    }// end else
 
  fclose(filepointer);
  printf("Now to solve the maze... Computing solution.\n");

/* Find entrance and exit */
  int iEntrance, iExit, jEntrance, jExit;

// checks the first column for an entrance
  while (entrance == 0 && mazeExit == 0){ 
    for (i = 0; i < rows; i++){
      if (maze[i][0]=='0'){
	if (entrance ==0){
	  entrance = 1;
	  printf("The entrance was found in the first column.\n");
	  entrancePoint = maze[i][0];
	  iEntrance = i;
	  jEntrance = 0;
	} // end if
	else {
	  printf("The exit was found in the first column.\n");
	  mazeExit = 1;
	  exitPoint = maze[i][0];
	  iExit = i;
	  jExit = 0; 
	  mazeExit = 1;
	} // end else
      } // end if
    }// end for 
  
    // checks the last row for an entrance 
 
    for ( j = 0; j < columns; j ++){
      if( maze[rows-1][j] == '0'){
	if (entrance == 0){
	  entrance = 1;
	  entrancePoint = maze[rows-1][j];
	  iEntrance = rows-1;
	  jEntrance = j;
	  printf("The entrance was found in the last row.\n"); 
	}//end if
	else {
	  printf("The exit was found in the last row.\n");
	  exitPoint = maze[rows-1][j];	 
	  iExit = rows-1;
	  jExit = j;
	  mazeExit = 1;
	}// end else
      }// end if
    }// end for
    
    // checks the last column for an entrance   
for (i = rows; i > 0 ; i--){
      if (maze[i][columns-1]=='0'){	
	if (entrance == 0){	 
	  entrance = 1;
	  entrancePoint = maze[i][columns-1];
	  iEntrance = i;
	  jEntrance = columns-1; 
	  printf("The entrance was found in the last column.\n");
	}// end if
	else {
	  printf("The exit was found in the last column.\n");
	  exitPoint = maze[i][columns-1];
	  iExit = i;
	  jExit = columns-1;
	  mazeExit = 1;
	}// end else
      }// end if 
 }// end for

    // checks the first row for an entrance
 for (j = columns; j > 0; j--){ 
      if (maze[0][j] == '0'){;
	if (entrance == 0){
	  printf("The entrance was found in the first row.\n");
	  entrance = 1;
	  iEntrance = 0;
	  jEntrance = j;
	  entrancePoint = maze[0][j];
	}// end if
	else {
	  printf("The exit was found in the first row.\n");
	  mazeExit = 1;
	  iExit = 0;
	  jExit = j;
	  exitPoint = maze[0][j];
	}// end else
      }// end if
 }// end for
  }// end while
  
  solveMaze(iEntrance,jEntrance, iExit, jExit);

  return 0; /* indicate program ended successfully */
}// end Main 

void solveMaze(int iEntrance, int jEntrance, int iExit, int jExit){ 
  printf("starting\n=================\nx = %d\ny = %d\n", iEntrance, jEntrance);
  printf("finish\n=================\nx = %d\ny = %d\n", iExit, jExit);
  maze[iEntrance][jEntrance] = 'x';

int  startI = iEntrance;
 int startJ = jEntrance;
 
  while(solved == 0){   
    // Checks to see if right movement is possible      
    if((maze[iEntrance][jEntrance+1] == '0' ||  maze[iEntrance][jEntrance+1] == 'x' )  &&(specialDown == 0 && triedLeft == 0 && wentRight == 0))  {     
      
      // checks to see if there is a path down
      if (maze[iEntrance+1][jEntrance] == '0'){
	printf("Special case to see if path down.\n");
	  wentRight = 1;
	  triedRight = 0;	
	   
      }
      // if it's possible to go down consecutively
	  else  if (wentDown == 1 && triedDown == 1){
	  printf("Going consecutively down\n");
	  specialDown = 1; 
	}

      // no special condtions met, go right
	else {      
       printf("right\n");
	 jEntrance = moveRight(jEntrance); 
	 maze[iEntrance][jEntrance] = 'x';	
	 triedRight = 1;
	}
      
      triedDown = 0;
      triedUp = 0;
      wentUp = 0;
      wentDown = 0;
      wentLeft = 0; 
	 }// end if 
     
     //checks to see if down movement is possible. 
    else if ((maze[iEntrance+1][jEntrance] == '0' ||  maze[iEntrance+1][jEntrance] == 'x') && (triedUp ==0 && wentDown == 0 && triedLeft == 0) ){      
     
 // go down 
      printf("down \n");
       iEntrance  = moveDown(iEntrance);
       maze[iEntrance][jEntrance] = 'x';
       triedDown = 1; 
       triedRight = 0;
       triedLeft = 0;
       wentRight = 0;
       wentLeft = 0;
       wentUp = 0;
    }

    // checks to see if left movement is possible
    else if((maze[iEntrance][jEntrance-1] == '0' || maze[iEntrance][jEntrance-1] == 'x') && (specialDown == 1 ||(triedRight == 0 && wentUp ==0))){

      //checks to see if there is a path up
      if(maze[iEntrance-1][jEntrance] == '0'){
	printf("Special case to see if path up.\n");
	triedLeft = 0;
	wentLeft =0 ;
      }

  
      // if not go left 
      else{ 
	printf("left\n");
	jEntrance = moveLeft(jEntrance);
      maze[iEntrance][jEntrance]='x';
      triedLeft = 1;      }
     
      wentRight = 0;
      wentUp = 0;
      wentDown=0;
      triedUp = 0;
      triedDown = 0;
      specialDown = 0;
      }

    // checks to see if the maze has been solved or met the conditions for not solvable
    if  (check == 0){   
    if (maze[iEntrance][jEntrance+1] != '0' &&  maze[iEntrance][jEntrance+1] != '1' &&  maze[iEntrance][jEntrance+1] != 'x'){
      if (startI == iEntrance && startJ == jEntrance){
	printf("The maze was not solvable\n");
      }
      else{
     printf("The maze has been solved by finding the exit on the right side. \n");    
      }     
solved = 1;  
   }
   
   else if (maze[iEntrance][jEntrance-1] != '0' && maze[iEntrance][jEntrance-1] != '1'&&  maze[iEntrance][jEntrance-1] != 'x'){
     if (startI == iEntrance && startJ == jEntrance){
       printf("The maze is not solvable\n");
     }
     else {    
 printf("The maze has been solved by finding the exit on the left side. \n");
   }    
 solved =1;       
   }

   else if  (maze[iEntrance-1][jEntrance] != '0' &&  maze[iEntrance-1][jEntrance] != '1'&& maze[iEntrance-1][jEntrance] != 'x'){
     if (startI ==iEntrance && startJ == jEntrance){
       printf("The maze is not solvable\n");
     }
     else {
     printf("The maze has been solved by finding the exit on the top  side. \n");	  
     }      
solved =1;
   }
   else if ((maze[iEntrance+1][jEntrance] != '0' &&  maze[iEntrance+1][jEntrance] != '1' && maze[iEntrance+1][jEntrance] != 'x') && (triedRight == 1 || triedLeft == 1 || triedUp == 1|| triedDown == 1 )){
     if(startI == iEntrance && startJ == jEntrance){
       printf("The maze is not solvable\n");
     }     
printf("Maze has been solved by finding the exit on the bottom side.\n");
     solved =1;     
   }// end if
    }// end check
       
     if (solved == 0){



       // checks to see if up movement is possible
    if ((maze[iEntrance-1][jEntrance] == '0' || maze[iEntrance-1][jEntrance] == 'x') && (triedRight == 0 && triedDown == 0 && wentUp == 0)){

      // determines if there is a wall up ahead 
  if (maze[iEntrance-1][jEntrance] == '0' && maze[iEntrance][jEntrance-1] == '1' && maze[iEntrance][jEntrance+1] == '1'){
	triedUp =1;
      }

  // if no wall, go up
  else {
	  printf("up \n");
	  iEntrance = moveUp(iEntrance);
	  maze[iEntrance][jEntrance] ='x';
	  triedUp = 1;
  }
	  wentRight = 0;
	  wentLeft = 0;
	  wentDown = 0;
	  triedRight = 0;
	  triedLeft =0;
  
 }
    // if surrounding area, flip flag
     if (maze[iEntrance][jEntrance-1] == '1' && maze[iEntrance][jEntrance+1] == '1' && maze[iEntrance+1][jEntrance] == '1'){
     printf("Can no longer go down.\n");
     triedDown = 0;
     wentDown = 1;
 }
   
      if (maze[iEntrance][jEntrance-1]== '1' && maze[iEntrance][jEntrance+1] == '1' && maze[iEntrance-1][jEntrance] == '1'){
	printf("Can no longer go up.\n");
	triedUp = 0;
	wentUp = 1;   
}
      if (triedRight = 1 && maze[iEntrance][jEntrance +1] == '1'){
	triedRight = 0;
      }

      if (maze[iEntrance-1][jEntrance]== '1' && maze[iEntrance+1][jEntrance] == '1' && maze[iEntrance][jEntrance + 1] == '1'){
	printf("Can no longer go right.\n");         
	triedRight = 0;
	wentRight = 1; 
   }
 

  if(maze[iEntrance-1][jEntrance]=='1' && maze[iEntrance+1][jEntrance] == '1' && maze[iEntrance][jEntrance -1] == '1'){
    printf("Can no longer go left.\n");
     wentLeft = 1;
     triedLeft = 0; 
   }
  
  if(maze[iEntrance+1][jEntrance] == 'x' && maze[iEntrance-1][jEntrance] == 'x' && maze[iEntrance][jEntrance+1] == 'x' && (maze[iEntrance][jEntrance-1] == '0' || maze[iEntrance][jEntrance-1] == 'x')){  
 printf("forced right\n");
     jEntrance = moveRight(jEntrance);
     maze[iEntrance][jEntrance] = 'x';
     triedUp = 0;
     triedLeft = 0;
     triedRight = 1;
     triedDown = 0;
   }

  else if((maze[iEntrance][jEntrance-1] == 'x' && maze[iEntrance][jEntrance+1] == 'x' && maze[iEntrance-1][jEntrance] == 'x') &&( maze[iEntrance+1][jEntrance] == '0' || maze[iEntrance+1][jEntrance]== 'x')){
   printf("forced down");     
iEntrance = moveDown(iEntrance);
     maze[iEntrance][jEntrance] = 'x';
     triedDown = 1;
      triedLeft = 0;
     triedRight = 0;
     triedUp = 0;
        }

 else if ((maze[iEntrance +1][jEntrance] == 'x' && maze[iEntrance-1][jEntrance] == 'x' && maze[iEntrance][jEntrance+1] == 'x') && (maze[iEntrance][jEntrance-1] == '0'|| maze[iEntrance][jEntrance-1]== 'x')){
     printf("forced left\n");
     jEntrance = moveLeft(jEntrance);
     maze[iEntrance][jEntrance] = 'x';
     triedLeft = 1;
     triedRight = 0;
     triedUp = 0;
     triedDown = 0;  
  }

 else if ((maze[iEntrance][jEntrance+1] == 'x' && maze[iEntrance][jEntrance-1] == 'x' && maze[iEntrance][jEntrance] == 'x') && (maze[iEntrance+1][jEntrance]== '0' || maze[iEntrance][jEntrance+1] == 'x')){
    printf("forced up\n");    
    jEntrance = moveUp(jEntrance);
     maze[iEntrance][jEntrance]= 'x';
     triedRight = 0;
     triedUp = 1;
     triedDown = 0;
     triedLeft = 0; 
  }
   
     }// end solved
       
     //print out maze                                                                                              ;  
     /*    
          for (i = 0; i < rows; i++){
	 for (j =0; j < columns; j++){
	   printf("%c",  maze[i][j]);
	 }// end for                                                                                                                
	 printf("\n");
       }// end for            
     */ 
  }  // end while
   
   //print out maze                                                                         
   for (i = 0; i < rows; i++){
     for (j =0; j < columns; j++){
       printf("%c",  maze[i][j]);
     }// end for                                                                            
     printf("\n");
   }// end for     ;
}// end solveMaze


int moveRight(int jEntrance){
 jEntrance += 1;  
    return jEntrance;
}

int moveLeft(int jEntrance){
jEntrance -= 1;  
return jEntrance;
}
int moveUp(int iEntrance){
iEntrance -= 1;  
return iEntrance;
}

int moveDown(int iEntrance){
iEntrance += 1; 
 return iEntrance;
}
