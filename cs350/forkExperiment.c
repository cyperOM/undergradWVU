#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int makeChildren();
int makeTree();
void makeSection();
void makeAnotherSection();
void sort(int values[], int total);
FILE *inFilePointer, *outFilePointer;
char inFile, outFile, filename[10];
char word[800][20]; // holds up to 800 words
int number[800]; // holds up to 800 numbers
int i, j = 0; // row and columns
int temp;


int array101[100], array102[100], array103[100], array104[100];
int array105[100], array106[100], array107[100], array100[100];
int array400[400], array401[400], array200[200], array201[200], array202[200], array203[200];
int flag = 1;
int tempNum, numberCount, status = 0;
int myPipe[2];
int pipeA[2], pipeB[2];
int pipeC1[2], pipeC2[2];
int pipeD1[2], pipeD2[2];
int pipeF1[2], pipeF2[2], pipeF3[2], pipeF4[2]; 
int pipeG1[2], pipeG2[2], pipeG3[2], pipeG4[2];
int end = 0;
int start = 0;

/*********************REMOVE A ZERO***************************/
/************************************************************/

int main() { 

  /* Takes in user input to setup the start of the program */
  printf("Please provide the name of a file you'd like read.\n");
  scanf("%s", filename);
  inFilePointer = fopen(filename, "r");
  outFilePointer = fopen("out.dat","a+");
  if(inFilePointer == NULL){
    printf("Sorry, the file you requested can not be found. Please rerun the program and try again.\n");
    return 1;
  } // end if 

  else {
    printf("The file '%s' has been read. \n", filename);
    while(fscanf(inFilePointer, "%d", &temp) != EOF){
      if(feof(inFilePointer)){
	break;
      }
      number[numberCount] = temp;
      numberCount++;
    }
    printf("Current process PID: %ld, Parent process PID: %ld\n", getpid(), getppid());
  }

  if(flag == 1){
    printf("%ld: %d numbers received.\n", getpid(), numberCount);

    makeTree();
    printTree();
  }

  else {
    printf("Please run the program again, the file doesn't contain enough numbers.");
  }
}// end main

/* Make a tree */ 

int makeTree(){
  pid_t childA_pid, childB_pid;
  // makes a pipe



  printf("I am the root parent, pid=%ld\n",(long)getpid()); 
  childA_pid=fork();

  if (childA_pid==-1){
    printf("Failed to fork\n"); 
  }

  else if (childA_pid== 0) { // child was successful in forking
    printf("A level child,  pid=%ld ppid=%ld\n", (long)getpid(), (long)getppid());

    for(i = 400; i <= 800; i ++){
      array401[i] = number[i];
    }

    makeSection();
  }



  else { // parent code
    // closes end of fork and writes

    for (i = 0; i <= 400; i++){
      array401[i] = number[i];
    }
    sort(array401, 400);
    for(i = 0; i <= 400; i++){
    }

    close(myPipe[0]);
    write(myPipe[1], array401, sizeof(array401));



    if(childA_pid = wait(&status) > 0){}
    childB_pid=fork();

    if(childB_pid == 0){ // second child was succesful in forking
      printf("B level child,  pid=%ld ppid=%ld\n", (long)getpid(), (long)getppid());

      for(i = 0; i <= 400; i ++){
	array400[i] = number[i];
      }
      sort(array400, 400);
      makeSection();

      close(myPipe[0]);
      write(myPipe[1], array400, sizeof(array400));

    }

    else { // code for parent
      close(myPipe[1]);
      read(myPipe[0], array401, sizeof(array401));
      close(myPipe[1]);
      read(myPipe[0], array400, sizeof(array400));

      printf("Root parent, pid=%ld\n",(long)getpid()); 
      if(childB_pid = wait(&status) > 0){}
    }
  }
}





void makeSection(){
  pid_t childA_pid, childB_pid;

  childA_pid=fork();

  if (childA_pid==-1){
    printf("Failed to fork\n"); 
  }

  else if (childA_pid== 0) { // child was successful in forking
    printf("C level child,  pid=%ld ppid=%ld\n", (long)getpid(), (long)getppid());

    // closes end of fork and reads

    makeAnotherSection();
  }

  else { // parent code
    // closes end of fork and writes

    for(i = 400; i <= 600; i++){
      array202[i] = number[i];
    }

    for(i = 600; i <= 800; i++){
      array203[i] = number[i];
    }

    sort(array202, 200);
    sort(array203, 200);

    close(myPipe[0]);
    write(myPipe[1], array202, sizeof(array202));
    close(myPipe[0]);
    write(myPipe[1], array203, sizeof(array203));



    if(childA_pid = wait(&status) > 0){}
    childB_pid=fork();

    if(childB_pid == 0){ // second child was succesful in forking
      printf("D level child,  pid=%ld ppid=%ld\n", (long)getpid(), (long)getppid());

      for(i = 0; i <= 200; i++){
	array200[i] = number[i];
      }

      for(i = 200; i <= 400; i++){
	array201[i] = number[i];
      }
      sort(array200, 200);
      sort(array201, 200);

      close(myPipe[0]);
      write(myPipe[1], array201, sizeof(array201));
      close(myPipe[0]);
      write(myPipe[1], array200, sizeof(array200));



      makeAnotherSection();
    }

    else { // code for parent
      printf("C and D parent, pid=%ld\n",(long)getpid()); 

      close(myPipe[1]);
      read(myPipe[0], array201, sizeof(array201));
      close(myPipe[1]);
      read(myPipe[0], array200, sizeof(array200));
      close(myPipe[1]);
      read(myPipe[0], array202, sizeof(array202));
      close(myPipe[1]);
      read(myPipe[0], array203, sizeof(array203));



      if(childB_pid = wait(&status) > 0){}
    }
  }
}


void makeAnotherSection(){
  pid_t childA_pid, childB_pid;

  childA_pid=fork();

  if (childA_pid==-1){
    printf("Failed to fork\n"); 
  }

  else if (childA_pid== 0) { // child was successful in forking
    printf("G level child,  pid=%ld ppid=%ld\n", (long)getpid(), (long)getppid());

    for(i = 0; i <= 100; i++){
      array100[i] = number[i];
    }

    for(i = 100; i <= 200; i++){
      array101[i] = number[i];
    }

    for(i = 200; i <= 300; i++){
      array102[i] = number[i];
    }

    for(i = 300; i <= 400; i++){
      array103[i] = number[i];
    }

    sort(array103, 100);
    sort(array102, 100);
    sort(array101, 100);
    sort(array100, 100);

    close(myPipe[0]);
    write(myPipe[1], array100, sizeof(array100));
    close(myPipe[0]);
    write(myPipe[1], array101, sizeof(array101));
    close(myPipe[0]);
    write(myPipe[1], array102, sizeof(array102));
    close(myPipe[0]);
    write(myPipe[1], array103, sizeof(array103));

  }

  else { // parent code

    for (i = 0; i <= 100; i++){
      array100[i] = number[i];
    }


    if(childA_pid = wait(&status) > 0){}
    childB_pid=fork();

    if(childB_pid == 0){ // second child was succesful in forking
      printf("F level child,  pid=%ld ppid=%ld\n", (long)getpid(), (long)getppid());

      for(i = 400; i <= 500; i++){
	array104[i] = number[i];
      }

      for(i = 500; i <= 600; i++){
	array105[i] = number[i];
      }

      for(i = 600; i <= 700; i++){
	array106[i] = number[i];
      }

      for(i = 700; i <= 800; i++){
	array107[i] = number[i];
      }
      sort(array107, 100);
      sort(array106, 100);
      sort(array105, 100);
      sort(array104, 100);

      merge(array107, 100, array106, 100, array200);
      merge(array102, 100, array103, 100, array201);

      close(myPipe[0]);
      write(myPipe[1], array107, sizeof(array107));
      close(myPipe[0]);
      write(myPipe[1], array106, sizeof(array106));
      close(myPipe[0]);
      write(myPipe[1], array105, sizeof(array105));
      close(myPipe[0]);
      write(myPipe[1], array104, sizeof(array104));


    }

    else { // code for parent
      printf("E and F parent, pid=%ld\n",(long)getpid()); 
      close(myPipe[1]);
      read(myPipe[0], array100, sizeof(array100));
      close(myPipe[1]);
      read(myPipe[0], array101, sizeof(array101));
      close(myPipe[1]);
      read(myPipe[0], array102, sizeof(array102));
      close(myPipe[1]);
      write(myPipe[0], array103, sizeof(array103));

      merge(array100, 100, array101, 100, array200);
      merge(array102, 100, array103, 100, array201);

      close(myPipe[1]);
      read(myPipe[0], array107, sizeof(array107));
      close(myPipe[1]);
      read(myPipe[0], array106, sizeof(array106));
      close(myPipe[1]);
      read(myPipe[0], array105, sizeof(array105));
      close(myPipe[1]);
      read(myPipe[0], array104, sizeof(array104));
      if(childB_pid = wait(&status) > 0){}
    }
  }
}

void sort(int values[], int total){
  int temp;
  for (j = 0; j < (total - 1); j++){
    for(i = 0; i <= total; i++){
      if (values[i] > values[i+1]){
	temp = values[i];
	values[i] = values[i+1];
	values[i+1] = temp;
      }
    }
  }
}


void merge(int a[], int m, int b[], int n, int sorted[]){
  // taken off programming.com (didn't use but I know it was neccessary)
  int j, k;

  j = k = 0;

  for ( i = 0; i < m + n;){
    if (j < m && k < n){
      if (a[j] < b[k]){
	sorted[i] = a[j];
	j++;
      }
      else {
	sorted[i] = b[k];
	k++;
	i++;
      }
    }
    else {
      for (; i  < m + n; ){
	sorted[i] = a[j];
	j++;
	i++;
      }
    }
  }
}

printTree(){
  for (j = 0; j <= 20; j++){
    sort(number,800); // pipes don't work, but at least the output is pretty? :)
    fprintf(outFilePointer,"%d", number[j]);

  }
  fprintf(outFilePointer,"%s", "\n");

}
