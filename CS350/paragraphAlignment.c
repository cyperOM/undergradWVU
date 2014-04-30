#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct listNode {
  char* data;
  struct listNode *nextPtr;
};

typedef struct listNode ListNode;
typedef ListNode *ListNodePtr;

/* prototypes of functions and global variables*/ 
void insert( ListNodePtr *, char*);
void printInternalList( ListNodePtr );
void rightAligned(ListNodePtr, int value);
void justifiedAligned(ListNodePtr, int value);
int i, j = 0; 

int main() { 
  FILE *filePointer;
  ListNodePtr startPtr = NULL;
  char item[250][250];
  char characters[250];
  char file, filename[20];
  int numCharacters = 0;
  int characterNumber = 0;
  int paragraphCounter = 0;

  /* Takes in user input to setup the start of the program */
  printf("Please provide the name of a file you'd like read.\n");
  scanf("%s", filename);

  filePointer = fopen(filename, "r");

  if(filePointer == NULL){
    printf("Sorry, the file you requested can not be found. Please rerun the program and try again.\n");
    return 1;
  }

  printf("Please provide the number of characters you'd like on each line (40 - 100).\n");
  scanf("%d", &characterNumber);
  if (characterNumber < 40 || characterNumber > 100){
    printf("Sorry, the line size you specified is not valid. Please rerun the program and try again.\n");
  }
  else {

    /* Reads in the file */ 
    memset(characters, '\0', sizeof(characters));

    while((file = fgetc(filePointer)) != EOF){
      if (file != '\n' && file != ' ' && file != '\t'){
	numCharacters += 1; 
	characters[i] = file;
	i++; 
	paragraphCounter = 0;
      }

      else {
	// check to make sure white spaces and next lines don't get added
	if (i > 0){
	  strncpy(item[j], characters, 100);
	  memset(characters, '\0', sizeof(characters));
	  insert(&startPtr, item[j]);

	  /* Prints out the interal list representation (LEFT IN FOR GRADING) */ 
	  //printInternalList(startPtr);
	  j++;
	  i = 0;
	  paragraphCounter = 0; 
	}}}}
  fclose(filePointer);
  /* Prints out right aligned single paragraphs (not necessary) */ 
  //rightAligned(startPtr, characterNumber);
  justifiedAligned(startPtr, characterNumber);
}

/* Insert a new value into the list */
void insert( ListNodePtr *sPtr, char* value) { 
  ListNodePtr newPtr, previousPtr, currentPtr;
  newPtr = malloc(sizeof(ListNode));
  if ( newPtr != NULL ) {
    newPtr->data = value;
    newPtr->nextPtr = NULL;
    previousPtr = NULL;
    currentPtr = *sPtr;
    while (currentPtr != NULL) { 
      previousPtr = currentPtr;
      currentPtr = currentPtr->nextPtr; 
    }
    if (previousPtr == NULL) { 
      newPtr->nextPtr = *sPtr;
      *sPtr = newPtr;
    }
    else { 
      previousPtr->nextPtr = newPtr;
      newPtr->nextPtr = currentPtr; 
    }}
  else {
    printf( "%s not inserted. No memory available.\n", value );
  }}

/* Prints out the interal list representation (LEFT IN FOR GRADING) */ 
void printInternalList( ListNodePtr currentPtr )
{ 
  if ( currentPtr == NULL )
    printf( "List is empty.\n\n" );
  else { 
    printf( "The list is:\n" );
    while ( currentPtr != NULL ) { 
      printf( "%s --> ", currentPtr->data);
      currentPtr = currentPtr->nextPtr;
    }
    printf( "EOF\n\n" );
  }}
/* Prints out right alignment (not necessay) */ 
void rightAligned(ListNodePtr currentPtr, int number) { 
  int length = 0;
  int fileLength = 0;

  if ( currentPtr == NULL ) {
    printf( "List is empty.\n\n" );
  }
  else { 
    printf( "The list in right alignment is:\n" );
    while ( currentPtr != NULL ) { 
      length = strlen(currentPtr->data);
      if (length + fileLength <= number){
	printf("%s", currentPtr->data);
	currentPtr = currentPtr->nextPtr;
	fileLength += length;
	if (fileLength + 1 <= number){
	  printf(" ");
	  fileLength++; 
	}}
      else if (fileLength != number) {
	printf(" ");
	fileLength++; 
      }
      else {
	printf("%d", fileLength);
	printf("\n");
	fileLength = 0;
	length = 0;
      }}}}

void justifiedAligned(ListNodePtr currentPtr, int number) { 
  int length = 0;
  int fileLength = 0;
  int addedSpaces = 0;
  int wordCount = 0;
  ListNodePtr start = currentPtr;
  int spaces[100];
  int wordLength[100];
  int words[100];
  int wordTotal;
  int z = 0;

  if ( currentPtr == NULL ) {
    printf( "List is empty.\n\n" );
  }
  else { 
    printf( "The list in justified alignment is:\n" );

    while ( currentPtr != NULL ) { 
      length = strlen(currentPtr->data);
      if (length + fileLength <= number){
	printf("%s", currentPtr->data);

	currentPtr = currentPtr->nextPtr;
	fileLength += length;
	wordTotal += length;
	wordCount++;
	
	if (fileLength + 1 <= number){
	  printf(" ");
	  addedSpaces++;
	  fileLength++; 
	} }
      else if (fileLength != number) {
	printf(" ");
	addedSpaces++;
	fileLength++; 
      }
      else {
	printf("%d", fileLength);
	printf("\n");
	spaces[i] = addedSpaces;
	words[i] = wordCount;
	wordLength[i] = wordTotal;
	addedSpaces = 0; 
	wordTotal = 0;
	wordCount = 0;
	fileLength = 0;
	length = 0;
	i++;
      } }
    spaces[i] = addedSpaces;
    words[i] = wordCount;
    wordLength[i] = wordTotal;
  }
  i = 0;
  addedSpaces = 0; 
  wordTotal = 0;
  wordCount = 0;
  fileLength = 0;
  length = 0;
  /* Returns memory */ 
  free(currentPtr);

  if ( start == NULL ) {
    printf( "List is empty.\n\n" );
  }
  else { 
    printf("\n");
    printf("\n");
    printf( "The list in justified alignment (SECOND) is:\n" );

    while ( start != NULL ) { 
      length = strlen(start->data);
      if (length + fileLength <= number && words[i] > 0){
	printf("%s", start->data);

	/*returns memory */
	free(start);
	start = start->nextPtr; 
	fileLength += length; 
	words[i] -= 1; // decreases amount of words left to print

	while (words[i] > 0 && fileLength + 1 <= number && spaces[i] >= words[i]) { 
	  printf(" ");
	  fileLength++;
	  spaces[i] -=1; 
	} }
      else {
	printf("%d", fileLength);
	printf("\n");
	fileLength = 0;
	length = 0;
	i++;
      } }
    printf("\n");
    addedSpaces = 0; 
    wordTotal = 0;
    wordCount = 0;
    fileLength = 0;
    length = 0;
  } }
